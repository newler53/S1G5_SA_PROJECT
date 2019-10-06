package g05.ambulance.system.usingAmbulance.controller;
import g05.ambulance.system.usingAmbulance.entity.UsingType;
import g05.ambulance.system.usingAmbulance.repository.usingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/usingAmbulance")
@RestController
public class UsingTypeController {
    @Autowired
    usingRepository repository;

    @GetMapping("/usingType")
    public List<UsingType> getAllUsingType() {
        System.out.println("Get all getAll PurchaseType...");

        List<UsingType> usingTypes = new ArrayList<>();
        repository.findAll().forEach(usingTypes::add);

        return usingTypes;
    }
}
