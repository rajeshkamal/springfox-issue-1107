package com.acme.vdi.rest.controller;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.acme.vdi.rest.model.VDI;

/**
 * VDI Converter
 */
@Component
public class VDIConverter extends
        ResourceAssemblerSupport<com.acme.vdi.dal.model.VDI, VDI> {

    public VDIConverter() {
        super(VDIController.class, VDI.class);
    }

    @Override
    public final VDI toResource(final com.acme.vdi.dal.model.VDI entity) {
        VDI apiVDI = createResourceWithId(entity.getVdiId(), entity);
        apiVDI.setVdiId(entity.getVdiId());
        apiVDI.setVdiName(entity.getVdiName());
        apiVDI.setCpu(entity.getCpu());
        apiVDI.setMemory(entity.getMemory());
        apiVDI.setStorage(entity.getStorage());
        apiVDI.setNew20Property(entity.getNew20Property());
        return apiVDI;
    }

    public final com.acme.vdi.dal.model.VDI fromResource(final VDI apiVDI) {
        com.acme.vdi.dal.model.VDI entityVDI =
                new com.acme.vdi.dal.model.VDI();
        entityVDI.setVdiId(apiVDI.getVdiId());
        entityVDI.setVdiName(apiVDI.getVdiName());
        entityVDI.setCpu(apiVDI.getCpu());
        entityVDI.setMemory(apiVDI.getMemory());
        entityVDI.setStorage(apiVDI.getStorage());
        entityVDI.setNew20Property(apiVDI.getNew20Property());
        return entityVDI;
    }

}
