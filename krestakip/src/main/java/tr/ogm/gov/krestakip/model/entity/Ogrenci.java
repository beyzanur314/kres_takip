package tr.ogm.gov.krestakip.model.entity;

import  lombok.Data;
import  javax.persistence.*;
import java.util.Date;

@Data
@Table(name="Ogrenci")
@javax.persistence.Entity
public class Ogrenci
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    @Id
    private int id;

    @Column(name="ad")
    private String ad;

    @Column (name="soyad")
    private String soyad;

    @Column(name="tc")
    private int tc;

    @Column(name="adres")
    private  String adres;

    @Column(name="telefon")
    private int telefon;

    @Column(name = "cinsiyet")
    private String cinsiyet;

    @Column(name="kayit_tarihi")
    @Temporal(TemporalType.DATE)
    private Date kayitTarihi;

    @Column (name="yetki")
    private  String yetki;

    @Column (name="kayit_no")
    private  int kayitNo;


}