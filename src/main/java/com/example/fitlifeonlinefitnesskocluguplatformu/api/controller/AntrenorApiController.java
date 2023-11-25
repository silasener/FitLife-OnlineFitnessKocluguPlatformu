package com.example.fitlifeonlinefitnesskocluguplatformu.api.controller;

import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.DeneyimEkleRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.AntrenorDeneyimleri;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Deneyimler;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AntrenorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/deneyimler")
    public ResponseEntity<List<Deneyimler>> getDeneyimler() {
        List<Deneyimler> deneyimler =antrenorService.deneyimList();
        return ResponseEntity.ok(deneyimler);
    }

    @GetMapping("/antrenorDeneyimleri")
    public ResponseEntity<List<AntrenorDeneyimleri>> getAntrenorDeneyimleri(@RequestParam Integer  antrenorId) {
        List<AntrenorDeneyimleri> antrenorDeneyimleri = antrenorService.getAntrenorDeneyimler(antrenorId);
        return ResponseEntity.ok(antrenorDeneyimleri);
    }

    @GetMapping("/antrenorunSahipOlmadigiDeneyimler")
    public ResponseEntity<List<Deneyimler>> getAntrenorunSahipOlmadigiDeneyimler(@RequestParam Integer  antrenorId) {
        List<Deneyimler> antrenorunSahipOlmadigiDeneyimler= antrenorService.getAntrenorunSahipOlmadigiDeneyimler(antrenorId);
        return ResponseEntity.ok(antrenorunSahipOlmadigiDeneyimler);
    }


    @PostMapping("/deneyimEkle")
    public ResponseEntity<String> deneyimEkle(@RequestBody DeneyimEkleRequest request) {
        try {
            antrenorService.deneyimEkle(request.getAntrenorId(), request.getDeneyimId(), request.isUzmanlikAlaniMi());
            return ResponseEntity.ok("Deneyim eklendi.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deneyim eklenirken bir hata oluştu.");
        }
    }

    @DeleteMapping("/deneyimSil/{antrenorId}/{deneyimId}")
    public ResponseEntity<String> deneyimSil(@PathVariable int antrenorId, @PathVariable int deneyimId) {
        try {
            antrenorService.deneyimSil(antrenorId,deneyimId);
            return ResponseEntity.ok("Deneyim silindi.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deneyim silinirken bir hata oluştu.");
        }
    }

    @PutMapping("/uzmanlikAlaniOlarakIsaretle/{antrenorId}/{deneyimId}")
    public ResponseEntity<String> uzmanlikAlaniEkle(@PathVariable int antrenorId, @PathVariable int deneyimId) {
        try {
            antrenorService.uzmanlikAlaniEkle(antrenorId,deneyimId);
            return ResponseEntity.ok("Uzmanlık alanı olarak işaretlendi.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Uzmanlık alanı eklenirken bir hata oluştu.");
        }
    }

    @PutMapping("/uzmanlikAlaniniKaldir/{antrenorId}/{deneyimId}")
    public ResponseEntity<String> uzmanlikAlaniniKaldir(@PathVariable int antrenorId, @PathVariable int deneyimId) {
        try {
            antrenorService.uzmanlikAlaniKaldir(antrenorId,deneyimId);
            return ResponseEntity.ok("Uzmanlık alanı kaldırıldı.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Uzmanlık alanı kaldırılırken bir hata oluştu.");
        }
    }


    @PostMapping("/guncelleAntrenor")
    public ResponseEntity<?> guncelleAntrenor(@RequestBody Antrenor antrenor) {
        antrenorService.antrenorBilgileriGuncelle(antrenor);
        return ResponseEntity.ok("Danışan bilgileri başarıyla güncellendi.");
    }



}
