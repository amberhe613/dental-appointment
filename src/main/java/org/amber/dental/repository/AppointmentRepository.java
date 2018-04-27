package org.amber.dental.repository;

import org.amber.dental.model.DoctorProfile;
import org.amber.dental.model.User;
import org.amber.dental.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenlinquan on 4/25/18.
 */
@Repository("appointmentRepository")
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
    List<Appointment> findAllByDoctorProfileOrderByDateDesc(DoctorProfile doctorProfile);
    List<Appointment> findAllByUserOrderByDateDesc(User user);
    Appointment findById(int id);
}
