package com.api.j2ee.shoppings.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;

import javax.ws.rs.*;
import java.util.*;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

import com.api.j2ee.shoppings.repository.StoreRepository;
import com.api.j2ee.shoppings.repository.SegmentRepository;
import com.api.j2ee.shoppings.entity.Store;
import com.api.j2ee.shoppings.entity.Segment;
/**
 * Serviços de entrada REST, abaixos estão as operações de negócio relacionadas a Lojas.
 *
 * @author Diego Costa
 * @since 1.0.0
 */
@Api
@Stateless
@Path("/store")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class StoreRest {
    
    @Inject
    StoreRepository storeRepository;

    @Inject
    SegmentRepository segmentRepository;

    @POST
    @Path("/populate")
    @Produces(MediaType.APPLICATION_JSON)
    public Store createPopulate(){

        Store store = new Store();
        store.setName("Loja Modelo | Com Segmento 1");
        store.setCreated(new Date());
        store.setCnpj("86.140.865/0001-79");
        store.setNumber(1);
        store.setFloor(2);
        
        Segment segment = this.segmentRepository.findById(1L);
        List<Segment> segments = new ArrayList<>();
        segments.add(segment);
        store.setSegments(segments);
        
        this.storeRepository.create(store);
        
        return store;
    }


    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    public Store createStore(Store store){

        Store newStore = new Store();
        newStore.setName(store.getName());
        newStore.setCreated(new Date());
        newStore.setCnpj(store.getCnpj());
        newStore.setNumber(store.getNumber());
        newStore.setFloor(store.getFloor());
        this.storeRepository.create(newStore);

        return newStore;
    }

    @GET
    @Path("/findbyid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Store getStoreById(@PathParam("id") Long id) {

        Store store = this.storeRepository.findById(id);
        return null != store ? store : new Store();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Store updateStore(Store store) {

        Store storePU = this.storeRepository.findById(store.getId());
        storePU.setName(store.getName());
        this.storeRepository.update(storePU);
        return store;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Store> getStoreList(){

        List<Store> all = this.storeRepository.findAll();
        return all;
    }


}
