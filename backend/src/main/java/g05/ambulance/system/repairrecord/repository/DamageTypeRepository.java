package g05.ambulance.system.repairrecord.repository;

import g05.ambulance.system.repairrecord.entity.DamageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DamageTypeRepository extends JpaRepository<DamageType, Long> {
    DamageType findById(long id);
}