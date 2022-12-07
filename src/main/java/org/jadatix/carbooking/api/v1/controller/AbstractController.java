package org.jadatix.carbooking.api.v1.controller;

import org.jadatix.carbooking.api.v1.request.AbstractRequest;
import org.jadatix.carbooking.api.v1.response.AbstractResponse;
import org.jadatix.carbooking.model.IdentifierEntity;
import org.jadatix.carbooking.service.AbstractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractController<T extends IdentifierEntity, Req extends AbstractRequest<T>, Res extends AbstractResponse> {

    public static final Integer DEFAULT_PAGE_SIZE = 25;
    public static final Integer DEFAULT_PAGE_INDEX = 0;

    protected abstract AbstractService<T> getService();

    protected abstract Res toResponse(T t);

    @GetMapping
    public ResponseEntity<PageResponse<Res>> getAll(
            @RequestParam(value = "index") Optional<Integer> index,
            @RequestParam(value = "size") Optional<Integer> size) {
        Integer pageIndex = index.orElse(DEFAULT_PAGE_INDEX);
        Integer pageSize = size.orElse(DEFAULT_PAGE_SIZE);

        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<T> pagedEntities = getService().get(pageable);

        List<Res> responses = pagedEntities.getContent().stream()
                .map(item -> toResponse(item))
                .collect(Collectors.toList());

        PageResponse<Res> response = new PageResponse<>(responses,
                pagedEntities.getTotalElements(), pagedEntities.getNumber(), pagedEntities.getSize());

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Res> get(@PathVariable Long id) {
        T t = getService().get(id);
        return ResponseEntity.ok(toResponse(t));
    }

    @PostMapping
    public ResponseEntity<Res> create(@RequestBody Req body) {
        T t = getService().create(body.create());
        return ResponseEntity.ok(toResponse(t));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Res> update(@PathVariable Long id, @RequestBody Req body) {
        T old = getService().get(id);
        T t = getService().update(body.update(body.update(old)));
        return ResponseEntity.ok(toResponse(t));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        getService().delete(id);
    }

}
