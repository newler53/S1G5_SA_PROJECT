package g05.ambulance.system;

import g05.ambulance.system.entity.*;
import g05.ambulance.system.repository.*;

import g05.ambulance.system.supplyProcurement.entity.*;
import g05.ambulance.system.supplyProcurement.repository.*;

import g05.ambulance.system.worksheet.entity.*;
import g05.ambulance.system.worksheet.repository.*;

import g05.ambulance.system.usingAmbulance.entity.*;
import g05.ambulance.system.usingAmbulance.repository.*;

import g05.ambulance.system.ambulanceExpense.entity.*;
import g05.ambulance.system.ambulanceExpense.repository.*;

import g05.ambulance.system.repairrecord.entity.*;
import g05.ambulance.system.repairrecord.repository.*;

import g05.ambulance.system.ambulancePurchase.entity.*;
import g05.ambulance.system.ambulancePurchase.repository.*;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;
import java.util.stream.Stream;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class SpringBootVueApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootVueApplication.class, args);
	}
	int pp = 0;
    @Bean
    ApplicationRunner init(checkLoginRepository checkLoginRepository, //main Repository 
                           employeeRepository employeeRepository,
                           amVehicleRepository amVehicleRepository,
                           purchaseRepository purchaseRepository,  //Repository  supplyProcurementSystem 
                           productRepository productRepository,
                           Start_Getoff_WorkRepository start_Getoff_WorkRepository, //Repository worksheetSystem 
                           usingRepository usingRepository,  //Repository usingAmbulanceSystem 
                           UsingAmbulanceRepository usingAmbulanceRepository,  //Repository ambulanceExpenseSystem 
                           ExpenseTypeRepository expenseTypeRepository,
                           DamageTypeRepository damageTypeRepository, //Repository RepairRecordExpenseSystem 
                           companyRepository companyRepository,  //Repository  ambulancePurchaseSystem 
                           amProductRepository amProductRepository,
                           ambulancePurchaseRepository ambulancePurchaseRepository
                           ) {

        return args -> {

//set data main
            String initialEmployee[][][] =
                    {
                            {
                                    {"Damrong Khruaesri","123456789"},
                                    {"Natthawat Narin","123456789"}
                            },
                            {
                                    {"Chatdanai Phakaket","123456789"},
                                    {"Pattarasit Lomthaisong","123456789"}
                            },
                            {
                                    {"Pairat Tonkean","123456789"},
                                    {"Chiwin Siriphan","123456789"}
                            }
                    };

            String[] position = {"หัวหน้า ER","เจ้าหน้าที่ ER","คนขับรถ"};

            for(int i=0;i<position.length;i++){

                EmpPosition chk = new EmpPosition();
                chk.setPosition(position[i]);
                checkLoginRepository.save(chk);

                    for(String obj[] : initialEmployee[i]){
                        Employee e = new Employee();
                        e.setName(obj[0]);
                        e.setPassword(obj[1]);
                        e.setPosition(chk);
                        employeeRepository.save(e);
                    }

            }
            
            Stream.of("AM001","AM002","AM003","AM004").forEach(name -> {
                Am_Vehicle amp = new Am_Vehicle();
                amp.setAm_Code(name);
                amVehicleRepository.save(amp);
            });

//set data supplyProcurementSystem  
            String initialProduct[][] = {
                    {"เครื่องช่วยหายใจอัตโนมัติ","เครื่องกระตุกหัวใจด้วยไฟฟ้า"},
                    {"ผ้าก๊อต","ชุดล็อคศรีษะ","ชุดช่วยหายใจด้วยมือบีบ","First Aid"
                            , "ชุดส่องคอช่วยหายใจ","ชุดป้องกันกระดูกคอเลื่อน"
                            ,"เปลสนาม"},

            };

            Stream.of("เช่าซื้อ","ซื้อขาด").forEach(name -> {
                PurchaseType emp = new PurchaseType();
                emp.setType(name);
                purchaseRepository.save(emp);

                for(String item : initialProduct[pp]){
                    Product p = new Product();
                    p.setName(item);
                    p.setPurchaseType(emp);
                    productRepository.save(p);
                }

                pp++;
            });

//set data worksheetSystem 
            Stream.of("เข้างาน","ออกงาน").forEach(cgw -> {
                Start_Getoff_Work sgw = new Start_Getoff_Work();
                sgw.setChooseStartGetoff(cgw);
                start_Getoff_WorkRepository.save(sgw);
            });

            Stream.of("Emergency","Transfer").forEach(name -> {
                UsingType emp = new UsingType();
                emp.setType(name);
                usingRepository.save(emp);
            });
            
//set data UsingAmbulanceSystem 
            Object[][] dataUsingAmbulance = new Object[][]{
                {"2011-07-04",1,1,1},
                {"2013-11-22",1,2,1},
                {"2018-06-18",2,1,2},
                {"2017-03-30",3,1,2}
              };
    
              for(int i=0;i<dataUsingAmbulance.length;i++){
                UsingAmbulance usingAmbulance = new UsingAmbulance();
    
    
    
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date usingDate = new Date();
                try {
                    usingDate = formatter.parse((String)dataUsingAmbulance[i][0]);
    
                } catch (Exception e) {}
    
                Employee createdBy = employeeRepository.findById((int)dataUsingAmbulance[i][1]);
                Am_Vehicle vehicle = amVehicleRepository.findById((int)dataUsingAmbulance[i][2]);
                UsingType usingType = usingRepository.findById((int)dataUsingAmbulance[i][3]);
    
                usingAmbulance.setUsingDate(usingDate);
                usingAmbulance.setCreatedBy(createdBy);
                usingAmbulance.setVehicle(vehicle);
                usingAmbulance.setUsingType(usingType);
    
    
                usingAmbulanceRepository.save(usingAmbulance);
              }

//set data ambulanceExpenseSystem 
              Stream.of("ค่าซ่อมบำรุงอุปกรณ์ครุภัณฑ์", "ค่าเดินทาง","อื่นๆ *เพิ่มข้อมูลใน note").forEach(name -> {
                ExpenseType et = new ExpenseType();
                et.setType((String)name);
                expenseTypeRepository.save(et);
              });

//set data RepairRecordExpenseSystem 
              Stream.of("เสียหายฝั้งขวา", "เสียหายฝั่งซ้าย", "เสียหายภายใน","ไม่ได้เสียหาย(ซ่อมบำรุงตรวจเช็คสภาพ)").forEach(name -> {
                DamageType et = new DamageType();
                et.setType((String)name);
                damageTypeRepository.save(et);
             });

//set data ambulancePurchaseSystem 
            Stream.of("TOYOTA", "FORD").forEach(dataName -> {
                Company company = new Company();
                company.setName(dataName);
                companyRepository.save(company);
            });

            Object[][] dataAm_Product = new Object[][] {
                { "Toyota Hiace", 1000000.00, 1 },
                { "Toyota Hiace High Roof", 1500000.00, 1 },
                { "Ford Transit V348", 1700000.00, 2 },
                { "Ford Transit V362", 1200000.00, 2 }
            };

            for (int i = 0; i < dataAm_Product.length; i++) {
                Am_Product am_product1 = new Am_Product();
                am_product1.setName((String) dataAm_Product[i][0]);
                am_product1.setPrice((double) dataAm_Product[i][1]);

                Company company = companyRepository.findById((int) dataAm_Product[i][2]);
                am_product1.setCompany(company);

                amProductRepository.save(am_product1);

            }

            Object[][] dataAmbulancePurchase = new Object[][] {
                {"2019-07-15","MR053HY9305270928",1,1,1,},
                {"2017-09-25","SR053HY9334242305",2,2,2,},
                {"2015-11-30","TR053HY9309660922",3,3,4,},
                {"2018-02-01","VR053HY9352704927",3,4,4,},

            };

            for (int i = 0; i < dataAmbulancePurchase.length; i++){
                AmbulancePurchase am_purchase = new AmbulancePurchase();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date dateFormat = new Date();
                try {
                    dateFormat = formatter.parse((String)dataAmbulancePurchase[i][0]);

                } catch (Exception e) {}

                am_purchase.setDate(dateFormat);
                am_purchase.setVin((String) dataAmbulancePurchase[i][1]);

                Employee employee = employeeRepository.findById((int) dataAmbulancePurchase[i][2]);
                am_purchase.setCreatedBy(employee);

                Am_Vehicle am_vehicle = amVehicleRepository.findById((int) dataAmbulancePurchase[i][3]);
                am_purchase.setAmbulabceCode(am_vehicle);

                Am_Product am_product2 = amProductRepository.findById((int) dataAmbulancePurchase[i][4]);
                am_purchase.setAmbulabceBuy(am_product2);

                ambulancePurchaseRepository.save(am_purchase);
            }


        };

    }


}
