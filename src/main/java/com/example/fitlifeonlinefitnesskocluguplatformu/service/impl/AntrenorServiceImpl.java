package com.example.fitlifeonlinefitnesskocluguplatformu.service.impl;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.DanisanAntrenorEslesmesi;
import com.example.fitlifeonlinefitnesskocluguplatformu.repository.AntrenorRepo;
import com.example.fitlifeonlinefitnesskocluguplatformu.repository.DanisanAntrenorEslesmesiRepo;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AntrenorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AntrenorServiceImpl implements AntrenorService {
    private AntrenorRepo antrenorRepo;
    private DanisanAntrenorEslesmesiRepo danisanAntrenorEslesmesiRepo;

    @Override
    public void antrenorKaydiOlustur(String ad, String soyad,  String cinsiyet,LocalDate dogumTarihi, String telefonNumarasi, String email, String sifre,String dosyaURL) {
        boolean kayitVarMi=false;
        List<Antrenor> antrenorList=antrenorRepo.findAll();
        for (Antrenor antrenor:antrenorList) {
            if(antrenor.getEmail().equals(email)){
                System.out.println("Hata: Bu email adresi zaten kullanımda.");
                kayitVarMi=true;
                return; // Metod sonlanır
            }
        }

        if(!kayitVarMi){
            Antrenor yeniAntrenor=new Antrenor();
            yeniAntrenor.setAd(ad);
            yeniAntrenor.setSoyad(soyad);
            yeniAntrenor.setCinsiyet(cinsiyet);
            yeniAntrenor.setDogumTarihi(dogumTarihi);
            yeniAntrenor.setTelefonNumarasi(telefonNumarasi);
            yeniAntrenor.setEmail(email);
            yeniAntrenor.setSifre(sifre);
            yeniAntrenor.setProfilFotografi(dosyaURL);
            antrenorRepo.save(yeniAntrenor);
            System.out.println("Kayıt Başarılı!");
        }
    }

    @Override
    public Antrenor antrenorGirisi(String email, String sifre) {
        List<Antrenor> antrenorList=antrenorRepo.findAll();
        for (Antrenor antrenor:antrenorList) {
            if(antrenor.getEmail().equals(email) && antrenor.getSifre().equals(sifre)){
                return antrenor;
            }
        }
        return null;
    }

    @Override
    public boolean antrenorSifreDegistir(String email, String yeniSifre) {
        List<Antrenor> antrenorList=antrenorRepo.findAll();
        for (Antrenor antrenor:antrenorList) {
            if(antrenor.getEmail().equals(email)){
                antrenor.setSifre(yeniSifre);
                antrenorRepo.save(antrenor);
                return true;
            }
        }
        return false;
    }

    @Override
    public Antrenor antrenorBul(String email) {
        List<Antrenor> antrenorList=antrenorRepo.findAll();
        for (Antrenor antrenor:antrenorList) {
            if(antrenor.getEmail().equals(email)){
                return antrenor;
            }
        }
        return null;
    }

    @Override
    public List<Antrenor> tumAntrenorleriGetir() {
        return antrenorRepo.findAll();
    }


    @Override
    public void profilimiGuncelle(Integer antrenorId, String ad, String soyad, String telefonNumarasi, String email, String sifre) {
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        antrenor.setAd(ad);
        antrenor.setSoyad(soyad);
        antrenor.setTelefonNumarasi(telefonNumarasi);
        antrenor.setEmail(email);
        antrenor.setSifre(sifre);
        antrenorRepo.save(antrenor);
    }

    @Override
    public List<Danisan> danisanlarimList(Integer antrenorId) {
        List<Danisan> danisanlarim=new ArrayList<>();
        List< DanisanAntrenorEslesmesi> danisanAntrenorEslesmesiList =danisanAntrenorEslesmesiRepo.findDanisanAntrenorEslesmesiByAntrenor_Id(antrenorId);
        for (DanisanAntrenorEslesmesi danisanlar:danisanAntrenorEslesmesiList) {
            danisanlarim.add(danisanlar.getDanisan());
        }
        return danisanlarim;
    }


}
