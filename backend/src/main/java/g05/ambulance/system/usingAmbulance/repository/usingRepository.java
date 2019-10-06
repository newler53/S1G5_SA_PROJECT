package g05.ambulance.system.usingAmbulance.repository;

import g05.ambulance.system.usingAmbulance.entity.UsingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface usingRepository extends JpaRepository<UsingType, Long> {
    UsingType findById(long id);
}
