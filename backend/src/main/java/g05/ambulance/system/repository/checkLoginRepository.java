package g05.ambulance.system.repository;
import g05.ambulance.system.entity.EmpPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface checkLoginRepository extends JpaRepository<EmpPosition, Long> {
    @Query( value = "SELECT * FROM EMPPOSITION as a INNER JOIN  EMPLOYEE   as  p on a.POSITION_ID  = p.POSITION_ID   where p.EMPLOYEE_ID  = :uid",
            nativeQuery = true)
    EmpPosition findByUserID(@Param("uid") long uid);
    EmpPosition findById(long id);
}
