package com.example.fitlifeonlinefitnesskocluguplatformu.api.controller;

import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.GirisRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class FitLifeApiController {

    //kullanıcı turu: antrenor, danisan

    @PostMapping("/kayitOl")
    public ResponseEntity<String> kayitOl(@RequestPart("kullaniciTuru") String kullaniciTuru, @RequestPart("ad") String ad, @RequestPart("soyad") String soyad,
            @RequestPart("cinsiyet") String cinsiyet, @RequestPart("dogumTarihi") String dogumTarihi, @RequestPart("telefonNumarasi") String telefonNumarasi,
            @RequestPart("email") String email, @RequestPart("sifre") String sifre, @RequestPart("profilFotografi") MultipartFile profilFotografi) {

        System.out.println(kullaniciTuru);
        System.out.println(ad);
        System.out.println(soyad);
        System.out.println(cinsiyet);
        System.out.println(dogumTarihi);
        System.out.println(telefonNumarasi);
        System.out.println(email);
        System.out.println(sifre);
        System.out.println(profilFotografi.getName());

        if (kullaniciTuru.equals("antrenor")) {
            System.out.println("antrenör seçtik");
        } else if (kullaniciTuru.equals("danisan")) {
            System.out.println("danisan seçtik");
        }
        System.out.println("kayıt atıldı");

        String sonuc = "Kullanıcı Türü: " + kullaniciTuru + ", Ad: " + ad + ", Soyad: " + soyad;

        return ResponseEntity.ok("Başarılı! " + sonuc);
    }



    @PostMapping("/girisYap")
    public ResponseEntity<String> girisYap(@RequestBody GirisRequest girisRequest) {
        System.out.println("giriş yaptı");
        return ResponseEntity.ok("giriş yapıldı");
    }


}
