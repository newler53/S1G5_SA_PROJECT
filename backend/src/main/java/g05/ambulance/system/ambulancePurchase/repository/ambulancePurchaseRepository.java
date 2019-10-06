package g05.ambulance.system.ambulancePurchase.repository;
import g05.ambulance.system.ambulancePurchase.entity.AmbulancePurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ambulancePurchaseRepository extends JpaRepository<AmbulancePurchase, Long> {
    AmbulancePurchase findById(long id);
}