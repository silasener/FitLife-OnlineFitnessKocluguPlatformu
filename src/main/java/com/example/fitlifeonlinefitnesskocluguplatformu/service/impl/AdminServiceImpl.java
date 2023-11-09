package com.example.fitlifeonlinefitnesskocluguplatformu.service.impl;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Admin;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import com.example.fitlifeonlinefitnesskocluguplatformu.repository.AdminRepo;
import com.example.fitlifeonlinefitnesskocluguplatformu.repository.AntrenorRepo;
import com.example.fitlifeonlinefitnesskocluguplatformu.repository.DanisanRepo;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepo adminRepo;
    private DanisanRepo danisanRepo;
    private AntrenorRepo antrenorRepo;

    @Override
    public Admin adminGirisi(String email, String sifre) {
        List<Admin> adminList=adminRepo.findAll();
        for (Admin admin:adminList) {
            if(admin.getEmail().equals(email) && admin.getSifre().equals(sifre)){
                return admin;
            }
        }
        return null;
    }

    @Override
    public boolean adminSifreDegistir(String email, String yeniSifre) {
        List<Admin> adminList=adminRepo.findAll();
        for (Admin admin:adminList) {
            if(admin.getEmail().equals(email)){
                admin.setSifre(yeniSifre);
                adminRepo.save(admin);
                return true;
            }
        }
        return false;
    }

    @Override
    public Admin adminBul(String email) {
        List<Admin> adminList = adminRepo.findAll();
        for (Admin admin : adminList) {
            if (admin.getEmail().equals(email)) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public void danisanBilgileriniGuncelle(Danisan danisan) {
        Danisan danisaninEskiBilgileri=danisanRepo.findDanisanById(danisan.getId());
        danisaninEskiBilgileri.setAd(danisan.getAd());
        danisaninEskiBilgileri.setSoyad(danisan.getSoyad());
        danisaninEskiBilgileri.setCinsiyet(danisan.getCinsiyet());
        danisaninEskiBilgileri.setDogumTarihi(danisan.getDogumTarihi());
        danisaninEskiBilgileri.setTelefonNumarasi(danisan.getTelefonNumarasi());
        danisaninEskiBilgileri.setEmail(danisan.getEmail());
        danisaninEskiBilgileri.setSifre(danisan.getSifre());
        danisanRepo.save(danisaninEskiBilgileri);
    }

    @Override
    public void antrenorBilgileriniGuncelle(Antrenor antrenor) {
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

    @Override
    public void danisanDevreDisiBirak(Danisan danisan) {
        Danisan guncelDanisan=danisanRepo.findDanisanById(danisan.getId());
        guncelDanisan.setAktifMi(false);
        danisanRepo.save(guncelDanisan);
    }

    @Override
    public void antrenorDevreDisiBirak(Antrenor antrenor) {
        Antrenor guncelAntrenor=antrenorRepo.findAntrenorById(antrenor.getId());
        guncelAntrenor.setAktifMi(false);
        antrenorRepo.save(guncelAntrenor);
    }

    @Override
    public void danisanEtkinlestir(Danisan danisan) {
        Danisan guncelDanisan=danisanRepo.findDanisanById(danisan.getId());
        guncelDanisan.setAktifMi(true);
        danisanRepo.save(guncelDanisan);
    }

    @Override
    public void antrenorEtkinlestir(Antrenor antrenor) {
        Antrenor guncelAntrenor=antrenorRepo.findAntrenorById(antrenor.getId());
        guncelAntrenor.setAktifMi(true);
        antrenorRepo.save(guncelAntrenor);
    }


}
