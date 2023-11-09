package com.example.fitlifeonlinefitnesskocluguplatformu.api.controller;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AntrenorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/antrenor")
public class AntrenorApiController {

    @Autowired
    private AntrenorService antrenorService;

    @GetMapping("/info")
    public ResponseEntity<?> getAntrenorInfo(@RequestParam String type, @RequestParam String email) {
        Antrenor antrenor=antrenorService.antrenorBul(email);
        return ResponseEntity.ok(antrenor);
    }
}
