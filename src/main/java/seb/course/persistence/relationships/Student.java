package seb.course.persistence.relationships;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@NamedEntityGraph(
        name = "Student.withGrades",
        attributeNodes = {
                @NamedAttributeNode(value = "profile", subgraph = "profileWithGrades")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "profileWithGrades",
                        attributeNodes = {
                                @NamedAttributeNode("grades")
                        }
                )
        }
)
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long studentId;
    private String fullName;
    private Date dob;
    @Embedded
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private StudentProfile profile;

    @ManyToMany(mappedBy = "students")
    public List<Course> courses;

    public Student() {}

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public StudentProfile getProfile() {
        return profile;
    }

    public void setProfile(StudentProfile profile) {
        this.profile = profile;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
