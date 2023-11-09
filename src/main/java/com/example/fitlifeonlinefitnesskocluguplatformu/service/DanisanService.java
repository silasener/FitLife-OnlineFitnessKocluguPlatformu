package com.example.fitlifeonlinefitnesskocluguplatformu.service;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface DanisanService {
    void danisanKaydiOlustur(String ad, String soyad,  String cinsiyet,LocalDate dogumTarihi, String telefonNumarasi, String email, String sifre,String dosyaURL);

    Danisan danisanGirisi(String email, String sifre);

    boolean danisanSifreDegistir(String email,String yeniSifre);

    Danisan danisanBul(String email);

    List<Danisan> tumDanisanlariGetir();
}
