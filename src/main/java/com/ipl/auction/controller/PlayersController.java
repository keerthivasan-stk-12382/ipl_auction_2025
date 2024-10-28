package com.ipl.auction.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipl.auction.entity.Player;
import com.ipl.auction.service.PlayersServiceInterface;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/player")
public class PlayersController {

    @Autowired
    PlayersServiceInterface playerService;

    @Autowired
    ResourceLoader resourceLoader;

    static Map<String, CSVWriter> writers = new HashMap<>();

    @RequestMapping("/process")
    public String process() {
        String newCsvFilePath = "src/main/resources/players/%s_Players.csv";

        try {
        // Read from old JSON file
        Resource resource = resourceLoader.getResource("classpath:players/2024IPLPlayerList.json");
        InputStream inputStream = resource.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputStream);
        jsonNode = jsonNode.get("results");

        Map<String, CSVWriter> writers = new HashMap<>();
        try {

            // Write player data
            jsonNode.forEach(node -> {
                String name = node.get("known_as").asText().replace("\"", "");
                String role = node.get("role").asText().replace("\"", "");
                String team = node.get("abbreviation").asText().replace("\"", "");

                String[] data = {name, role, node.get("abbreviation").asText()};
                CSVWriter csvWriter = getCSVWriter(team);
                csvWriter.writeNext(data);
            });

            closeAllWriters();
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }

        return "Processing complete. New JSON file created at: " + newCsvFilePath;
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }

    @RequestMapping("/all")
    public List<Player> getPlayers() {
        return playerService.getAllPlayers();
    }

    private CSVWriter getCSVWriter(String teamName){
        if (writers.containsKey(teamName)) {
            return writers.get(teamName);
        }
        String newCsvFilePath = String.format("src/main/resources/players/%s_Players.csv",teamName);
        Writer writer = null;
        try {
            writer = Files.newBufferedWriter(Paths.get(newCsvFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CSVWriter csvWriter = new CSVWriter(writer);
        writers.put(teamName, csvWriter);
        String[] header = {"name", "Role", "Team"};
        csvWriter.writeNext(header);
        return csvWriter;
    }

    private void closeAllWriters() {
        for (CSVWriter writer : writers.values()) {
            try {
                writer.close();
            } catch (IOException e) {
                System.out.println("Error closing writer: " + e.getMessage());
            }
        }
        writers.clear();
    }
}
