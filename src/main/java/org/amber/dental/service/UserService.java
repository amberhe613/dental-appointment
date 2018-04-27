package org.amber.dental.service;

import org.amber.dental.model.User;

/**
 * Created by zhuoqihe on 4/15/18.
 */
public interface UserService {
    public User findUserByEmail(String email);
    public User findById(int id);
    public void saveUser(User user);
}
