package seb.course.persistence.relationships;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import seb.course.exception.StudentNotFoundError;

import java.util.Map;

@Stateless
public class StudentService {
    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;
    public Student getStudentById(Long id) throws StudentNotFoundError {
        Student s =  em.find(Student.class, id);
        if(s != null){
            return s;
        } else {
            throw new StudentNotFoundError(id);
        }

    }

    public void createStudent(Student student){
        em.persist(student);
    }

    public Student updateStudent(Long id, Student newStudent) throws StudentNotFoundError {
        Student existingStudent = em.find(Student.class, id);
        if (existingStudent != null) {
            existingStudent.setFullName(newStudent.getFullName());
            existingStudent.setAddress(newStudent.getAddress());
            existingStudent.setDob(newStudent.getDob());
            em.merge(existingStudent);
        } else {
            throw new StudentNotFoundError(id);
        }
        return existingStudent;
    }

    public void deleteStudent(Long id) throws StudentNotFoundError {
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
        } else {
            throw new StudentNotFoundError(id);
        }
    }

    public Student getStudentByName(String name) throws StudentNotFoundError {
        try {
            return em.createQuery("SELECT s FROM Student s WHERE s.fullName = :name", Student.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e){
            throw new StudentNotFoundError(name);
        }
    }

    public Student getStudentByNameWithGrades(String name) throws StudentNotFoundError {
        EntityGraph<?> graph = em.getEntityGraph("Student.withGrades");

        Map<String,Object> hint = Map.of("jakarta.persistence.fetchgraph", graph);
        try {
            return em.createQuery("SELECT s FROM Student s WHERE s.fullName = :name", Student.class)
                    .setParameter("name", name)
                    .setHint("jakarta.persistence.fetchgraph", graph)
                    .getSingleResult();
        }catch (NoResultException e){
            throw new StudentNotFoundError(name);
        }
    }
}
