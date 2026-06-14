package project2.controller;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCrudController<T> {

    private final JpaRepository<T, Integer> repository;

    protected AbstractCrudController(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Map<String, Object>> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(entity -> ResponseEntity.ok(toResponse(entity)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody T entity) {
        T savedEntity = repository.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(savedEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id, @RequestBody T entity) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        setId(entity, id);
        T savedEntity = repository.save(entity);
        return ResponseEntity.ok(toResponse(savedEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private Map<String, Object> toResponse(T entity) {
        Map<String, Object> response = new LinkedHashMap<>();
        Class<?> currentClass = entity.getClass();

        while (currentClass != null && currentClass != Object.class) {
            for (Field field : currentClass.getDeclaredFields()) {
                if (shouldSkip(field)) {
                    continue;
                }

                try {
                    field.setAccessible(true);
                    response.put(field.getName(), field.get(entity));
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot read field: " + field.getName(), e);
                }
            }

            currentClass = currentClass.getSuperclass();
        }

        return response;
    }

    private boolean shouldSkip(Field field) {
        int modifiers = field.getModifiers();
        return Modifier.isStatic(modifiers)
                || field.getName().equalsIgnoreCase("password")
                || field.isAnnotationPresent(OneToMany.class)
                || field.isAnnotationPresent(ManyToOne.class)
                || field.isAnnotationPresent(OneToOne.class)
                || field.isAnnotationPresent(ManyToMany.class);
    }

    private void setId(T entity, Integer id) {
        Class<?> currentClass = entity.getClass();

        while (currentClass != null && currentClass != Object.class) {
            try {
                Field idField = currentClass.getDeclaredField("id");
                idField.setAccessible(true);
                idField.set(entity, id);
                return;
            } catch (NoSuchFieldException e) {
                currentClass = currentClass.getSuperclass();
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Cannot set entity id", e);
            }
        }

        throw new IllegalStateException("Entity does not have an id field: " + entity.getClass().getName());
    }
}
