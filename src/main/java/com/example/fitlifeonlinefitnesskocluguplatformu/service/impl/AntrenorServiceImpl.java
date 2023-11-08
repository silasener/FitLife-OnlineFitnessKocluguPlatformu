package com.example.fitlifeonlinefitnesskocluguplatformu.service.impl;

import com.example.fitlifeonlinefitnesskocluguplatformu.service.AntrenorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@AllArgsConstructor
@Service
public class AntrenorServiceImpl implements AntrenorService {
    @Override
    public void antrenorKaydiOlustur(String ad, String soyad, LocalDate dogumTarihi, String cinsiyet, String telefonNumarasi, String dosyaURL, String email, String sifre) {
        System.out.println("kayittttt");
    }
}
