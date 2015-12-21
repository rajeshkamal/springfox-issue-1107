package com.acme.vdi.rest.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockVDI {

    public static VDI getOne() {
        VDI vdi = new VDI();
        vdi.setCpu(120);
        vdi.setMemory(512);
        vdi.setStorage(3);
        vdi.setVdiId(UUID.randomUUID().toString());
        vdi.setVdiName("VDI-" + vdi.getVdiId());
        vdi.setNew20Property("New 2.0 Property");
        return vdi;
    }

    public static List<VDI> getVdis() {
        List<VDI> vdis = new ArrayList<VDI>();
        vdis.add(getOne());
        vdis.add(getOne());
        vdis.add(getOne());
        vdis.add(getOne());
        vdis.add(getOne());
        vdis.add(getOne());
        return vdis;
    }

}