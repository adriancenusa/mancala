package com.interview.mancala.game.controller;

import com.interview.mancala.game.gameplay.GameService;
import com.interview.mancala.game.gameplay.GameStateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;


    @GetMapping("/")
    public String homePage(Model model) {
        GameStateDTO currentState = gameService.getCurrentState();
        model.addAttribute("currentState", currentState);
        return "home";
    }


    @PostMapping("/play/{index}")
    public String play(Model model, @PathVariable Integer index) {
        GameStateDTO currentState = gameService.play(index);
        model.addAttribute("currentState", currentState);
        return "home :: board";
    }


    @PostMapping("/reset")
    public String reset(Model model) {
        GameStateDTO currentState = gameService.reset();
        model.addAttribute("currentState", currentState);
        return "home :: board";
    }

}
