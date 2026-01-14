package seb.course.persistence.relationships;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Map;

@Stateless
public class StudentService {
    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;
    public Student getStudentById(Long id){
        return em.find(Student.class, id);
    }

    public void createStudent(Student student){
        em.persist(student);
    }

    public Student updateStudent(Long id, Student newStudent){
        Student existingStudent = em.find(Student.class, id);
        if (existingStudent != null) {
            existingStudent.setFullName(newStudent.getFullName());
            existingStudent.setAddress(newStudent.getAddress());
            existingStudent.setDob(newStudent.getDob());
            em.merge(existingStudent);
        }
        return existingStudent;
    }

    public void deleteStudent(Long id){
        Student student = em.find(Student.class, id);
        if (student != null) {
            em.remove(student);
        }
    }

    public Student getStudentByName(String name){
        return em.createQuery("SELECT s FROM Student s WHERE s.fullName = :name", Student.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public Student getStudentByNameWithGrades(String name){
        EntityGraph<?> graph = em.getEntityGraph("Student.withGrades");

        Map<String,Object> hint = Map.of("jakarta.persistence.fetchgraph", graph);

        return em.createQuery("SELECT s FROM Student s WHERE s.fullName = :name", Student.class)
                .setParameter("name", name)
                .setHint("jakarta.persistence.fetchgraph", graph)
                .getSingleResult();
    }
}
