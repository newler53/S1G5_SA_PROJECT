package g05.ambulance.system.repository;

import g05.ambulance.system.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface employeeRepository extends JpaRepository<Employee, Long> {
    Employee findById(long id);
}