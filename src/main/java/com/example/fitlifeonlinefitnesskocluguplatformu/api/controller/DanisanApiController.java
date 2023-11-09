package com.example.fitlifeonlinefitnesskocluguplatformu.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/danisan")
public class DanisanApiController {

    @GetMapping("/info")
    public ResponseEntity<?> getDanisanInfo(@RequestParam String type, @RequestParam String email) {

        return ResponseEntity.ok("Danışan bilgileri");
    }

}
