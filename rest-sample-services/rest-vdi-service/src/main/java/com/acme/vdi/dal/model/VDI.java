package com.acme.vdi.dal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

import com.acme.common.rest.model.BaseEntity;

/**
 * The User Entity.
 */
@Getter
@Setter
@Entity
public class VDI extends BaseEntity {

    /** The Identifier for the VDI. */
    @Id
    private String vdiId;

    /** VDI Name. */
    @Column(nullable = false)
    private String vdiName;

    /** CPU. */
    @Column(nullable = false)
    private Integer cpu;

    /** Memory. */
    @Column(nullable = false)
    private Integer memory;

    /** Storage. */
    @Column(nullable = false)
    private Integer storage;

    /** New 2.0 Property. */
    @Column(nullable = false)
    private String new20Property;

}
