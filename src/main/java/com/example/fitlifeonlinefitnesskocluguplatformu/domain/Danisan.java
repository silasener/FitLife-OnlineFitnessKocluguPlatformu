package com.example.fitlifeonlinefitnesskocluguplatformu.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "danisan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Danisan{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ad")
    private String ad;

    @Column(name = "soyad")
    private String soyad;

    @Column(name = "dogum_tarihi")
    private LocalDate dogumTarihi;

    @Column(name = "cinsiyet")
    private String cinsiyet;

    @Column(name = "email")
    private String email;

    @Column(name = "telefon_numarasi")
    private String telefonNumarasi;

    @Column(name = "profil_fotografi")
    private String profilFotografi;

}

