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
@Table(name = "segment")
@NamedQueries({
    @NamedQuery(name = Segment.FIND_ALL, query = "SELECT s FROM Segment s"),
    @NamedQuery(name = Segment.FIND_BY_ID, query="SELECT s FROM Segment s WHERE s.id = :id"),
})
public class Segment {

    public static final String FIND_ALL = "Segment.findAll";

    public static final String FIND_BY_ID = "Segment.findById";

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date created;

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

    @Override
    public String toString() {
        return new StringBuilder("Segment [")
                .append(id).append(", ")
                .append(name).append(", ")
                .append(created).append("]").toString();
    }

    public JsonObject toJson(URI self) {
        return Json.createObjectBuilder()
                .add("name", this.name)
                .add("created", this.created.toString())
                .add("_links", Json.createObjectBuilder()
                        .add("rel", "self")
                        .add("href", self.toString())
                )
                .build();
    }
}
