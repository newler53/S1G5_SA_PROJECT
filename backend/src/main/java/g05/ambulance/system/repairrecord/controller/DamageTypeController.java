package g05.ambulance.system.repairrecord.controller;
import g05.ambulance.system.repairrecord.entity.DamageType;
import g05.ambulance.system.repairrecord.repository.DamageTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DamageTypeController {

    @Autowired
    private final DamageTypeRepository damageTypeRepository;

    public DamageTypeController(DamageTypeRepository damageTypeRepository) {
        this.damageTypeRepository = damageTypeRepository;
    }

    @GetMapping("/damageType")
    public Collection<DamageType> DamageType() {
        return damageTypeRepository.findAll().stream().collect(Collectors.toList());
    }

}