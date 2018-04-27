package org.amber.dental.repository;

import org.amber.dental.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.amber.dental.model.Role;
/**
 * Created by zhuoqihe on 4/15/18.
 */

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByRole(String role);
}
