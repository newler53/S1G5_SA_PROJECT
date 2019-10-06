package g05.ambulance.system.controller;
import g05.ambulance.system.entity.EmpPosition;
import g05.ambulance.system.entity.Employee;
import g05.ambulance.system.repository.employeeRepository;
import g05.ambulance.system.repository.checkLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/chkLogin")
@RestController

public class CheckLoginController {
    @Autowired
    employeeRepository empRepository;
    @Autowired
    checkLoginRepository chkRepository;

    @PostMapping("/{id}/{password}")
    public Employee newChkLogin(@PathVariable long id, @PathVariable String password){
        Employee uid = empRepository.findById(id);
        EmpPosition chkLogin = chkRepository.findByUserID(id);
        if(!uid.getPassword().equals(password)) {
            uid = null;
        }

        return uid;
    }
}
