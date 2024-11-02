package com.ipl.auction.integration;

import com.ipl.auction.dto.ESPNPlayer;
import com.ipl.auction.entity.player_entity.Player;
import com.opencsv.CSVWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EspnCricInfoUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EspnCricInfoUtil.class);

    private EspnCricInfoUtil() {
    }


    public static void processPlayerList(int year, List<ESPNPlayer> playerList) {
        String newCsvFilePath = "src/main/resources/players/%s_Players.csv";

        try {
            Map<String, CSVWriter> writers = new HashMap<>() {
                @Override
                public CSVWriter get(Object key) {
                    if (!containsKey(key) && !key.equals("AHM")) {
                        try {

                            String filePath = getIplPlayersListFile(year, key.toString());
                            Path path = Paths.get(filePath);

                            Files.createDirectories(path.getParent());

                            if (Files.exists(path)) {
                                Files.delete(path);
                            }
                            Files.createFile(path);

                            CSVWriter csvWriter = new CSVWriter(Files.newBufferedWriter(path));
                            csvWriter.writeNext(ESPNPlayer.getCsvHeaders());
                            put((String) key, csvWriter);

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    if(key.equals("AHM")){  // AHM is not a valid team name
                        key = "GT";
                    }
                    return super.get(key);
                }
            };

            try {

                // Write player data
                playerList.forEach(player -> {
                    CSVWriter csvWriter = writers.get(player.getIplTeam());
                    csvWriter.writeNext(player.toCsv());
                });

                writers.values().forEach(writer -> {
                    try {
                        writer.close();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

            } catch (Exception e) {
                LOGGER.error("An error occurred: ", e);
            }

            LOGGER.info("Processing complete. New JSON file created at: {}",newCsvFilePath);

        } catch (Exception e) {
            LOGGER.error("Error occurred while processing player list: ", e);
        }
    }

    private static String getIplPlayersListFile(int year,String teamName){
        return "src/main/resources/players/" + year + "/" + teamName + "_Players.csv";
    }

    public static Path getIplPlayerImage(Player player) {
        String imageFolder = "src/main/resources/players/images";
        String imageName = player.getName().replace(" ", "_") + ".jpg";
        return new File(imageFolder, imageName).toPath();
    }

}
