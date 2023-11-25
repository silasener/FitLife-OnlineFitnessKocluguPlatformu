package com.example.fitlifeonlinefitnesskocluguplatformu.service;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.AntrenorDeneyimleri;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Deneyimler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface AntrenorService {
    void antrenorKaydiOlustur(String ad, String soyad,  String cinsiyet,LocalDate dogumTarihi, String telefonNumarasi, String email, String sifre,String dosyaURL);

    Antrenor antrenorGirisi(String email, String sifre);

    boolean antrenorSifreDegistir(String email,String yeniSifre);

    Antrenor antrenorBul(String email);

    List<Antrenor> tumAntrenorleriGetir();

    void profilimiGuncelle(Integer antrenorId,String ad, String soyad, String telefonNumarasi, String email, String sifre);

    List<Danisan> danisanlarimList(Integer antrenorId);

    List<Deneyimler> deneyimList();

    void deneyimEkle(int antrenorId,int deneyimId,boolean uzmanlikAlaniMi);

    void deneyimSil(int antrenorId, int deneyimId);

    void uzmanlikAlaniEkle(int antrenorId, int deneyimId);

    List<AntrenorDeneyimleri> getAntrenorDeneyimler(int antrenorId);

    List<Deneyimler> getAntrenorunSahipOlmadigiDeneyimler(int antrenorId);

    void uzmanlikAlaniKaldir(int antrenorId, int deneyimId);

    void antrenorBilgileriGuncelle(Antrenor antrenor);
}
