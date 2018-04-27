package org.amber.dental.service;

import org.amber.dental.model.DoctorProfile;
import org.amber.dental.model.User;
import java.util.List;

/**
 * Created by chenlinquan on 4/24/18.
 */
public interface DentistService {
    public DoctorProfile findDentistByUser(User user);
    public DoctorProfile findDentistById(int id);
    public List<DoctorProfile> findAllDentist();
    public void saveDentist(DoctorProfile dentist, User user);
}
