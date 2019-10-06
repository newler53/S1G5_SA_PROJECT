package g05.ambulance.system.repairrecord.controller;
import g05.ambulance.system.repairrecord.entity.*;
import g05.ambulance.system.repairrecord.repository.*;
import g05.ambulance.system.entity.*;
import g05.ambulance.system.repository.*;
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
public class RepairRecordController{
    
    @Autowired
    private final RepairRecordRepository repairrecordRepository;
    @Autowired
    private employeeRepository employeeRepository;
    @Autowired
    private amVehicleRepository amVehicleRepository;
    @Autowired
    private DamageTypeRepository damageTypeRepository;

    RepairRecordController(RepairRecordRepository repairrecordRepository,
                            employeeRepository employeeRepository,
                            amVehicleRepository amVehicleRepository,
                            DamageTypeRepository damageTypeRepository) {
        this.repairrecordRepository = repairrecordRepository;
        this.employeeRepository = employeeRepository;
        this.amVehicleRepository = amVehicleRepository;
        this.damageTypeRepository = damageTypeRepository;
    }

    @GetMapping("/RepairRecord")
    public Collection<RepairRecord> RepairRecord() {
        return repairrecordRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/RepairRecord/{employee_id}/{vehicle_id}/{damage_id}/{time}")
    public RepairRecord SaveRepairRecord(RepairRecord newRepairRecord,
                                            @PathVariable long employee_id,
                                            @PathVariable long vehicle_id,
                                            @PathVariable long damage_id,
                                            @PathVariable long time                     
    ){
        
        Employee employee = employeeRepository.findById(employee_id);
        Am_Vehicle am_vehicle = amVehicleRepository.findById(vehicle_id);
        DamageType damageType = damageTypeRepository.findById(damage_id);


        newRepairRecord.setCreateBy(employee);
        newRepairRecord.setVehicle(am_vehicle);
        newRepairRecord.setDamageType(damageType);
        newRepairRecord.setRepairtime(time);
        
        return repairrecordRepository.save(newRepairRecord);
    }
}
