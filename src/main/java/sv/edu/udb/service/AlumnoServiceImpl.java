package sv.edu.udb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.udb.dto.AlumnoDTO;
import sv.edu.udb.dto.MateriaDTO;
import sv.edu.udb.model.Alumno;
import sv.edu.udb.model.Materia;
import sv.edu.udb.repository.AlumnoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final MateriaService materiaService;

    @Autowired
    public AlumnoServiceImpl(AlumnoRepository alumnoRepository, MateriaService materiaService) {
        this.alumnoRepository = alumnoRepository;
        this.materiaService = materiaService;
    }

    @Override
    public List<AlumnoDTO> findAll() {
        return alumnoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlumnoDTO findById(Long id) {
        return alumnoRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public AlumnoDTO save(AlumnoDTO alumnoDTO) {
        Alumno alumno = new Alumno();
        return convertAndSave(alumno, alumnoDTO);
    }

    @Override
    public AlumnoDTO update(Long id, AlumnoDTO alumnoDTO) {
        return alumnoRepository.findById(id)
                .map(alumno -> convertAndSave(alumno, alumnoDTO))
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        alumnoRepository.deleteById(id);
    }

    @Override
    public List<AlumnoDTO> findByMateriaId(Long materiaId) {
        return alumnoRepository.findByMateriaId(materiaId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private AlumnoDTO convertToDTO(Alumno alumno) {
        AlumnoDTO dto = new AlumnoDTO();
        dto.setId(alumno.getId());
        dto.setNombre(alumno.getNombre());
        dto.setApellido(alumno.getApellido());

        if(alumno.getMateria() != null) {
            dto.setMateriaId(alumno.getMateria().getId());
            dto.setMateriaNombre(alumno.getMateria().getNombre());
        }

        return dto;
    }

    private AlumnoDTO convertAndSave(Alumno alumno, AlumnoDTO dto) {
        alumno.setNombre(dto.getNombre());
        alumno.setApellido(dto.getApellido());

        if(dto.getMateriaId() != null) {
            MateriaDTO materiaDTO = materiaService.findById(dto.getMateriaId());
            if(materiaDTO != null) {
                Materia materia = new Materia();
                materia.setId(materiaDTO.getId());
                materia.setNombre(materiaDTO.getNombre());
                alumno.setMateria(materia);
            }
        }

        Alumno saved = alumnoRepository.save(alumno);
        return convertToDTO(saved);
    }
}