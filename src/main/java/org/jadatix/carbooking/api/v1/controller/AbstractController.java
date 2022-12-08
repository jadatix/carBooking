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
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import static java.util.Objects.isNull;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

public abstract class AbstractController<T extends IdentifierEntity, Req extends AbstractRequest<T>, Res extends AbstractResponse> {

    protected abstract AbstractService<T> getService();

    protected abstract Res toResponse(T t);

    @GetMapping
    public ResponseEntity<PageResponse<Res>> getAll(
            @RequestParam(defaultValue = "0") Integer index,
            @RequestParam(defaultValue = "10") Integer size,
            @PathParam(value = "sort") Sort sort) {
        sort = mapSortPropertities(sort);

        Pageable pageable = PageRequest.of(index, size, sort);

        Page<T> pagedEntities = getService().get(pageable);

        List<Res> responses = pagedEntities.getContent().stream()
                .map(item -> toResponse(item))
                .collect(Collectors.toList());

        PageResponse<Res> response = new PageResponse<>(responses,
                pagedEntities.getTotalElements(), pagedEntities.getNumber(), pagedEntities.getSize());

        return ResponseEntity.ok(response);
    }

    protected Sort mapSortPropertities(Sort sort) {
        if (isNull(sort)) {
            return Sort.unsorted();
        }

        Iterator<Order> iterator = sort.iterator();
        List<Order> mappedOrders = new LinkedList<>();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            Order mappedOrder = new Order(
                    order.getDirection(),
                    order.getProperty(),
                    order.getNullHandling());
            mappedOrders.add(mappedOrder);
        }

        return Sort.by(mappedOrders);
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
        T t = getService().update(body.update(old));
        return ResponseEntity.ok(toResponse(t));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        getService().delete(id);
    }

}
