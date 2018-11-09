package com.api.j2ee.shoppings.entity;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.net.URI;
import java.util.Date;
import java.util.*;

/**
 * Entidade que representa as Lojas no sistema de api.
 * @author Diego Costa.
 * @since 1.0.0
 */
@Entity
@Table(name = "store")
@NamedQueries({
    @NamedQuery(name = Store.FIND_ALL, query = "SELECT s FROM Store s"),
    @NamedQuery(name = Store.FIND_BY_ID, query="SELECT s FROM Store s WHERE s.id = :id"),
})
public class Store {

    public static final String FIND_ALL = "Store.findAll";

    public static final String FIND_BY_ID = "Store.findById";

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date created;

    private String cnpj;

    private Integer number;

    private Integer floor;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { 
        CascadeType.PERSIST, 
        CascadeType.MERGE
    })
    @JoinTable(name = "store_segment",
        joinColumns = @JoinColumn(name = "store_id"),
        inverseJoinColumns = @JoinColumn(name = "segment_id")
    )
    private List<Segment> segments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }    

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    @Override
    public String toString() {
        return new StringBuilder("Store [")
                .append(id).append(", ")
                .append(name).append(", ")
                .append(created).append(",")
                .append(cnpj).append(",")
                .append(number).append(",")
                .append(floor).append("]")
                .toString();
    }

    public JsonObject toJson(URI self) {
        return Json.createObjectBuilder()
                .add("name", this.name)
                .add("created", this.created.toString())
                .add("cnpj", this.cnpj)
                .add("number", this.number.toString())
                .add("floor", this.floor.toString())
                .add("_links", Json.createObjectBuilder()
                        .add("rel", "self")
                        .add("href", self.toString())
                )
                .build();
    }
}
