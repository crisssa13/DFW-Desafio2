package sv.edu.udb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.dto.MateriaDTO;
import sv.edu.udb.service.MateriaService;

import java.util.List;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaDTO>> getAllMaterias() {
        return ResponseEntity.ok(materiaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> getMateriaById(@PathVariable Long id) {
        MateriaDTO materia = materiaService.findById(id);
        return materia != null ? ResponseEntity.ok(materia) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MateriaDTO> createMateria(@RequestBody MateriaDTO materiaDTO) {
        return ResponseEntity.ok(materiaService.save(materiaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaDTO> updateMateria(@PathVariable Long id, @RequestBody MateriaDTO materiaDTO) {
        MateriaDTO updated = materiaService.update(id, materiaDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable Long id) {
        materiaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}