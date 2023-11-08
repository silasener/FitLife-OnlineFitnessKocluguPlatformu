package com.example.fitlifeonlinefitnesskocluguplatformu.service.impl;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import com.example.fitlifeonlinefitnesskocluguplatformu.repository.AntrenorRepo;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AntrenorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class AntrenorServiceImpl implements AntrenorService {
    private AntrenorRepo antrenorRepo;

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


}
