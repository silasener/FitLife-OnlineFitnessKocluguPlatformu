package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DanisanHedefleriRepo extends JpaRepository<DanisanHedefleri,Integer> {

    List<DanisanHedefleri> findDanisanHedefleriByDanisan(Danisan danisan);

    DanisanHedefleri findDanisanHedefleriByDanisanAndDeneyim(Danisan danisan, Deneyimler deneyim);
}
