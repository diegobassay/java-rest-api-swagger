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

/**
 * Entidade que representa as Lojas no sistema de api.
 * @author Diego Costa.
 * @since 1.0.0
 */
//@Entity
//@Embeddable
//@Table(name = "store_segment")
public class StoreSegment {
    
    @Column(name = "store_id")
    @NotNull
    private Long storeId;
    
    @Column(name = "segment_id")
    @NotNull
    private Long segmentId;

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Long segmentId) {
        this.segmentId = segmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass()) 
            return false;
 
        StoreSegment that = (StoreSegment) o;
        return that.storeId.equals(storeId) && 
               that.segmentId.equals(segmentId);
    }
 
    @Override
    public int hashCode() {
        return storeId.intValue() * segmentId.intValue();
    }

}
