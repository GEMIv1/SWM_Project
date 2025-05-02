package com.LMS.LMS.RepositoryLayer;

import com.LMS.LMS.ModelLayer.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.Email = :Email")
    Optional<User> findByEmail(String Email);
}