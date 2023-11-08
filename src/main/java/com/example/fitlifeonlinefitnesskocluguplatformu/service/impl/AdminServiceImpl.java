package com.example.fitlifeonlinefitnesskocluguplatformu.service.impl;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Admin;
import com.example.fitlifeonlinefitnesskocluguplatformu.repository.AdminRepo;
import com.example.fitlifeonlinefitnesskocluguplatformu.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepo adminRepo;

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

}
