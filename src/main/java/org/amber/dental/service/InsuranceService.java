package org.amber.dental.service;

import org.amber.dental.model.InsuranceCompany;
import org.amber.dental.model.User;
/**
 * Created by chenlinquan on 4/24/18.
 */
public interface InsuranceService {
    public InsuranceCompany findInsuranceCompanyByUser(User user);
    public void saveInsuranceCompany(InsuranceCompany insuranceCompany, User user);
}
