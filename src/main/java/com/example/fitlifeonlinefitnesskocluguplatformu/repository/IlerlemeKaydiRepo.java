package com.example.fitlifeonlinefitnesskocluguplatformu.repository;

import com.example.fitlifeonlinefitnesskocluguplatformu.domain.Danisan;
import com.example.fitlifeonlinefitnesskocluguplatformu.domain.IlerlemeKaydi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IlerlemeKaydiRepo extends JpaRepository<IlerlemeKaydi,Integer> {
    List<IlerlemeKaydi> findIlerlemeKaydiByDanisan(Danisan danisan);

    IlerlemeKaydi findIlerlemeKaydiById(int kayitId);

}
