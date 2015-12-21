package com.acme.vdi.rest.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acme.vdi.rest.model.MockTask;
import com.acme.vdi.rest.model.Task;
import com.acme.vdi.rest.model.VDI;
import com.acme.vdi.service.VDIService;

/**
 * A Spring Controller to handle {@link VDI} CRUD operations.
 */
@RestController
@RequestMapping("vdis")
@Api(tags = { "VDIService" })
public class VDIController {

    private VDIService vdiService;
    private VDIConverter vdiConverter;

    @Autowired
    public VDIController(final VDIService vdiService, final VDIConverter vdiConverter) {
        this.vdiService = vdiService;
        this.vdiConverter = vdiConverter;
    }

    @ApiOperation(value = "List VDI's", notes = "Get the list of all configured VDI's from the system", nickname = "getVdis")
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response = com.acme.common.rest.model.Error.class) })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "Size of the page you want to retrieve. Default page size is 10.", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "Page you want to retrieve. Default page number is 0.", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "Properties that should be sorted by in the format property,property(,ASC|DESC). "
                    + "Default sort direction is ascending. Use multiple sort parameters if you want to switch directions, "
                    + "e.g. ?sort=firstname&sort=lastname,asc.", required = false, dataType = "int", paramType = "query") })
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public PagedResources<VDI> getVdis(
            @PageableDefault final Pageable pageable,
            final PagedResourcesAssembler<com.acme.vdi.dal.model.VDI> pagedAssembler,
            @ApiParam(value = "Search VDI params", required = false) @RequestParam(value = "search", required = false) final String search) {
        return pagedAssembler.toResource(vdiService.search(pageable, search), vdiConverter);
    }

    /**
     * Create a {@link com.acme.vdi.rest.model.VDI}.
     *
     * @param vdi
     *            The {@link com.acme.vdi.rest.model.VDI} to create.
     * @return The {@link com.acme.vdi.rest.model.VDI} if it was created successfully.
     */
    @ApiOperation(value = "Create VDI", notes = "Create and configure VDI", nickname = "createVdi", code = 201, response = VDI.class)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response = com.acme.common.rest.model.Error.class) })
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public final VDI createVdi(
            @ApiParam(value = "Create VDI configuration", required = true) @RequestBody(required = true) final VDI vdi) {
        return vdiConverter.toResource(vdiService.create(vdiConverter.fromResource(vdi)));
    }

    /**
     * Given a ID, return a {@link com.acme.vdi.rest.model.VDI}.
     *
     * @param vdiId
     *            The ID to lookup a {@link com.acme.vdi.rest.model.VDI} by.
     * @return The {@link com.acme.vdi.rest.model.VDI} if found
     */
    @ApiOperation(value = "Get VDI", notes = "Get the details of a specific VDI", nickname = "getVdi", response = VDI.class, code = 200)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response = com.acme.common.rest.model.Error.class) })
    @RequestMapping(value = "/{vdiId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public final VDI getVdi(
            @ApiParam(value = "VDI's unique identifier", required = true) @PathVariable final String vdiId) {
        return vdiConverter.toResource(vdiService.get(vdiId));
    }

    /**
     * Update a given {@link com.acme.vdi.rest.model.VDI}.
     *
     * @param vdiId
     *            The VDI ID to update
     * @param vdi
     *            The {@link com.acme.vdi.rest.model.VDI} to update
     * @return The updated {@link com.acme.vdi.rest.model.VDI} if found
     */
    @ApiOperation(value = "Update VDI", notes = "Update the configurable details of a VDI", nickname = "updateVdi", response = VDI.class, code = 200)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response = com.acme.common.rest.model.Error.class) })
    @RequestMapping(value = "/{vdiId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public final VDI updateVdi(
            @ApiParam(value = "VDI's unique identifier", required = true) @PathVariable final String vdiId,
            @ApiParam(value = "VDI's configuration that needs to be updated", required = true) @RequestBody(required = true) final VDI vdi) {
        return vdiConverter.toResource(vdiService.update(vdiConverter.fromResource(vdi)));
    }

    /**
     * Delete a {@link com.acme.vdi.rest.model.VDI} given an ID.
     *
     * @param vdiId
     *            The VDI ID to be deleted
     */
    @ApiOperation(value = "Delete VDI", notes = "Delete a specific VDI", nickname = "deleteVdi", code = 204)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response = com.acme.common.rest.model.Error.class) })
    @RequestMapping(value = "/{vdiId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public final void deleteVdi(
            @ApiParam(value = "VDI's unique identifier", required = true) @PathVariable final String vdiId) {
        vdiService.delete(vdiId);
    }

    /**
     * Deprecated operation.
     *
     * @param vdiId
     *            The VDI ID to perform the deprecated operation
     */
    @ApiOperation(value = "Deprecated operation", nickname = "deprecatedOperation", code = 204)
    @Deprecated
    @RequestMapping(value = "/{vdiId}/deprecatedOp", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public final void deprecatedOperation(
            @ApiParam(value = "VDI's unique identifier", required = true) @PathVariable final String vdiId) {
    }

    /**
     * Put VDI in maintenance mode.
     */
    @ApiOperation(value = "VDI Enter-Maintenance Mode", notes = "Put VDI in maintenance mode", nickname = "enterMaintenance", code = 202)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response = com.acme.common.rest.model.Error.class) })
    @RequestMapping(value = "/{vdiId}/action/enter-maintenance", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public final Task enterMaintenance(
            @ApiParam(value = "VDI's unique identifier", required = true) @PathVariable final String vdiId) {
        return MockTask.getOne(vdiId, "Enter-Maintenance");
    }

    /**
     * Exit VDI from maintenance mode.
     */
    @ApiOperation(value = "VDI Exit-Maintenance Mode", notes = "Exit VDI from maintenance mode", nickname = "exitMaintenance", code = 202)
    @ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error", response = com.acme.common.rest.model.Error.class) })
    @RequestMapping(value = "/{vdiId}/action/exit-maintenance", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public final Task exitMaintenance(
            @ApiParam(value = "VDI's unique identifier", required = true) @PathVariable final String vdiId) {
        return MockTask.getOne(vdiId, "Exit-Maintenance");
    }

    /**
     * Perform a private operation.
     *
     * @param vdiId
     *            The VDI ID to perform the private operation
     */
    @ApiOperation(hidden = true, value = "Perform a private operation", nickname = "privateOperation", code = 204)
    @RequestMapping(value = "/{vdiId}/privateOp", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public final void privateOperation(
            @ApiParam(value = "VDI's unique identifier", required = true) @PathVariable final String vdiId) {
    }

}
