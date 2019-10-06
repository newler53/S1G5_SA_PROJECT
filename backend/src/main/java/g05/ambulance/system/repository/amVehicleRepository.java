package g05.ambulance.system.repository;
import g05.ambulance.system.entity.Am_Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface amVehicleRepository extends JpaRepository<Am_Vehicle, Long> {
    Am_Vehicle findById(long id);
}
