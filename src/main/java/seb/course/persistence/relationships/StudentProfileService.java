package seb.course.persistence.relationships;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class StudentProfileService {
    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;

    public StudentProfile getStudentProfileById(Long id){
        return em.find(StudentProfile.class, id);
    }

    public void createStudentProfile(StudentProfile profile){
        em.persist(profile);
    }

    public StudentProfile updateStudentProfile(Long id, StudentProfile newProfile){
        StudentProfile existingProfile = em.find(StudentProfile.class, id);
        if (existingProfile != null) {
            existingProfile.setBio(newProfile.getBio());
            existingProfile.setAverageGrade(newProfile.getAverageGrade());
            em.merge(existingProfile);
        }
        return existingProfile;
    }

    public void deleteStudentProfile(Long id){
        StudentProfile profile = em.find(StudentProfile.class, id);
        if (profile != null) {
            em.remove(profile);
        }
    }

    public void addGradeToProfile(Long profileId, Grade grade){
        StudentProfile profile = em.find(StudentProfile.class, profileId);
        if (profile != null) {
            profile.getGrades().add(grade);
            em.merge(profile);
        }
    }

    public void addGradesToProfile(Long profileId, java.util.List<Grade> grades){
        StudentProfile profile = em.find(StudentProfile.class, profileId);
        if (profile != null) {
            profile.getGrades().addAll(grades);
            em.merge(profile);
        }
    }

    public void attachStudentToProfile(Long profileId, Student student){
        StudentProfile profile = em.find(StudentProfile.class, profileId);
        if (profile != null) {
            profile.setStudent(student);
            em.merge(profile);
        }
    }
}
