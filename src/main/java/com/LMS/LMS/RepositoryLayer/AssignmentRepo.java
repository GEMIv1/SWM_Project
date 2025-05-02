package com.LMS.LMS.RepositoryLayer;

import com.LMS.LMS.ModelLayer.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepo extends JpaRepository<Assignment, Long> {
}