package seb.course.persistence.relationships;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE)
    public Long profileId;
    public String bio;
    public float averageGrade;
    @OneToOne
    @JoinColumn(name = "student_id")
    public Student student;
    @OneToMany(mappedBy = "studentProfile", cascade = CascadeType.ALL)
    public List<Grade> grades;

    public StudentProfile() {}



    public Long getProfileId() {
        return profileId;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(float averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
