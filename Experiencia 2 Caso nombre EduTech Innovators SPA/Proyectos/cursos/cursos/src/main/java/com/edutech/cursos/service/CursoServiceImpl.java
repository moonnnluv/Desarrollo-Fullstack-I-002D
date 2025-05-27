package com.edutech.cursos.service;

// IMPORTAMOS CLASE CURSO Y REPOSITORIO CORRESPONDIENTE
import com.edutech.cursos.entity.Curso;
import com.edutech.cursos.repository.CursoRepository;

// SPRING
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// JAVA LIST Y OPTIONAL
import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // READ
    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return (List<Curso>) cursoRepository.findAll();
    }

    // READ POR ID
    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    // GUARDAR
    @Override
    @Transactional
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }
    @Override

    // UPDATE
    public Curso actualizar(Long id, Curso cursoActualizado) {
       Curso curso = cursoRepository.findById(id).orElseThrow();
        curso.setNombre(cursoActualizado.getNombre());
        curso.setDescripcion(cursoActualizado.getDescripcion());
        curso.setInstructor(cursoActualizado.getInstructor());
        curso.setDuracion(cursoActualizado.getDuracion());
        return cursoRepository.save(curso);
    }    

    // DELETE
    @Override
    @Transactional
    public void deleteById(Long id) {
        cursoRepository.deleteById(id);
    }
}
