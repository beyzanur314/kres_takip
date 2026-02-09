package tr.ogm.gov.krestakip.model.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Ders {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    @Id
    private int id;

    @Column(name="ad")
    private String ad;

    public Integer getId() {
        return id;
    }


}
