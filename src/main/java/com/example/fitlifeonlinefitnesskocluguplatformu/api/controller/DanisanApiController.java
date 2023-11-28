package com.example.fitlifeonlinefitnesskocluguplatformu.api.controller;

import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.AntrenoreMesajGonderRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.DanisanGuncellemeRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.DanisanaMesajGonderRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.*;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.DanisanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/danisan")
public class DanisanApiController {

    @Autowired
    DanisanService danisanService;

    @GetMapping("/info")
    public ResponseEntity<?> getDanisanInfo(@RequestParam String type, @RequestParam String email) {
        Danisan danisan=danisanService.danisanBul(email);
        return ResponseEntity.ok(danisan);
    }

    @PostMapping("/guncelleDanisan")
    public ResponseEntity<?> danisanGuncelle(@RequestBody DanisanGuncellemeRequest request) {
        danisanService.danisanGuncelle(request);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/egzersizPlanlarimiGoster")
    public ResponseEntity<?> egzersizPlanlarimiGoster(@RequestParam Integer danisanId) {
        try {
            List<DanisanEgzersizProgramlari> egzersizList=danisanService.danisanProgramlist(danisanId);
            return ResponseEntity.ok(egzersizList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Danışanın egzersiz planlarını getirirken bir hata oluştu. Hata Detayı: " + e.getMessage());
        }
    }

    @GetMapping("/egzersizPlanDetaylari")
    public ResponseEntity<?> getEgzersizPlanDetaylari(@RequestParam Integer egzersizPlanId) {
        try {
            AntrenorEgzersizProgramlari egzersizPlanDetaylari = danisanService.getEgzersizPlanDetaylari(egzersizPlanId);
                return ResponseEntity.ok(egzersizPlanDetaylari);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Egzersiz plan detayları getirirken bir hata oluştu. Hata Detayı: " + e.getMessage());
        }
    }


    @PostMapping("/egzersizPlanTamamla")
    public ResponseEntity<String> egzersizPlanTamamla(@RequestParam Integer egzersizPlanId) {
        try {
            danisanService.egzersizPlaniniTamamla(egzersizPlanId);
            return ResponseEntity.ok("Egzersiz planı başarıyla tamamlandı.");
        } catch (Exception e) {
            // Hata durumunda ise uygun bir hata mesajı ile 500 Internal Server Error dönebilirsiniz
            return ResponseEntity.status(500).body("Egzersiz planını tamamlama sırasında bir hata oluştu. Hata Detayı: " + e.getMessage());
        }
    }


    @PostMapping("/antrenoreMesajGonder")
    public ResponseEntity<String> mesajGonder(@RequestBody AntrenoreMesajGonderRequest antrenoreMesajGonderRequest) {
        try {
            danisanService.antrenoreMesajGonder(antrenoreMesajGonderRequest);
            return ResponseEntity.ok("Mesaj başarıyla gönderildi.");
        } catch (Exception e) {
            // Hata durumunda iç hata mesajını döndür
            return ResponseEntity.status(500).body("Mesaj gönderilirken bir hata oluştu.");
        }
    }

    @GetMapping("/gelenMesajlar")
    public ResponseEntity<?> getGelenMesajlar(@RequestParam Integer danisanId) {
        try {
            List<DanisanGelenKutusu> gelenKutusu = danisanService.danisanGelenKutusu(danisanId);
            return ResponseEntity.ok(gelenKutusu);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Mesajları getirirken bir hata oluştu.");
        }
    }

    @GetMapping("/beslenmePlanlarimiGoster")
    public ResponseEntity<?> getBeslenmePlanlarim(@RequestParam Integer danisanId) {
        try {
            List<DanisanBeslenmePlani> planlar = danisanService.beslenmePlanlarimiBul(danisanId);
            return ResponseEntity.ok(planlar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Beslenme planlarını getirirken bir hata oluştu.");
        }
    }

    @GetMapping("/beslenmePlanDetaylari")
    public ResponseEntity<?> getBeslenmePlanDetaylari(@RequestParam Integer beslenmePlanId) {
        try {
            BeslenmePlani beslenmeDetaylari = danisanService.getBeslenmePlaniminDetayi(beslenmePlanId);
            return ResponseEntity.ok(beslenmeDetaylari);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Beslenme plan detayları getirirken bir hata oluştu. Hata Detayı: " + e.getMessage());
        }
    }





}
