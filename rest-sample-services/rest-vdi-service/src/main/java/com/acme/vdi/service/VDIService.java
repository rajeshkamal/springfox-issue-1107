package com.acme.vdi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.acme.vdi.dal.model.VDI;

/**
 * VDI Service
 */
public interface VDIService {

    VDI create(VDI vdi);

    VDI get(String vdiId);

    void delete(String vdiId);

    VDI update(VDI vdi);

    Page<VDI> search(Pageable pageable, String search);

}
