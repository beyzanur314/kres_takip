package tr.ogm.gov.krestakip.model.service;

import org.springframework.stereotype.Service;
import tr.ogm.gov.krestakip.model.entity.Ogrenci;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public abstract class OgrenciService extends AbstractOgrenciService{
    @Override
    public List<Ogrenci> findAll() {
        return ogrenciRepository.findByOrderByKayitNoAsc();
    }

    @Override
    public Ogrenci save(Ogrenci entity) {
        return ogrenciRepository.save(entity);
    }

    @Override
    public void update(Ogrenci entity)
    {
        Ogrenci databasOgrenci= ogrenciRepository.findById(entity.getId())
                .orElseThrow(()-> new InvalidParameterException("Bir problem meydana geldi."));
        databasOgrenci.setAd(entity.getAd());
        databasOgrenci.setSoyad(entity.getSoyad());
        databasOgrenci.setTc(entity.getTc());
        databasOgrenci.setAdres(entity.getAdres());
        databasOgrenci.setCinsiyet(entity.getCinsiyet());
        databasOgrenci.setKayitNo(entity.getKayitNo());
        databasOgrenci.setTelefon(entity.getTelefon());
        databasOgrenci.setKayitTarihi(entity.getKayitTarihi());
        databasOgrenci.setYetki(entity.getYetki());
        ogrenciRepository.save(entity);
    }

    @Override
    public void deleteByID(Integer id) {
        try {
            ogrenciRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
        }
    }
}
