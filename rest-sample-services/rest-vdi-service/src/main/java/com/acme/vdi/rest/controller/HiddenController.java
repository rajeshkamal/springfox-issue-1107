package com.acme.vdi.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.acme.vdi.rest.model.MockVDI;
import com.acme.vdi.rest.model.VDI;

@RestController
@RequestMapping("internals")
@ApiIgnore
public class HiddenController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<VDI> getInternalRacks() {
        return MockVDI.getVdis();
    }

}
