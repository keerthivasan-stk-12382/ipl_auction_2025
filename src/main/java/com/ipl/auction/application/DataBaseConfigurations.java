package com.ipl.auction.application;

import com.ipl.auction.entity.User;
import com.ipl.auction.entity.player_entity.Player;
import com.ipl.auction.entity.player_entity.PlayerTeamMapping;
import com.ipl.auction.entity.Team;
import com.ipl.auction.entity.player_entity.PlayerExtn;
import com.ipl.auction.enums.Country;
import com.ipl.auction.enums.PlayerCategory;
import com.ipl.auction.enums.UserRoles;
import com.ipl.auction.exceptions.ApplicationException;
import com.ipl.auction.exceptions.UserException;
import com.ipl.auction.repository.TeamRepository;
import com.ipl.auction.service.AuctionUserService;
import com.ipl.auction.service.interfaces.PlayersServiceInterface;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

@Configuration
public class DataBaseConfigurations {

    @Autowired
    Logger auctionStartUpLogger;

    @Autowired
    private Environment environment;

    final ResourceLoader resourceLoader;
    final TeamRepository teamRepository;
    final PlayersServiceInterface playerService;
    final AuctionUserService userService;

    @Autowired
    public DataBaseConfigurations(TeamRepository teamRepository, ResourceLoader resourceLoader, PlayersServiceInterface playerService, AuctionUserService userService) {
        this.teamRepository = teamRepository;
        this.resourceLoader = resourceLoader;
        this.playerService = playerService;
        this.userService = userService;
    }

    @Bean
    public CommandLineRunner loadDB() {
        return args -> {
            try {
                boolean reloadUsers = environment.getProperty("reload.users", Boolean.class, false);
                loadUsers(reloadUsers);

                boolean reloadTeams = environment.getProperty("reload.teams", Boolean.class, false);
                loadIPLTeams(reloadTeams);

                int auctionYear = environment.getProperty("auction.year", Integer.class, LocalDate.now().getYear());
                boolean reloadPlayers = environment.getProperty("reload.players", Boolean.class, false);
                loadPlayers(reloadPlayers,auctionYear);
            } catch (ApplicationException e) {
                auctionStartUpLogger.error("Error occurred during Populating data \n\t Error Code : {} \n\t Error Message : {}", e.getErrorCode(), e.getMessage());
            }
        };
    }

    private void loadUsers(boolean reloadUsers) throws UserException {

        if(!reloadUsers && userService.getUserCount()>0) {
            auctionStartUpLogger.info("Users already loaded");
            return;
        }

        String userName = environment.getProperty("auction.admin.username");
        String password = environment.getProperty("auction.admin.password");
        userService.addUser(new User(userName, password, UserRoles.SUPER_ADMIN));

    }

    private void loadIPLTeams(boolean reloadTeams) {

        if(!reloadTeams && teamRepository.count()>0) {
            auctionStartUpLogger.info("Teams already loaded");
            return;
        }

        Resource resource = resourceLoader.getResource("classpath:static/teams/teams.csv");
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {

            String[] values;
            csvReader.readNext(); // Skip header row

            while ((values = csvReader.readNext()) != null) {
                int teamId = Integer.parseInt(values[0]);
                String teamName = values[1];
                String teamCode = values[2];
                int espnId = Integer.parseInt(values[3]);

                Team team = new Team(teamId, teamName, teamCode, espnId);

                auctionStartUpLogger.info("Saving team: {}", team);
                teamRepository.save(team);
            }
        } catch (IOException | CsvException e) {
            auctionStartUpLogger.error("Error loading teams", e);
        }
    }

    private void loadPlayers(boolean reloadPlayers,int year) {

        if(!reloadPlayers && playerService.getPlayerCount() > 0) {
            auctionStartUpLogger.info("Players already loaded");
            return;
        }

        Resource resource = resourceLoader.getResource("classpath:static/players/" + year);
        try  {

            File file = new File(resource.getURI());
            if(!file.isDirectory() || !file.exists()) {
                auctionStartUpLogger.error("Players file not found");
                return;
            }

            File[] csvFiles = file.listFiles();
            if(csvFiles == null) {
                auctionStartUpLogger.error("No CSV files found");
                return;
            }

            for(File csvFile : csvFiles) {
                loadPlayersFromCSV(csvFile);
            }

        } catch (IOException  e) {
            auctionStartUpLogger.error("Error loading players", e);
        }
    }

    private void loadPlayersFromCSV(File csvFile) {
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFile))) {

            String[] values;
            csvReader.readNext(); // Skip header row

            while ((values = csvReader.readNext()) != null) {
                Player  player = new Player();
                player.setName(values[1]);
                player.setFullName(values[2]);
                player.setCountry(Country.getCountry(values[5]));
                player.setCappedPlayer(Boolean.parseBoolean(values[6]));
                player.setCategory(PlayerCategory.getCategory(values[7]));

                PlayerExtn playerExtn = new PlayerExtn();
                playerExtn.setPlayer(player);
                playerExtn.setDateOFBirth(values[3]);
                playerExtn.setEspnId(Integer.parseInt(values[0]));
                playerExtn.setBattingStyle(values[9]);
                playerExtn.setBowlingStyle(values[10]);
                playerExtn.setImagePath(values[11]);


                PlayerTeamMapping playerTeamMapping = new PlayerTeamMapping();
                String teamCode = values[4];
                teamCode = teamCode.equals("DD") ? "DC" : teamCode.equals("KXIP") ? "PK" : teamCode;

                playerTeamMapping.setPlayer(player);
                playerTeamMapping.setTeam(teamRepository.findByTeamCode(teamCode));

                player.setPlayerExtn(playerExtn);
                player.setPlayerTeamMapping(playerTeamMapping);
                playerService.addOrUpdatePlayer(player);

            }

        } catch (IOException | CsvException e) {
            auctionStartUpLogger.error("Error loading players", e);
        }
    }

}
