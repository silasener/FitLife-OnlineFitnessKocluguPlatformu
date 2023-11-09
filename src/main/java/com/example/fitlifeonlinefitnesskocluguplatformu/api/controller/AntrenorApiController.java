package com.example.fitlifeonlinefitnesskocluguplatformu.api.controller;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AntrenorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/antrenorGuncelle")
    public ResponseEntity<?> antrenorGuncelle(@RequestPart("id") Integer id,
                                              @RequestPart("ad") String ad,
                                              @RequestPart("soyad") String soyad,
                                              @RequestPart("telefonNumarasi") String telefonNumarasi,
                                              @RequestPart("email") String email,
                                              @RequestPart("sifre") String sifre) {

        antrenorService.profilimiGuncelle(id, ad, soyad, telefonNumarasi, email, sifre);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/danisanlarim")
    public ResponseEntity<?> getDanisanlarim(@RequestParam Integer  antrenorId) {
        List<Danisan> danisanList=antrenorService.danisanlarimList(antrenorId);
        return ResponseEntity.ok(danisanList);
    }




}
