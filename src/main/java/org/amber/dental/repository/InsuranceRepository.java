package org.amber.dental.repository;

import org.amber.dental.model.InsuranceCompany;
import org.amber.dental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by chenlinquan on 4/24/18.
 */
@Repository("insuranceRepository")
public interface InsuranceRepository extends JpaRepository<InsuranceCompany, Integer>{
    InsuranceCompany findByUser(User user);
}
