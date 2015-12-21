package com.acme.vdi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.acme.vdi.dal.model.VDI;
import com.acme.vdi.repository.VDIRepository;
import com.acme.common.rest.exception.NotFoundException;
import com.acme.common.search.RSQLSpecificationFactory;

@Component
public class VDIServiceImpl implements VDIService {

    private VDIRepository vdiRepository;
    private RSQLSpecificationFactory<VDI> rsqlSpecFactory;

    @Autowired
    public VDIServiceImpl(final VDIRepository vdiRepository,
            final RSQLSpecificationFactory<VDI> rsqlSpecFactory) {
        this.vdiRepository = vdiRepository;
        this.rsqlSpecFactory = rsqlSpecFactory;
    }


    @Override
    public VDI create(VDI vdi) {
        vdi.setVdiId(UUID.randomUUID().toString());
        return vdiRepository.save(vdi);
    }

    @Override
    public VDI get(String vdiId) {
        VDI vdi = vdiRepository.findOne(vdiId);
        if (vdi != null) {
            return vdi;
        }
        throw new NotFoundException(VDI.class, vdiId);
    }

    @Override
    public void delete(String vdiId) {
        vdiRepository.delete(vdiId);
    }

    @Override
    public VDI update(VDI vdi) {
        if (vdiRepository.exists(vdi.getVdiId())) {
            return vdiRepository.save(vdi);
        }
        throw new NotFoundException(VDI.class, vdi.getVdiId());
    }

    @Override
    public Page<VDI> search(Pageable pageable, String search) {
        return vdiRepository.findAll(rsqlSpecFactory.createRSQLSpecification(search), pageable);
    }

}
