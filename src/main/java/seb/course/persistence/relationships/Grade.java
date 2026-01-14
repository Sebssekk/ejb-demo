package seb.course.persistence.relationships;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@IdClass(GradeId.class)
public class Grade {
    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @Id
    @ManyToOne
    @JoinColumn(name = "student_profile_id")
    private StudentProfile studentProfile;
    private float value;
    private Date dateAwarded;


    public Grade() {}

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getDateAwarded() {
        return dateAwarded;
    }

    public void setDateAwarded(Date dateAwarded) {
        this.dateAwarded = dateAwarded;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }
}
