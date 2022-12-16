package org.jadatix.carbooking.api.v1.controller;

import org.jadatix.carbooking.api.v1.request.AbstractRequest;
import org.jadatix.carbooking.api.v1.response.AbstractResponse;
import org.jadatix.carbooking.exception.NotFoundException;
import org.jadatix.carbooking.model.IdentifierEntity;
import org.jadatix.carbooking.service.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public abstract class AbstractController<Entity extends IdentifierEntity, Request extends AbstractRequest<Entity>, Response extends AbstractResponse> {
    protected abstract AbstractService<Entity> getService();

    protected abstract Response convertToResponse(Entity entity);

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Response> getAll() {
        return getService().getAll().stream()
                .map(entity -> convertToResponse(entity))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Response get(@PathVariable Long id) {
        Entity entity = getService().get(id);
        if (isNull(entity)) {
            throw new NotFoundException("Not found entity with " + id);
        }
        return convertToResponse(entity);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody Request body) {
        Entity createdEntity = getService().create(body.create());
        return convertToResponse(createdEntity);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Response update(@PathVariable Long id, @RequestBody Request body) {
        Entity entity = getService().get(id);
        Entity newEntity = body.update(entity);
        Entity updatedEntity = getService().update(newEntity);
        return convertToResponse(updatedEntity);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        getService().delete(id);
    }
}
