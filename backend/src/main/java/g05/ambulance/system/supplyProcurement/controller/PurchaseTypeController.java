package g05.ambulance.system.supplyProcurement.controller;
import g05.ambulance.system.supplyProcurement.entity.PurchaseType;
import g05.ambulance.system.supplyProcurement.repository.purchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/supplyProcurement")
@RestController
public class PurchaseTypeController {
    @Autowired
    purchaseRepository repository;

    @GetMapping("/purchaseType")
    public List<PurchaseType> getAllPurchaseType() {
        List<PurchaseType> purchaseTypes = new ArrayList<>();
        repository.findAll().forEach(purchaseTypes::add);
        return purchaseTypes;
    }
}
