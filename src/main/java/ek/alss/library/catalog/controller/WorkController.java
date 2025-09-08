package ek.alss.library.catalog.controller;

import ek.alss.library.catalog.dto.WorkDto;
import ek.alss.library.catalog.service.WorkService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/works")
public class WorkController {

    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping
    public ResponseEntity<List<WorkDto>> getAllWorks() {
        List<WorkDto> works = workService.getAllWorks();
        return ResponseEntity.ok(works);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkDto> getWorkById(@PathVariable Long id) {
        try {
            WorkDto work = workService.getWorkById(id);
            return ResponseEntity.ok(work);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<WorkDto> createWork(@Valid @RequestBody WorkDto work) {
        try {
            WorkDto createdWork = workService.createWork(work);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWork);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkDto> updateWork(@PathVariable Long id, @Valid @RequestBody WorkDto work) {
        try {
            WorkDto updatedWork = workService.updateWork(id,work);
            return ResponseEntity.ok(updatedWork);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWork(@PathVariable Long id) {
        try {
            workService.deleteWork(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<WorkDto>> searchWorkByTitle(@RequestParam String title) {
        try {
            List<WorkDto> work = workService.searchWorks(title);
            return ResponseEntity.ok(work);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}