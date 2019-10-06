package g05.ambulance.system.ambulanceExpense.repository;

import g05.ambulance.system.ambulanceExpense.entity.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {
    ExpenseType findById(long id);
}