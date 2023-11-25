package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Antrenor;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.AntrenorDeneyimleri;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Deneyimler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AntrenorDeneyimleriRepo extends JpaRepository<AntrenorDeneyimleri,Integer> {
    AntrenorDeneyimleri findAntrenorDeneyimleriByAntrenorAndDeneyim(Antrenor antrenor, Deneyimler deneyim);

    List<AntrenorDeneyimleri> findAntrenorDeneyimleriByAntrenor(Antrenor antrenor);

}
