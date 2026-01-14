package seb.course.persistence.relationships;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class CourseService {
    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;

    public Course getCourseById(Long id){
        return em.find(Course.class, id);
    }

    public void createCourse(Course course){
        em.persist(course);
    }

    public Course updateCourse(Long id, Course newCourse){
        Course existingCourse = em.find(Course.class, id);
        if (existingCourse != null) {
            existingCourse.setCourseName(newCourse.getCourseName());
            existingCourse.setDurationInWeeks(newCourse.getDurationInWeeks());
            em.merge(existingCourse);
        }
        return existingCourse;
    }
    public void deleteCourse(Long id){
        Course course = em.find(Course.class, id);
        if (course != null) {
            em.remove(course);
        }
    }

    public void addStudentToCourse(Long courseId, Student student){
        Course course = em.find(Course.class, courseId);
        if (course != null) {
            course.getStudents().add(student);
            em.merge(course);
        }
    }

    public void addStudentsToCourse(Long courseId, java.util.List<Student> students){
        Course course = em.find(Course.class, courseId);
        if (course != null) {
            course.getStudents().addAll(students);
            em.merge(course);
        }
    }
}
