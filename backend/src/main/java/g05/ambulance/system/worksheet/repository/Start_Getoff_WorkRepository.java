package g05.ambulance.system.worksheet.repository;
import g05.ambulance.system.worksheet.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface Start_Getoff_WorkRepository extends JpaRepository<Start_Getoff_Work, Long> {
    Start_Getoff_Work findById(long id);
}