package seb.course.persistence.relationships;

import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;

import java.io.Serializable;
import java.util.Objects;

public class GradeId implements Serializable {
    private Long course;
    private Long studentProfile;

    public GradeId() {}

    public GradeId(Long studentProfile, Long course) {
        this.studentProfile = studentProfile;
        this.course = course;
    }

    // hashCode and equals are MANDATORY
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeId gradeId = (GradeId) o;
        return Objects.equals(studentProfile, gradeId.studentProfile) &&
                Objects.equals(course, gradeId.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentProfile, course);
    }
}
