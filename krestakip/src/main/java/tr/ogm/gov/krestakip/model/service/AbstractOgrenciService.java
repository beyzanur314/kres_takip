package tr.ogm.gov.krestakip.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import tr.ogm.gov.krestakip.model.entity.Ders;
import tr.ogm.gov.krestakip.model.entity.Ogrenci;
import tr.ogm.gov.krestakip.model.repository.OgrenciRepository;

public abstract class AbstractOgrenciService implements BaseService<Ogrenci,Integer>
{
    @Autowired
    protected OgrenciRepository ogrenciRepository;

    public abstract void save(Ders ders);
}
