package org.amber.dental.service;

import org.amber.dental.model.DoctorProfile;
import org.amber.dental.model.Role;
import org.amber.dental.model.User;
import org.amber.dental.model.Appointment;
import org.amber.dental.repository.RoleRepository;
import org.amber.dental.repository.UserRepository;
import org.amber.dental.repository.DentistRepository;
import org.amber.dental.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Created by chenlinquan on 4/25/18.
 */
@Service("appointmentService")
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment findAppointmentById (int id) { return appointmentRepository.findById(id); }

    @Override
    public List<Appointment> findAppointmentsByUser (User user) { return appointmentRepository.findAllByUserOrderByDateDesc(user); }

    @Override
    public List<Appointment> findAppointmentsByDentist (DoctorProfile dentist) {
        return appointmentRepository.findAllByDoctorProfileOrderByDateDesc(dentist);
    }

    @Override
    public void saveAppointment(Appointment appointment, DoctorProfile dentist, User user) {
        appointment.setDoctorProfile(dentist);
        appointment.setUser(user);
        appointmentRepository.save(appointment);
    }

    @Override
    public void updateAppointment(Appointment appointment, String price) {
        appointment.setPrice(price);
        appointmentRepository.save(appointment);
    }
}
