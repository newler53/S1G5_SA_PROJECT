package g05.ambulance.system.supplyProcurement.controller;

import g05.ambulance.system.entity.Am_Vehicle;
import g05.ambulance.system.entity.Employee;
import g05.ambulance.system.supplyProcurement.entity.*;
import g05.ambulance.system.supplyProcurement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/supplyProcurement")
@RestController
public class SupplyProcurementController{
    @Autowired
    private SupplyProcurementRepository supplyProcurementRepository;
    @Autowired
    private g05.ambulance.system.repository.employeeRepository employeeRepository;
    @Autowired
    private g05.ambulance.system.repository.amVehicleRepository amVehicleRepository;
    @Autowired
    private productRepository productRepository;

    SupplyProcurementController(SupplyProcurementRepository supplyProcurementRepository) {
        this.supplyProcurementRepository = supplyProcurementRepository;
    }

    @GetMapping("/")
    public Collection<SupplyProcurement> supplyProcurement() {
        return supplyProcurementRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/{employee_id}/{amvehicle_id}/{p_id}/{pdate}")
    public SupplyProcurement newSupplyProcurement(SupplyProcurement newSupplyProcurement,
                                            @PathVariable long employee_id,
                                            @PathVariable long amvehicle_id,
                                            @PathVariable long p_id,
                                            @PathVariable String pdate){

        Employee createdBy = employeeRepository.findById(employee_id);
        Am_Vehicle vehicle = amVehicleRepository.findById(amvehicle_id);
        Product product = productRepository.findById(p_id);

        String [] pdate_split =  pdate.split("@");
        String pdate_s = ""+pdate_split[0]+"/"+pdate_split[1]+"/"+pdate_split[2];
        Date purchaseDate = null;
        try {
            purchaseDate = new SimpleDateFormat("dd/MM/yyyy").parse(pdate_s);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        newSupplyProcurement.setCreatedBy(createdBy);
        newSupplyProcurement.setVehicle(vehicle);
        newSupplyProcurement.setProduct(product);
        newSupplyProcurement.setPurchaseDate(purchaseDate);

        return supplyProcurementRepository.save(newSupplyProcurement);

    }



}
