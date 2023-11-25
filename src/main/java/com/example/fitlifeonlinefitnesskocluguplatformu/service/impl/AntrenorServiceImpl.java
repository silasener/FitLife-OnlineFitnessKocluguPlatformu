package com.example.fitlifeonlinefitnesskocluguplatformu.service.impl;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.*;
import com.example.fitlifeonlinefitnesskocluguplatformu.repository.AntrenorDeneyimleriRepo;
import com.example.fitlifeonlinefitnesskocluguplatformu.repository.AntrenorRepo;
import com.example.fitlifeonlinefitnesskocluguplatformu.repository.DanisanAntrenorEslesmesiRepo;
import com.example.fitlifeonlinefitnesskocluguplatformu.repository.DeneyimlerRepo;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AntrenorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AntrenorServiceImpl implements AntrenorService {
    private AntrenorRepo antrenorRepo;
    private DanisanAntrenorEslesmesiRepo danisanAntrenorEslesmesiRepo;
    private DeneyimlerRepo deneyimlerRepo;
    private AntrenorDeneyimleriRepo antrenorDeneyimleriRepo;

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

    @Override
    public List<Deneyimler> deneyimList() {
        return deneyimlerRepo.findAll();
    }

    @Override
    public void deneyimEkle(int antrenorId, int deneyimId, boolean uzmanlikAlaniMi) {
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        Deneyimler deneyim=deneyimlerRepo.findDeneyimlerById(deneyimId);
        AntrenorDeneyimleri antrenorDeneyimleri=new AntrenorDeneyimleri();
        antrenorDeneyimleri.setAntrenor(antrenor);
        antrenorDeneyimleri.setDeneyim(deneyim);
        antrenorDeneyimleri.setUzmanlikAlaniMi(uzmanlikAlaniMi);
        antrenorDeneyimleriRepo.save(antrenorDeneyimleri);
    }

    @Override
    public void deneyimSil(int antrenorId, int deneyimId) {
        Deneyimler deneyim=deneyimlerRepo.findDeneyimlerById(deneyimId);
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        AntrenorDeneyimleri deneyimim= antrenorDeneyimleriRepo.findAntrenorDeneyimleriByAntrenorAndDeneyim(antrenor,deneyim);
        antrenorDeneyimleriRepo.delete(deneyimim);
    }

    @Override
    public void uzmanlikAlaniEkle(int antrenorId, int deneyimId) {
        Deneyimler deneyim=deneyimlerRepo.findDeneyimlerById(deneyimId);
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        AntrenorDeneyimleri deneyimim= antrenorDeneyimleriRepo.findAntrenorDeneyimleriByAntrenorAndDeneyim(antrenor,deneyim);
        deneyimim.setUzmanlikAlaniMi(true);
        antrenorDeneyimleriRepo.save(deneyimim);
    }

    @Override
    public List<AntrenorDeneyimleri> getAntrenorDeneyimler(int antrenorId) {
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        List<AntrenorDeneyimleri> deneyimlerimList= antrenorDeneyimleriRepo.findAntrenorDeneyimleriByAntrenor(antrenor);
        return deneyimlerimList;
    }

    @Override
    public List<Deneyimler> getAntrenorunSahipOlmadigiDeneyimler(int antrenorId) {
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        List<AntrenorDeneyimleri> deneyimlerimList= antrenorDeneyimleriRepo.findAntrenorDeneyimleriByAntrenor(antrenor);
        List<Integer> deneyimIdList=new ArrayList<>();
        List<Deneyimler> sahipOlunmayanDeneyimler=new ArrayList<>();
        for (AntrenorDeneyimleri deneyim:deneyimlerimList) {
            deneyimIdList.add(deneyim.getDeneyim().getId());
        }
        sahipOlunmayanDeneyimler=deneyimList().stream().filter(deneyimler -> !deneyimIdList.contains(deneyimler.getId())).collect(Collectors.toList());

        return sahipOlunmayanDeneyimler;
    }

    @Override
    public void uzmanlikAlaniKaldir(int antrenorId, int deneyimId) {
        Deneyimler deneyim=deneyimlerRepo.findDeneyimlerById(deneyimId);
        Antrenor antrenor=antrenorRepo.findAntrenorById(antrenorId);
        AntrenorDeneyimleri deneyimim= antrenorDeneyimleriRepo.findAntrenorDeneyimleriByAntrenorAndDeneyim(antrenor,deneyim);
        deneyimim.setUzmanlikAlaniMi(false);
        antrenorDeneyimleriRepo.save(deneyimim);
    }

    @Override
    public void antrenorBilgileriGuncelle(Antrenor antrenor) {
        Antrenor antrenorunEskiBilgileri=antrenorRepo.findAntrenorById(antrenor.getId());
        antrenorunEskiBilgileri.setAd(antrenor.getAd());
        antrenorunEskiBilgileri.setSoyad(antrenor.getSoyad());
        antrenorunEskiBilgileri.setCinsiyet(antrenor.getCinsiyet());
        antrenorunEskiBilgileri.setDogumTarihi(antrenor.getDogumTarihi());
        antrenorunEskiBilgileri.setTelefonNumarasi(antrenor.getTelefonNumarasi());
        antrenorunEskiBilgileri.setEmail(antrenor.getEmail());
        antrenorunEskiBilgileri.setSifre(antrenor.getSifre());
        antrenorRepo.save(antrenorunEskiBilgileri);
    }


}
