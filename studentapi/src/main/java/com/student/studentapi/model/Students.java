package com.student.studentapi.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student_table")
public class Students {

    @Id
    private String srn;

    private String fname;
    private String lname;
    private String emailid;
    private String phoneNumber;

    // Many-to-many relationship with Subjects
    @ManyToMany
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "srn"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects = new HashSet<>();

    // Default constructor
    public Students() {}

    // Constructor with fields
    public Students(String srn, String fname, String lname, String emailid, String phoneNumber) {
        this.srn = srn;
        this.fname = fname;
        this.lname = lname;
        this.emailid = emailid;
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter for srn
    public String getSrn() {
        return srn;
    }

    public void setSrn(String srn) {
        this.srn = srn;
    }

    // Getter and Setter for fname
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    // Getter and Setter for lname
    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    // Getter and Setter for emailid
    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    // Getter and Setter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter for subjects
    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    // Method to enroll a student in a subject
    public void enrollInSubject(Subject subject) {
        this.subjects.add(subject);
    }

    // Method to unenroll a student from a subject
    public void unenrollFromSubject(Subject subject) {
        this.subjects.remove(subject);
    }
}
