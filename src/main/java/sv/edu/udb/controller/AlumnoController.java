package sv.edu.udb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.dto.AlumnoDTO;
import sv.edu.udb.service.AlumnoService;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public ResponseEntity<List<AlumnoDTO>> getAllAlumnos() {
        return ResponseEntity.ok(alumnoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDTO> getAlumnoById(@PathVariable Long id) {
        AlumnoDTO alumno = alumnoService.findById(id);
        return alumno != null ? ResponseEntity.ok(alumno) : ResponseEntity.notFound().build();
    }

    @GetMapping("/materia/{materiaId}")
    public ResponseEntity<List<AlumnoDTO>> getAlumnosByMateria(@PathVariable Long materiaId) {
        List<AlumnoDTO> alumnos = alumnoService.findByMateriaId(materiaId);
        return !alumnos.isEmpty() ? ResponseEntity.ok(alumnos) : ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<AlumnoDTO> createAlumno(@RequestBody AlumnoDTO alumnoDTO) {
        return ResponseEntity.ok(alumnoService.save(alumnoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoDTO> updateAlumno(@PathVariable Long id, @RequestBody AlumnoDTO alumnoDTO) {
        AlumnoDTO updated = alumnoService.update(id, alumnoDTO);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
        alumnoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}