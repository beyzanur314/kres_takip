package tr.ogm.gov.krestakip.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.ogm.gov.krestakip.model.entity.Ogrenci;

import java.util.List;

@Repository
public interface OgrenciRepository extends JpaRepository<Ogrenci,Integer>
{
    List<Ogrenci> findByOrderByKayitNoAsc();
}
