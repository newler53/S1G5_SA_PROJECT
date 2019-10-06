package g05.ambulance.system.supplyProcurement.controller;
import g05.ambulance.system.supplyProcurement.entity.Product;
import g05.ambulance.system.supplyProcurement.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/supplyProcurement")
@RestController
public class ProductController {
    @Autowired
    productRepository repository;

    @GetMapping("/products/{id}")
    public List<Product> getAllProduct(@PathVariable long id) {
        List<Product> products = new ArrayList<>();
        repository.findByPurchaseType(id).forEach(products::add);
        return products;
    }
}
