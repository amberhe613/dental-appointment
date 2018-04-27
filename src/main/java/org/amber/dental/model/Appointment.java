package org.amber.dental.model;

import org.amber.dental.model.audit.DateAudit;
import javax.persistence.*;
import javax.validation.constraints.Size;


/**
 * Created by chenlinquan on 4/23/18.
 */
@Entity
@Table(name = "appointment")
public class Appointment extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorProfile doctorProfile;

    @Size(max = 32)
    private String date;

    @Column(name = "start_time")
    private String start;

    @Column(name = "end_time")
    private String end;

    @Column
    private String price;

    public Appointment() {}

    public Appointment(User patient, DoctorProfile dentist, String date, String start_time, String end_time) {
        this.user = patient;
        this.doctorProfile = dentist;
        this.date = date;
        this.start = start_time;
        this.end = end_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DoctorProfile getDoctorProfile() {
        return doctorProfile;
    }

    public void setDoctorProfile(DoctorProfile doctorProfile) {
        this.doctorProfile = doctorProfile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
