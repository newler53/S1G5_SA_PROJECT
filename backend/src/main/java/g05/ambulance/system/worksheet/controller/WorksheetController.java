package g05.ambulance.system.worksheet.controller;

import g05.ambulance.system.entity.*;
import g05.ambulance.system.repository.*;
import g05.ambulance.system.worksheet.entity.*;
import g05.ambulance.system.worksheet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.Collection;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/Worksheet")
@RestController
public class WorksheetController {
    @Autowired
    private WorksheetRepository worksheetRepository;
    @Autowired
    private employeeRepository employeeRepository;
    @Autowired
    private amVehicleRepository amVehicleRepository;
    @Autowired
    private Start_Getoff_WorkRepository Start_Getoff_WorkRepository;

    WorksheetController(WorksheetRepository WorksheetRepository) {
        this.worksheetRepository = WorksheetRepository;
    }

    @GetMapping("/")
    public Collection<Worksheet> Worksheet() {
        return worksheetRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/{employee_id}/{amvehicle_id}/{chooseStartGetoff_id}")
    public Worksheet newWorksheet(Worksheet newWorksheet, @PathVariable long employee_id,
            @PathVariable long amvehicle_id, @PathVariable long chooseStartGetoff_id) {

        Employee createdBy = employeeRepository.findById(employee_id);
        Am_Vehicle vehicle = amVehicleRepository.findById(amvehicle_id);
        Start_Getoff_Work start_Getoff_Work = Start_Getoff_WorkRepository.findById(chooseStartGetoff_id);
        
        Date time = new Date();

        newWorksheet.setCreatedBy(createdBy);
        newWorksheet.setVehicle(vehicle);
        newWorksheet.setStart_Getoff_Work(start_Getoff_Work);
        newWorksheet.setTime(time);


        return worksheetRepository.save(newWorksheet);

    }



}
