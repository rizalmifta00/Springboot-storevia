package com.learn.store.Repository;

import com.learn.store.Models.ERole;
import com.learn.store.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByname(ERole name);
}
