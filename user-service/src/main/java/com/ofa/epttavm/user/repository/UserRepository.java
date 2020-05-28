package com.ofa.epttavm.user.repository;


import com.ofa.epttavm.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(final String username);
    User findByEmail(final String email);
    List<User> findAll();
}
