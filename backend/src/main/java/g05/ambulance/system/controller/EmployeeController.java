package g05.ambulance.system.controller;
import g05.ambulance.system.entity.Employee;
import g05.ambulance.system.repository.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/employees")
@RestController
public class EmployeeController {
    @Autowired
    employeeRepository repository;

    @GetMapping("/")
    public List<Employee> getAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        repository.findAll().forEach(employees::add);
        return employees;
    }
}

