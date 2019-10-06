package g05.ambulance.system.controller;
import g05.ambulance.system.entity.Am_Vehicle;
import g05.ambulance.system.repository.amVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/vehicles")
@RestController
public class Am_VehicleController {
    @Autowired
    amVehicleRepository repository;

    @GetMapping("/")
    public List<Am_Vehicle> getAllAm_Vehicle() {
        List<Am_Vehicle> amVehicles = new ArrayList<>();
        repository.findAll().forEach(amVehicles::add);
        return amVehicles;
    }
}
