package g05.ambulance.system.worksheet.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import g05.ambulance.system.worksheet.entity.*;
import g05.ambulance.system.worksheet.repository.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/StartGetoffWork")
@RestController
public class Start_Getoff_WorkController {
    @Autowired
    Start_Getoff_WorkRepository repository;

    @GetMapping("/")
    public List<Start_Getoff_Work> getAllStart_Getoff_Work() {
        List<Start_Getoff_Work> StartGetoffWork = new ArrayList<>();
        repository.findAll().forEach(StartGetoffWork::add);

        return StartGetoffWork;
    }
}
