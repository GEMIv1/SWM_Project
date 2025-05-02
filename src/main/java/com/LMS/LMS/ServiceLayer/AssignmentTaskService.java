    package com.LMS.LMS.ServiceLayer;

    import com.LMS.LMS.ModelLayer.AssignmentTask;
    import com.LMS.LMS.ModelLayer.Assignment;
    import com.LMS.LMS.RepositoryLayer.AssignmentTaskRepository;
    import jakarta.persistence.EntityNotFoundException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class AssignmentTaskService {

        private final AssignmentTaskRepository taskRepository;

        @Autowired
        public AssignmentTaskService(AssignmentTaskRepository taskRepository) {
            this.taskRepository = taskRepository;
        }

        public AssignmentTask createTask(AssignmentTask task) {
            if (task == null) {
                throw new IllegalArgumentException("Task cannot be null");
            }
            return taskRepository.save(task);
        }

        public List<AssignmentTask> getAssignmentTasks(Assignment assignment) {
            if (assignment == null) {
                throw new IllegalArgumentException("Assignment cannot be null");
            }
            return taskRepository.findByAssignment(assignment);
        }

        public Optional<AssignmentTask> getTaskById(Long id) {
            if (id == null) {
                throw new IllegalArgumentException("Task ID cannot be null");
            }
            return taskRepository.findById(id);
        }

        public void deleteTask(Long id) {
            if (id == null) {
                throw new IllegalArgumentException("Task ID cannot be null");
            }
            if (!taskRepository.existsById(id)) {
                throw new EntityNotFoundException("Task not found with id: " + id);
            }
            taskRepository.deleteById(id);
        }

        public AssignmentTask updateTask(Long id, AssignmentTask updatedTask) {
            if (id == null || updatedTask == null) {
                throw new IllegalArgumentException("Task ID and updated task cannot be null");
            }

            AssignmentTask existingTask = taskRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));

            // Update the existing task properties
            existingTask.setTaskDescription(updatedTask.getTaskDescription());
            existingTask.setExpectedOutput(updatedTask.getExpectedOutput());
            existingTask.setPoints(updatedTask.getPoints());
            existingTask.setTaskType(updatedTask.getTaskType());
            existingTask.setAdditionalResources(updatedTask.getAdditionalResources());

            return taskRepository.save(existingTask);
        }
    }