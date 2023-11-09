package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.DanisanAntrenorEslesmesi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DanisanAntrenorEslesmesiRepo extends JpaRepository<DanisanAntrenorEslesmesi,Integer> {
    List<DanisanAntrenorEslesmesi> findDanisanAntrenorEslesmesiByAntrenor_Id(Integer id);
}
