package org.amber.dental.service;

import org.amber.dental.model.InsuranceCompany;
import org.amber.dental.model.Role;
import org.amber.dental.model.User;
import org.amber.dental.repository.InsuranceRepository;
import org.amber.dental.repository.RoleRepository;
import org.amber.dental.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenlinquan on 4/24/18.
 */
@Service("insuranceService")
public class InsuranceServiceImpl implements InsuranceService{
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public InsuranceCompany findInsuranceCompanyByUser(User user) {
        return insuranceRepository.findByUser(user);
    }

    @Override
    public void saveInsuranceCompany(InsuranceCompany insuranceCompany, User user) {
        Role userRole = roleRepository.findByRole("INSURANCECOMPANY");
        user.getRoles().add(userRole);
        user.setInsuranceCompany(insuranceCompany);
        insuranceCompany.setUser(user);
        userRepository.save(user);
    }
}
