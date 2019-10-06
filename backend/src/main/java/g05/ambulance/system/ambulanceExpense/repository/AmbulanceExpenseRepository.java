package g05.ambulance.system.ambulanceExpense.repository;

import g05.ambulance.system.ambulanceExpense.entity.AmbulanceExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AmbulanceExpenseRepository extends JpaRepository<AmbulanceExpense, Long> {
    AmbulanceExpense findById(long id);
}
