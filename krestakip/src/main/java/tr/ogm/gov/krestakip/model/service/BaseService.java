package tr.ogm.gov.krestakip.model.service;

import java.util.List;

public interface BaseService<E,I>
{
    List<E> findAll();

    E save(E entity);

    void update(E entity);

    void deleteByID(I id);
}
