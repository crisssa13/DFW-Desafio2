package sv.edu.udb.service;

import sv.edu.udb.dto.AlumnoDTO;
import java.util.List;

public interface AlumnoService {
    List<AlumnoDTO> findAll();
    AlumnoDTO findById(Long id);
    AlumnoDTO save(AlumnoDTO alumnoDTO);
    AlumnoDTO update(Long id, AlumnoDTO alumnoDTO);
    void delete(Long id);
    List<AlumnoDTO> findByMateriaId(Long materiaId);
}