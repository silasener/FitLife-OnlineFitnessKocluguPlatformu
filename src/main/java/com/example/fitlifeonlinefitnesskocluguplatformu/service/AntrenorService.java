package com.example.fitlifeonlinefitnesskocluguplatformu.service;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface AntrenorService {
    void antrenorKaydiOlustur(String ad, String soyad, LocalDate dogumTarihi, String cinsiyet, String telefonNumarasi,String dosyaURL,String email,String sifre);
}
