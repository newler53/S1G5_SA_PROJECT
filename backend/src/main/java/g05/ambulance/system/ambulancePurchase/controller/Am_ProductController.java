package g05.ambulance.system.ambulancePurchase.controller;
import g05.ambulance.system.ambulancePurchase.entity.Am_Product;
import g05.ambulance.system.ambulancePurchase.repository.amProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class Am_ProductController {

    @Autowired
    private final amProductRepository amProductRepository;

    public Am_ProductController(amProductRepository amProductRepository) {
        this.amProductRepository = amProductRepository;
    }

    @GetMapping("/am_product/")
    public Collection<Am_Product> Am_Product() {
        return amProductRepository.findAll().stream().collect(Collectors.toList());
    }

}


