package g05.ambulance.system.ambulanceExpense.controller;
import g05.ambulance.system.ambulanceExpense.entity.*;
import g05.ambulance.system.ambulanceExpense.repository.*;
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


import g05.ambulance.system.usingAmbulance.entity.UsingAmbulance;
import g05.ambulance.system.usingAmbulance.repository.UsingAmbulanceRepository;

import g05.ambulance.system.entity.*;
import g05.ambulance.system.repository.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class AmbulanceExpenseController{

    @Autowired
    private final AmbulanceExpenseRepository ambulanceExpenseRepository;
    @Autowired
    private employeeRepository employeeRepository;
    @Autowired
    private ExpenseTypeRepository expenseTypeRepository;
    @Autowired
    private UsingAmbulanceRepository usingAmbulanceRepository;

    AmbulanceExpenseController(AmbulanceExpenseRepository ambulanceExpenseRepository,
                                employeeRepository employeeRepository,
                                ExpenseTypeRepository expenseTypeRepository,
                                UsingAmbulanceRepository usingAmbulanceRepository) {
        this.ambulanceExpenseRepository = ambulanceExpenseRepository;
        this.employeeRepository = employeeRepository;
        this.expenseTypeRepository = expenseTypeRepository;

        this.usingAmbulanceRepository = usingAmbulanceRepository;
    }

    @GetMapping("/ambulanceExpense")
    public Collection<AmbulanceExpense> AmbulanceExpense() {
        return ambulanceExpenseRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/ambulanceExpense/{budget}/{note}/{employee_id}/{expenseType_id}/{usingAmbulance_id}")
    public AmbulanceExpense SaveAmbulanceExpense(AmbulanceExpense newAmbulanceExpense,
                                            @PathVariable Double budget,
                                            @PathVariable String note,
                                            @PathVariable long employee_id,
                                            @PathVariable long expenseType_id,
                                            @PathVariable long usingAmbulance_id
                                            ){

        Employee employee = employeeRepository.findById(employee_id);
        ExpenseType expenseType = expenseTypeRepository.findById(expenseType_id);
        UsingAmbulance usingAmbulance = usingAmbulanceRepository.findById(usingAmbulance_id);

        newAmbulanceExpense.setBudget((Double)budget);
        newAmbulanceExpense.setNote((String)note);
        newAmbulanceExpense.setEmployee(employee);
        newAmbulanceExpense.setExpenseType(expenseType);
        newAmbulanceExpense.setUsing(usingAmbulance);

        return ambulanceExpenseRepository.save(newAmbulanceExpense);
    }
}
