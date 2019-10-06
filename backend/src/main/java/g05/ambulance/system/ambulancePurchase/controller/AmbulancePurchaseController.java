package g05.ambulance.system.ambulancePurchase.controller;
import g05.ambulance.system.entity.*;
import g05.ambulance.system.repository.*;
import g05.ambulance.system.ambulancePurchase.entity.*;
import g05.ambulance.system.ambulancePurchase.repository.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AmbulancePurchaseController{

    @Autowired
    private final ambulancePurchaseRepository ambulancePurchaseRepository;
    @Autowired
    private employeeRepository employeeRepository;
    @Autowired
    private amVehicleRepository amVehicleRepository;
    @Autowired
    private amProductRepository amProductRepository;

    AmbulancePurchaseController(ambulancePurchaseRepository ambulancePurchaseRepository,
                                    employeeRepository employeeRepository,
                                        amVehicleRepository amVehicleRepository,
                                            amProductRepository amProductRepository) {
        this.ambulancePurchaseRepository = ambulancePurchaseRepository;
        this.employeeRepository = employeeRepository;
        this.amVehicleRepository = amVehicleRepository;
        this.amProductRepository = amProductRepository;
    }

    @GetMapping("/ambulancePurchase/")
    public Collection<AmbulancePurchase> AmbulancePurchase() {
        return ambulancePurchaseRepository.findAll().stream().collect(Collectors.toList());
    }


    @PostMapping("/newAm_vehicle/{am_code}")
      public Am_Vehicle AddAm_Vehicle(Am_Vehicle newAm_Vehicle,@PathVariable String am_code){
          newAm_Vehicle.setAm_Code(am_code);


      return amVehicleRepository.save(newAm_Vehicle);

    }
    
    @PostMapping("/ambulancePurchase/{ap_date}/{ap_vin}/{employee_id}/{am_vehicle_id}/{am_product_id}")
    public AmbulancePurchase AddAmbulancePurchase(AmbulancePurchase newAmbulancePurchase,
                                            @PathVariable String ap_date,
                                            @PathVariable String ap_vin,
                                            @PathVariable long employee_id,
                                            @PathVariable long am_vehicle_id,
                                            @PathVariable long am_product_id){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date dateFormat = new Date();
        try {
            dateFormat = formatter.parse((String)ap_date);

        } catch (Exception e) {}

        Employee createdBy = employeeRepository.findById(employee_id);
        Am_Vehicle am_vehicle = amVehicleRepository.findById(am_vehicle_id);
        Am_Product am_product = amProductRepository.findById(am_product_id);

        newAmbulancePurchase.setDate(dateFormat);
        newAmbulancePurchase.setVin(ap_vin);
        newAmbulancePurchase.setCreatedBy(createdBy);
        newAmbulancePurchase.setAmbulabceCode(am_vehicle);
        newAmbulancePurchase.setAmbulabceBuy(am_product);

        return ambulancePurchaseRepository.save(newAmbulancePurchase);
    }
}
