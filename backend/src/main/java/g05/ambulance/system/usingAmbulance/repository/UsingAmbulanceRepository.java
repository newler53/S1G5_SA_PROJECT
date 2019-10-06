package g05.ambulance.system.usingAmbulance.repository;

import g05.ambulance.system.usingAmbulance.entity.UsingAmbulance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UsingAmbulanceRepository extends JpaRepository<UsingAmbulance, Long> {
    UsingAmbulance findById(long id);
}