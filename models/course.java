package com.nimbus.studentmgmt.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private String courseName;

    private String duration;

    public Course() {}
    public Course(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    // getters and setters
    public Long getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
}
