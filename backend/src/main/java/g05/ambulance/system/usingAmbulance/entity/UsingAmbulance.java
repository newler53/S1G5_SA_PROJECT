package g05.ambulance.system.usingAmbulance.entity;

import g05.ambulance.system.entity.Am_Vehicle;
import g05.ambulance.system.entity.Employee;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

import com.fasterxml.jackson.annotation.*;

import g05.ambulance.system.ambulanceExpense.entity.AmbulanceExpense;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name="SUPPLY")
public class UsingAmbulance {
    @Id
    @SequenceGenerator(name="USE_SEQ",sequenceName="USE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USE_SEQ")
    @Column(name="USE_ID",unique = true, nullable = true)
    private @NonNull Long id;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private @NonNull Date usingDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee createdBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Am_Vehicle.class)
    @JoinColumn(name = "AMVEHICLE_ID", insertable = true)
    private Am_Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = UsingType.class)
    @JoinColumn(name = "UTYPE_ID", insertable = true)
    private UsingType usingType;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<AmbulanceExpense> ambulanceExpense;
}
