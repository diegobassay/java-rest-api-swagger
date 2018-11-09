package com.api.j2ee.shoppings.repository;

import com.api.j2ee.shoppings.entity.Segment;
import com.api.j2ee.shoppings.entity.StoreSegment;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Responsavel pelas operações no banco de dados da entidade Segmentos de Loja.
 * @author Diego Costa
 * @since 1.0.0
 */
@Stateless
public class SegmentRepository {
    @PersistenceContext(name = "PU")
    EntityManager em;

    public List<Segment> findAll() {
        return this.em.createNamedQuery(Segment.FIND_ALL).getResultList();
    }

    public Segment findById(Long id) {
        Query query = this.em.createNamedQuery(Segment.FIND_BY_ID);
        query.setParameter("id", id);
        List <Segment> listSegments = query.getResultList();
        return (listSegments.size() > 0) ? listSegments.get(0) : null;
    }

    public void create(Segment store) {
        this.em.persist(store);
        //this.em.flush();
    }

    public void createStoreSegment(StoreSegment storeSegment){
        this.em.persist(storeSegment);
        //this.em.flush();
    }
}