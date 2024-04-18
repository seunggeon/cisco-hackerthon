package com.cisco.hackerthon.controller;

import com.cisco.hackerthon.dto.SuccessResponse;
import com.cisco.hackerthon.dto.users.KinderDTO;
import com.cisco.hackerthon.service.KinderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Log4j2
@Controller
//@RequestMapping()
public class KinderController {
    @Autowired
    private KinderService seniorService;

    @GetMapping("/index")
    public String test(Model model) {
        KinderDTO kinderInfo = seniorService.getStats();
//        SuccessResponse response = new SuccessResponse(true, "무궁유교 테스트", kinderInfo);
        int seconds = kinderInfo.getDuration();
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;

        model.addAttribute("duration_min", minutes);
        model.addAttribute("duration_sec", remainingSeconds);
        model.addAttribute("freq", kinderInfo.getFrequency());
        return "test";
    }

    @GetMapping("kindergarten/stats/{id}")
    public ResponseEntity<?> stats(@PathVariable int id) {
        KinderDTO kinderInfo = seniorService.getStats();
        SuccessResponse response = new SuccessResponse(true, "무궁유교 테스", kinderInfo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
