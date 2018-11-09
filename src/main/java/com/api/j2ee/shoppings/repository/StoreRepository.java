package com.api.j2ee.shoppings.repository;

import com.api.j2ee.shoppings.entity.Store;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
import java.util.*;

/**
 * Responsavel pelas operações no banco de dados da entidade Loja.
 * @author Diego Costa
 * @since 1.0.0
 */
@Stateless
public class StoreRepository {
    @PersistenceContext(name = "PU")
    EntityManager em;

    public List<Store> findAll() {
        return this.em.createNamedQuery(Store.FIND_ALL).getResultList();
    }

    public Store findById(Long id) {
        Query query = this.em.createNamedQuery(Store.FIND_BY_ID);
        query.setParameter("id", id);
        List <Store> listStore = query.getResultList();
        return (listStore.size() > 0) ? listStore.get(0) : null;
    }

    public void create(Store store) {
        this.em.persist(store);
        //this.em.flush();
    }

    public void remove(Store store) {
        this.em.remove(store);
    }

    public Store update(Store store){
        this.em.merge(store);
        this.em.flush();

        return store;

    }
}