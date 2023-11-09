package com.example.fitlifeonlinefitnesskocluguplatformu.service;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Admin;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import org.springframework.stereotype.Component;

@Component
public interface AdminService {

    Admin adminGirisi(String email, String sifre);

    boolean adminSifreDegistir(String email,String yeniSifre);

    Admin adminBul(String email);

    void danisanBilgileriniGuncelle(Danisan danisan);

    void antrenorBilgileriniGuncelle(Antrenor antrenor);

    void danisanDevreDisiBirak(Danisan danisan);

    void antrenorDevreDisiBirak(Antrenor antrenor);

    void danisanEtkinlestir(Danisan danisan);

    void antrenorEtkinlestir(Antrenor antrenor);
}
