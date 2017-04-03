package ua.kushnirenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.kushnirenko.entity.SourceEntity;
import ua.kushnirenko.service.SourceService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
public class DashboardController {

    @Autowired
    private SourceService service;

    @PostMapping("/add")
    public ResponseEntity addNewSource(@RequestBody SourceEntity sr) throws URISyntaxException {
        SourceEntity result = service.addEntity(sr);
        if (result != null)
            return ResponseEntity
                    .created(new URI(""))
                    .body("New entity was with id: " + sr.getId() + " is created.");
        else
            return new ResponseEntity<>(sr, HttpStatus.CONFLICT);
    }

    @GetMapping("/get")
    public ResponseEntity getSource(@RequestParam Long id) {
        if (id == null) return ResponseEntity.badRequest().body("'id' must be positive integer!");
        SourceEntity sr = service.getEntity(id);
        if (sr != null) return ResponseEntity.ok().body(sr);
        else return ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity updateSource(@RequestBody SourceEntity sr) {
        service.updateEntity(sr);
        return ResponseEntity.ok().body(service.getEntity(sr.getId()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSource(@PathVariable Long id) {
        SourceEntity sr = service.deleteEntity(id);
        return ResponseEntity.ok().body(sr);
    }

    @GetMapping("/getAll")
    public List<SourceEntity> getAll() {
        List<SourceEntity> entity_lst = service.getAll();
        return entity_lst;
    }
}
