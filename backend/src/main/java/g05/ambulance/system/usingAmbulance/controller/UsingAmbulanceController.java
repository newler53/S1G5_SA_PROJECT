package g05.ambulance.system.usingAmbulance.controller;

import g05.ambulance.system.usingAmbulance.entity.*;
import g05.ambulance.system.usingAmbulance.repository.*;

import g05.ambulance.system.entity.*;
import g05.ambulance.system.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/usingAmbulance")
@RestController
public class UsingAmbulanceController{
    @Autowired
    private UsingAmbulanceRepository usingAmbulanceRepository;
    @Autowired
    private usingRepository usingRepository;
    @Autowired
    private employeeRepository employeeRepository;
    @Autowired
    private amVehicleRepository amVehicleRepository;

    UsingAmbulanceController(UsingAmbulanceRepository usingAmbulanceRepository) {
        this.usingAmbulanceRepository = usingAmbulanceRepository;
    }

    @GetMapping("/")
    public Collection<UsingAmbulance> usingAmbulance() {
        return usingAmbulanceRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/{employee_id}/{amvehicle_id}/{utype_id}")
    public UsingAmbulance newUsingAmbulance(UsingAmbulance newUsingAmbulance,
                                            @PathVariable long employee_id,
                                            @PathVariable long amvehicle_id,
                                            @PathVariable long utype_id){

        Employee createdBy = employeeRepository.findById(employee_id);
        Am_Vehicle vehicle = amVehicleRepository.findById(amvehicle_id);
        UsingType usingType = usingRepository.findById(utype_id);

        Date usingDate = new Date();
        
        newUsingAmbulance.setCreatedBy(createdBy);
        newUsingAmbulance.setVehicle(vehicle);
        newUsingAmbulance.setUsingType(usingType);
        newUsingAmbulance.setUsingDate(usingDate);

        return usingAmbulanceRepository.save(newUsingAmbulance);

    }



}
