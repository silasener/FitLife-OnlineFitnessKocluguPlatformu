package com.example.fitlifeonlinefitnesskocluguplatformu.service.impl;

import com.example.fitlifeonlinefitnesskocluguplatformu.EgzersizDurumu;
import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.AntrenoreMesajGonderRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.api.request.DanisanGuncellemeRequest;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.*;
import com.example.fitlifeonlinefitnesskocluguplatformu.repository.*;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.DanisanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class DanisanServiceImpl implements DanisanService {
    private DanisanRepo danisanRepo;
    private AntrenorRepo antrenorRepo;
    private DanisanEgzersizProgramlariRepo danisanEgzersizProgramlariRepo;
    private AntrenorEgzersizProgramlariRepo antrenorEgzersizProgramlariRepo;
    private AntrenorGelenKutusuRepo antrenorGelenKutusuRepo;
    private DanisanGelenKutusuRepo danisanGelenKutusuRepo;
    private DanisanBeslenmePlaniRepo danisanBeslenmePlaniRepo;
    private BeslenmePlaniRepo beslenmePlaniRepo;
    private SifreSifirlamaMailiRepo sifreSifirlamaMailiRepo;

    @Override
    public boolean danisanKaydiOlustur(String ad, String soyad,  String cinsiyet,LocalDate dogumTarihi, String telefonNumarasi, String email, String sifre,String dosyaURL) {
        boolean kayitVarMi=false;
        List<Danisan> danisanList=danisanRepo.findAll();
        for (Danisan danisan:danisanList) {
            if(danisan.getEmail().equals(email)){
                System.out.println("Hata: Bu email adresi zaten kullanımda.");
                kayitVarMi=true;
                return false; // Metod sonlanır
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
            return true;
        }
        return false;
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
    public boolean danisanSifreDegistir(String email) {
        List<Danisan> danisanList=danisanRepo.findAll();
        for (Danisan danisan:danisanList) {
            if(danisan.getEmail().equals(email)){
                String yeniSifre="danisan"+danisan.getId()+"yeniSifre";
               danisan.setSifre(yeniSifre);
                danisanRepo.save(danisan);
                SifreSifirlamaMaili sifreSifirlamaMaili=new SifreSifirlamaMaili();
                sifreSifirlamaMaili.setEmail(email);
                sifreSifirlamaMaili.setYeniSifre(yeniSifre);
                sifreSifirlamaMailiRepo.save(sifreSifirlamaMaili);
                return true;
            }
        }
        return false;
    }

    @Override
    public Danisan danisanBul(String email) {
        List<Danisan> danisanList=danisanRepo.findAll();
        for (Danisan danisan:danisanList) {
            if(danisan.getEmail().equals(email)){
                return danisan;
            }
        }
        return null;
    }

    @Override
    public List<Danisan> tumDanisanlariGetir() {
        return danisanRepo.findAll();
    }

    @Override
    public void danisanGuncelle(DanisanGuncellemeRequest request) {
        Danisan danisan=danisanRepo.findDanisanById(request.getId());
        danisan.setAd(request.getAd());
        danisan.setSoyad(request.getSoyad());
        danisan.setTelefonNumarasi(request.getTelefonNumarasi());
        danisan.setEmail(request.getEmail());
        danisan.setSifre(request.getSifre());
        danisanRepo.save(danisan);
    }

    @Override
    public List<DanisanEgzersizProgramlari> danisanProgramlist(int danisanId) {
        Danisan danisan=danisanRepo.findDanisanById(danisanId);
        List<DanisanEgzersizProgramlari> danisanEgzersizProgramlariList=danisanEgzersizProgramlariRepo.findDanisanEgzersizProgramlariByDanisan(danisan);
        return danisanEgzersizProgramlariList;
    }

    @Override
    public AntrenorEgzersizProgramlari getEgzersizPlanDetaylari(int egzersizId) {
        AntrenorEgzersizProgramlari program=antrenorEgzersizProgramlariRepo.findAntrenorEgzersizProgramlariById(egzersizId);
        return program;
    }

    @Override
    public void egzersizPlaniniTamamla(int danisanEgzersizProgramlariId) {
        DanisanEgzersizProgramlari program= danisanEgzersizProgramlariRepo.findDanisanEgzersizProgramlariById(danisanEgzersizProgramlariId);
        program.setEgzersizDurumu(EgzersizDurumu.YAPILDI);
        danisanEgzersizProgramlariRepo.save(program);
    }

    @Override
    public void antrenoreMesajGonder(AntrenoreMesajGonderRequest request) {
        Danisan danisan=danisanRepo.findDanisanById(request.getDanisanId());
        Antrenor antrenor=antrenorRepo.findAntrenorById(request.getAntrenorId());
        AntrenorGelenKutusu antrenorGelenKutusu=new AntrenorGelenKutusu();
        antrenorGelenKutusu.setAntrenor(antrenor);
        antrenorGelenKutusu.setDanisan(danisan);
        antrenorGelenKutusu.setMesaj(request.getMesaj());
        antrenorGelenKutusuRepo.save(antrenorGelenKutusu);
    }

    @Override
    public List<DanisanGelenKutusu> danisanGelenKutusu(int danisanId) {
        Danisan danisan=danisanRepo.findDanisanById(danisanId);
        List<DanisanGelenKutusu> danisaninMesajKutusu=danisanGelenKutusuRepo.findDanisanGelenKutusuByDanisan(danisan);
        return danisaninMesajKutusu;
    }

    @Override
    public List<DanisanBeslenmePlani> beslenmePlanlarimiBul(int danisanId) {
        Danisan danisan=danisanRepo.findDanisanById(danisanId);
        List<DanisanBeslenmePlani> beslenmePlanlarim=danisanBeslenmePlaniRepo.findDanisanBeslenmePlaniByDanisan(danisan);
        return beslenmePlanlarim;
    }

    @Override
    public BeslenmePlani getBeslenmePlaniminDetayi(int beslenmePlanId) {
        BeslenmePlani beslenmePlaniDetay=beslenmePlaniRepo.findById(beslenmePlanId);
        return beslenmePlaniDetay;
    }


}
