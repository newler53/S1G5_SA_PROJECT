package g05.ambulance.system.repairrecord.repository;
import g05.ambulance.system.repairrecord.entity.RepairRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RepairRecordRepository extends JpaRepository<RepairRecord, Long> {
    RepairRecord findById(long id);
}
