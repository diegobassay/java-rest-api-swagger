package com.api.j2ee.shoppings.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

import com.api.j2ee.shoppings.repository.SegmentRepository;
import com.api.j2ee.shoppings.entity.Segment;
import com.api.j2ee.shoppings.entity.StoreSegment;

/**
 * Serviços de entrada REST, abaixos estão as operações de negócio relacionadas Segmentos de Lojas.
 *
 * @author Diego Costa
 * @since 1.0.0
 */
@Api
@Stateless
@Path("/segment")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class SegmentRest {

    @Inject
    SegmentRepository repository;  

    @POST
    @Path("/populate")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Segment> populateSegments(){

        String arraySegments[] = {"Moda Masculina", "Moda Feminina", "Surfwear", "Calçados"};
        List<String> listNameSegments = new ArrayList<>(Arrays.asList(arraySegments));
        List<Segment> listSegments = new ArrayList<>();
        
        for(String nameSeg : listNameSegments){

            Segment segment = new Segment();
            segment.setName(nameSeg);
            segment.setCreated(new Date());
            this.repository.create(segment);
            listSegments.add(segment);

        }

        return listSegments;
    }     

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Segment> getSegmentList(){

        List<Segment> all = this.repository.findAll();
        return all;
    }



}
