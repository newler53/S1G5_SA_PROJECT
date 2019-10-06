package g05.ambulance.system.ambulancePurchase.repository;
import g05.ambulance.system.ambulancePurchase.entity.Am_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface amProductRepository extends JpaRepository<Am_Product, Long> {
    Am_Product findById(long id);
}
