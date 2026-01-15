package seb.course.exception;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class StudentNotFoundError extends Exception{
    public StudentNotFoundError(String name) {
        super("Student with name " + name + " not found.");
    }
    public StudentNotFoundError(Long id) {
        super("Student with id " + id + " not found.");
    }
}
