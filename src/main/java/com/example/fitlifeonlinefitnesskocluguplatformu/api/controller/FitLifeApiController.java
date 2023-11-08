package com.example.fitlifeonlinefitnesskocluguplatformu.api.controller;

import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.GirisRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AdminService;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AntrenorService;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.DanisanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class FitLifeApiController {
    private final DanisanService danisanService;
    private final AntrenorService antrenorService;
    private final AdminService adminService;

    public FitLifeApiController(DanisanService danisanService, AntrenorService antrenorService, AdminService adminService) {
        this.danisanService = danisanService;
        this.antrenorService = antrenorService;
        this.adminService= adminService;
    }


    @PostMapping("/kayitOl")
    public ResponseEntity<String> kayitOl(@RequestPart("kullaniciTuru") String kullaniciTuru, @RequestPart("ad") String ad, @RequestPart("soyad") String soyad,
            @RequestPart("cinsiyet") String cinsiyet, @RequestPart("dogumTarihi") String dogumTarihi, @RequestPart("telefonNumarasi") String telefonNumarasi,
            @RequestPart("email") String email, @RequestPart("sifre") String sifre, @RequestPart("profilFotografi") MultipartFile profilFotografi) {

        if (kullaniciTuru.equals("antrenor")) {
            antrenorService.antrenorKaydiOlustur(ad,soyad,cinsiyet, LocalDate.parse(dogumTarihi),telefonNumarasi,email,sifre,profilFotografi.getName());
        } else if (kullaniciTuru.equals("danisan")) {
            danisanService.danisanKaydiOlustur(ad,soyad,cinsiyet, LocalDate.parse(dogumTarihi),telefonNumarasi,email,sifre,profilFotografi.getName());
        }

        String sonuc = "Kullanıcı Türü: " + kullaniciTuru + ", Ad: " + ad + ", Soyad: " + soyad;
        return ResponseEntity.ok("Başarılı! " + sonuc);
    }


    @PostMapping("/girisYap")
    public ResponseEntity<String> girisYap(@RequestBody GirisRequest kullaniciVerileri) {
        if (Objects.nonNull(adminService.adminGirisi(kullaniciVerileri.getEmail(),kullaniciVerileri.getSifre()))) {
            return ResponseEntity.ok("Admin girişi başarılı");
        }else if(Objects.nonNull(antrenorService.antrenorGirisi(kullaniciVerileri.getEmail(),kullaniciVerileri.getSifre()))){
            return ResponseEntity.ok("Antrenör girişi başarılı");
        }else if(Objects.nonNull(danisanService.danisanGirisi(kullaniciVerileri.getEmail(),kullaniciVerileri.getSifre()))){
            return ResponseEntity.ok("Danışan girişi başarılı");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Giriş başarısız");
        }
    }

    @PostMapping("/sifreSifirla")
    public ResponseEntity<String> sifreSifirla(@RequestBody GirisRequest kullaniciVerileri) {
        if(adminService.adminSifreDegistir(kullaniciVerileri.getEmail(),kullaniciVerileri.getSifre())){
            return ResponseEntity.ok("Admin şifre sıfırlama başarılı");
        }else if(antrenorService.antrenorSifreDegistir(kullaniciVerileri.getEmail(),kullaniciVerileri.getSifre())){
            return ResponseEntity.ok("Antrenör şifre sıfırlama başarılı");
        }else if(danisanService.danisanSifreDegistir(kullaniciVerileri.getEmail(),kullaniciVerileri.getSifre())){
            return ResponseEntity.ok("Danışan şifre sıfırlama başarılı");
        }else{
            return ResponseEntity.ok("Şifre sıfırlama başarısız");
        }
    }


}
