package sv.edu.udb.service;

import sv.edu.udb.dto.MateriaDTO;
import java.util.List;

public interface MateriaService {
    List<MateriaDTO> findAll();
    MateriaDTO findById(Long id);
    MateriaDTO save(MateriaDTO materiaDTO);
    MateriaDTO update(Long id, MateriaDTO materiaDTO);
    void delete(Long id);
}