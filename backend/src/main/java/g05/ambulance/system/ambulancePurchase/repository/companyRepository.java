package g05.ambulance.system.ambulancePurchase.repository;
import g05.ambulance.system.ambulancePurchase.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface companyRepository extends JpaRepository<Company, Long> {
    Company findById(long id);
}