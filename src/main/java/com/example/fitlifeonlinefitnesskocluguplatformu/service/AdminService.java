package com.example.fitlifeonlinefitnesskocluguplatformu.service;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Admin;
import org.springframework.stereotype.Component;

@Component
public interface AdminService {

    Admin adminGirisi(String email, String sifre);

    boolean adminSifreDegistir(String email,String yeniSifre);
}
