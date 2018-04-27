package org.amber.dental.repository;

import org.amber.dental.model.DoctorProfile;
import org.amber.dental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 * Created by zhuoqihe on 4/15/18.
 */

@Repository("dentistRepository")
public interface DentistRepository extends JpaRepository<DoctorProfile, Integer>{
    DoctorProfile findByUser(User user);
    DoctorProfile findById(int id);
    List<DoctorProfile> findAll();
}