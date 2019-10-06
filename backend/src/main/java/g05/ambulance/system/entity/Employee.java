package g05.ambulance.system.entity;

import g05.ambulance.system.supplyProcurement.entity.SupplyProcurement;
import g05.ambulance.system.worksheet.entity.Worksheet;
import g05.ambulance.system.usingAmbulance.entity.UsingAmbulance;
import g05.ambulance.system.ambulanceExpense.entity.AmbulanceExpense;
import g05.ambulance.system.repairrecord.entity.RepairRecord;
import g05.ambulance.system.ambulancePurchase.entity.AmbulancePurchase;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name="EMPLOYEE")
public class Employee {

    @Id
    @SequenceGenerator(name="EMPLOYEE_SEQ",sequenceName="EMPLOYEE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EMPLOYEE_SEQ")
    @Column(name="EMPLOYEE_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String name;
    private @NonNull String password;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = EmpPosition.class)
    @JoinColumn(name = "POSITION_ID", insertable = true)
    private EmpPosition position;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<SupplyProcurement> purchase;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Worksheet> worksheet;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<UsingAmbulance> using;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<AmbulanceExpense> ambulanceExpense;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<RepairRecord> RepairRecord;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<AmbulancePurchase> am_purchase;

}