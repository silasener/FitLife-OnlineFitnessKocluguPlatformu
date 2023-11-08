package com.example.fitlifeonlinefitnesskocluguplatformu.service;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface AntrenorService {
    void antrenorKaydiOlustur(String ad, String soyad,  String cinsiyet,LocalDate dogumTarihi, String telefonNumarasi, String email, String sifre,String dosyaURL);

    Antrenor antrenorGirisi(String email, String sifre);

    boolean antrenorSifreDegistir(String email,String yeniSifre);
}
