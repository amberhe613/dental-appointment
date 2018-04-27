package org.amber.dental.service;

import org.amber.dental.model.DoctorProfile;
import org.amber.dental.model.Role;
import org.amber.dental.model.User;
import org.amber.dental.repository.RoleRepository;
import org.amber.dental.repository.UserRepository;
import org.amber.dental.repository.DentistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenlinquan on 4/24/18.
 */
@Service("dentistService")
public class DentistServiceImpl implements DentistService{

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DentistRepository dentistRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public DoctorProfile findDentistByUser(User user) {
        return dentistRepository.findByUser(user);
    }

    @Override
    public List<DoctorProfile> findAllDentist() {
        return dentistRepository.findAll();
    }

    @Override
    public DoctorProfile findDentistById(int id) {
        return dentistRepository.findById(id);
    }

    @Override
    public void saveDentist(DoctorProfile dentist, User user) {
        Role userRole = roleRepository.findByRole("DENTIST");
        user.getRoles().add(userRole);
        user.setDoctorProfile(dentist);
        dentist.setUser(user);
        userRepository.save(user);
    }
}
