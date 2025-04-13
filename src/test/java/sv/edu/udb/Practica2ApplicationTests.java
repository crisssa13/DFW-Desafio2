package sv.edu.udb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import sv.edu.udb.model.Alumno;
import sv.edu.udb.model.Materia;
import sv.edu.udb.repository.AlumnoRepository;
import sv.edu.udb.repository.MateriaRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class Practica2ApplicationTests {

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Autowired
	private MateriaRepository materiaRepository;

	@Test
	@Commit
	public void testInsertarDatos() {
		// Insertar materias
		Materia materia1 = new Materia();
		materia1.setNombre("Física");
		materiaRepository.save(materia1);

		Materia materia2 = new Materia();
		materia2.setNombre("Química");
		materiaRepository.save(materia2);

		// Insertar alumnos
		Alumno alumno1 = new Alumno();
		alumno1.setNombre("Pedro");
		alumno1.setApellido("García");
		alumno1.setIdMateria(materia1.getId());
		alumnoRepository.save(alumno1);

		Alumno alumno2 = new Alumno();
		alumno2.setNombre("Sofía");
		alumno2.setApellido("Hernández");
		alumno2.setIdMateria(materia2.getId());
		alumnoRepository.save(alumno2);

		assertNotNull(alumno1.getId());
		assertNotNull(alumno2.getId());
	}
}