package g05.ambulance.system.supplyProcurement.repository;

import g05.ambulance.system.supplyProcurement.entity.PurchaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface purchaseRepository extends JpaRepository<PurchaseType, Long> {
    PurchaseType findById(long id);
}
