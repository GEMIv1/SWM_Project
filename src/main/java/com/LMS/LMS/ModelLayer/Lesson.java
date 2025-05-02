package com.LMS.LMS.ModelLayer;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String topic;
//    private String content;
//    private String duration;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

//    @ElementCollection
//    private List<String> attendanceCodes; // OTPs for attendance

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String title) {
        this.topic = title;
    }

//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }

//    public String getDuration() {
//        return duration;
//    }
//
//    public void setDuration(String duration) {
//        this.duration = duration;
//    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
//
//    public List<String> getAttendanceCodes() {
//        return attendanceCodes;
//    }
//
//    public void setAttendanceCodes(List<String> attendanceCodes) {
//        this.attendanceCodes = attendanceCodes;
//    }
}
