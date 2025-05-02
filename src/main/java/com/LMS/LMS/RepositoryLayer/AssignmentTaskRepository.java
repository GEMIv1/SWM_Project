    package com.LMS.LMS.RepositoryLayer;

    import com.LMS.LMS.ModelLayer.AssignmentTask;
    import com.LMS.LMS.ModelLayer.Assignment;
    import org.springframework.data.jpa.repository.JpaRepository;
    import java.util.List;

    public interface AssignmentTaskRepository extends JpaRepository<AssignmentTask, Long> {
        List<AssignmentTask> findByAssignment(Assignment assignment);
    }