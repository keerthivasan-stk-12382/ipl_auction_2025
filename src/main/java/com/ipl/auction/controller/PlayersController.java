package com.ipl.auction.controller;

import com.ipl.auction.service.interfaces.PlayersServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/player")
public class PlayersController {

    @Autowired
    PlayersServiceInterface playerService;

    @GetMapping("")
    public String showPlayers(@RequestParam(value = "sortField",defaultValue = "") String sortField,
                              @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection,
                              @RequestParam(value = "search", defaultValue = "") String search,
                              Model model) {

        if (!sortField.isEmpty()) {
            boolean isAscending = !sortDirection.equalsIgnoreCase("desc");
            model.addAttribute("players", playerService.getAllPlayers(sortField, isAscending));
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDirection", sortDirection);

        } else if (!search.isEmpty()) {
            model.addAttribute("players", playerService.searchPlayer(search));
            model.addAttribute("search", search);
        } else {
            model.addAttribute("players", playerService.getAllPlayers());
        }

        return "players";
    }
}
