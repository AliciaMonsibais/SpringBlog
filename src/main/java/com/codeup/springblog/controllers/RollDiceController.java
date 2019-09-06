package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String guess(){
        return "guess";
    }

    @GetMapping("/roll-dice/{n}")
    public String guessMe(@PathVariable int n, Model model){
        int correctNumber = (int) (Math.floor(Math.random() * 6) +1);
        boolean verify = correctNumber == n;
        model.addAttribute("n", n);
        model.addAttribute("correctNumber", correctNumber);
        model.addAttribute("verify", verify);
        return "dice-roll-verify";
    }

}
