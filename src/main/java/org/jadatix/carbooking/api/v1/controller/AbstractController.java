package org.jadatix.carbooking.api.v1.controller;

import org.jadatix.carbooking.api.v1.request.AbstractRequest;
import org.jadatix.carbooking.api.v1.response.AbstractResponse;
import org.jadatix.carbooking.model.IdentifierEntity;
import org.jadatix.carbooking.service.AbstractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractController<T extends IdentifierEntity, Req extends AbstractRequest<T>, Res extends AbstractResponse> {

    protected abstract AbstractService<T> getService();

    protected abstract Res toResponse(T t);

    @GetMapping
    public ResponseEntity<List<Res>> getAll(){
        List<Res> all = getService().getAll().stream().map(entity -> toResponse(entity)).collect(Collectors.toList());
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Res> get(@PathVariable Long id){
        T t = getService().get(id);
        return ResponseEntity.ok(toResponse(t));
    }

    @PostMapping
    public ResponseEntity<Res> create(@RequestBody Req body){
        T t = getService().create(body.create());
        return ResponseEntity.ok(toResponse(t));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Res> update(@PathVariable Long id,@RequestBody Req body){
        T old  = getService().get(id);
        T t = getService().update(body.update(body.update(old)));
        return ResponseEntity.ok(toResponse(t));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        getService().delete(id);
    }

}
