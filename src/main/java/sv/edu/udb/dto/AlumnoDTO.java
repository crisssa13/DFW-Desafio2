package sv.edu.udb.dto;

import lombok.Data;

@Data
public class AlumnoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private Long materiaId;
    private String materiaNombre;
}