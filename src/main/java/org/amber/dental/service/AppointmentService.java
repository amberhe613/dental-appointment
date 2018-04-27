package org.amber.dental.service;

import org.amber.dental.model.DoctorProfile;
import org.amber.dental.model.User;
import org.amber.dental.model.Appointment;
import java.util.List;

/**
 * Created by chenlinquan on 4/25/18.
 */
public interface AppointmentService {
    public Appointment findAppointmentById (int id);
    public List<Appointment> findAppointmentsByUser (User user);
    public List<Appointment> findAppointmentsByDentist (DoctorProfile dentist);
    public void saveAppointment(Appointment appointment, DoctorProfile dentist, User user);
    public void updateAppointment(Appointment appointment, String price);
}
