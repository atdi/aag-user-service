package com.github.atdi.user.services.repositories;

import com.github.atdi.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by aurelavramescu on 11/05/16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
