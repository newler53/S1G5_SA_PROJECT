package g05.ambulance.system.ambulancePurchase.controller;
import g05.ambulance.system.ambulancePurchase.entity.Company;
import g05.ambulance.system.ambulancePurchase.repository.companyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CompanyController {

    @Autowired
    private final companyRepository companyRepository;

    public CompanyController(companyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/company/")
    public Collection<Company> Company() {
        return companyRepository.findAll().stream().collect(Collectors.toList());
    }


}
