package com.example.fitlifeonlinefitnesskocluguplatformu.service.impl;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import com.example.fitlifeonlinefitnesskocluguplatformu.repository.DanisanRepo;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.DanisanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class DanisanServiceImpl implements DanisanService {
    private DanisanRepo danisanRepo;

    @Override
    public void danisanKaydiOlustur(String ad, String soyad,  String cinsiyet,LocalDate dogumTarihi, String telefonNumarasi, String email, String sifre,String dosyaURL) {
        boolean kayitVarMi=false;
        List<Danisan> danisanList=danisanRepo.findAll();
        for (Danisan danisan:danisanList) {
            if(danisan.getEmail().equals(email)){
                System.out.println("Hata: Bu email adresi zaten kullanımda.");
                kayitVarMi=true;
                return; // Metod sonlanır
            }
        }

        if(!kayitVarMi){
            Danisan yeniDanisan=new Danisan();
            yeniDanisan.setAd(ad);
            yeniDanisan.setSoyad(soyad);
            yeniDanisan.setDogumTarihi(dogumTarihi);
            yeniDanisan.setCinsiyet(cinsiyet);
            yeniDanisan.setTelefonNumarasi(telefonNumarasi);
            yeniDanisan.setEmail(email);
            yeniDanisan.setProfilFotografi(dosyaURL);
            yeniDanisan.setSifre(sifre);
            danisanRepo.save(yeniDanisan);
            System.out.println("Kayıt Başarılı!");
        }
    }

    @Override
    public Danisan danisanGirisi(String email, String sifre) {
        List<Danisan> danisanList=danisanRepo.findAll();
        for (Danisan danisan:danisanList) {
            if(danisan.getEmail().equals(email) && danisan.getSifre().equals(sifre)){
                return danisan;
            }
        }
        return null;
    }

    @Override
    public boolean danisanSifreDegistir(String email, String yeniSifre) {
        List<Danisan> danisanList=danisanRepo.findAll();
        for (Danisan danisan:danisanList) {
            if(danisan.getEmail().equals(email)){
                danisan.setSifre(yeniSifre);
                danisanRepo.save(danisan);
                return true;
            }
        }
        return false;
    }


}
