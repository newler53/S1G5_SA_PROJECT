package g05.ambulance.system.supplyProcurement.repository;

import g05.ambulance.system.supplyProcurement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface productRepository extends JpaRepository<Product, Long> {
    Product findById(long id);

    @Query( value = "SELECT * FROM PRODUCT as a INNER JOIN  PTYPE   as  p on a.PTYPE_ID  = p.PTYPE_ID   where p.PTYPE_ID  = :pid",
            nativeQuery = true)
    List<Product> findByPurchaseType(@Param("pid") long pid);

}
