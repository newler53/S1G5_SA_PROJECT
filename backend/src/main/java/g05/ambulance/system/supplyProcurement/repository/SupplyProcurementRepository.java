package g05.ambulance.system.supplyProcurement.repository;

import g05.ambulance.system.supplyProcurement.entity.SupplyProcurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SupplyProcurementRepository extends JpaRepository<SupplyProcurement, Long> {}