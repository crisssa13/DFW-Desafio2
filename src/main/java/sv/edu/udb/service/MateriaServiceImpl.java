package sv.edu.udb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.udb.dto.MateriaDTO;
import sv.edu.udb.model.Materia;
import sv.edu.udb.repository.MateriaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class  MateriaServiceImpl implements MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public List<MateriaDTO> findAll() {
        return materiaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MateriaDTO findById(Long id) {
        return materiaRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public MateriaDTO save(MateriaDTO materiaDTO) {
        Materia materia = new Materia();
        materia.setNombre(materiaDTO.getNombre());
        Materia saved = materiaRepository.save(materia);
        return convertToDTO(saved);
    }

    @Override
    public MateriaDTO update(Long id, MateriaDTO materiaDTO) {
        return materiaRepository.findById(id)
                .map(materia -> {
                    materia.setNombre(materiaDTO.getNombre());
                    Materia updated = materiaRepository.save(materia);
                    return convertToDTO(updated);
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        materiaRepository.deleteById(id);
    }

    private MateriaDTO convertToDTO(Materia materia) {
        MateriaDTO dto = new MateriaDTO();
        dto.setId(materia.getId());
        dto.setNombre(materia.getNombre());
        return dto;
    }
}