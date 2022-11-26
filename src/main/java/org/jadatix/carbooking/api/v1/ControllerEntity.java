package org.jadatix.carbooking.api.v1;

import org.jadatix.carbooking.model.IdentifierEntity;
import org.jadatix.carbooking.service.ServiceEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ControllerEntity<T extends IdentifierEntity> {
    ServiceEntity<T> getService();

    @PostMapping
    default T create(@RequestBody T entity) {
        return getService().create(entity);
    }

    @GetMapping
    default List<T> getAll() {
        return getService().getAll();
    }

    @GetMapping("/{id}")
    default T get(@PathVariable("id") Long id) {
        return getService().get(id);
    }

    @PutMapping("/{id}")
    default T update(@RequestBody T entity, @PathVariable("id") Long id) {
        return getService().update(entity);
    }

    @DeleteMapping("/{id}")
    default void delete(@PathVariable("id") Long id) {
        getService().delete(id);
    }
}
