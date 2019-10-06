package g05.ambulance.system.worksheet.entity;

import lombok.*;
import java.util.Date;
import javax.persistence.*;
import g05.ambulance.system.entity.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="WORKSHEET")
public class Worksheet {
    @Id
    @SequenceGenerator(name="WORKSHEET_ID",sequenceName="WORKSHEET_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="WORKSHEET_ID")
    @Column(name="WORKSHEET_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull Date time;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee createdBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Am_Vehicle.class)
    @JoinColumn(name = "AMVEHICLE_ID", insertable = true)
    private Am_Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Start_Getoff_Work.class)
    @JoinColumn(name = "StartGetoffWork_ID", insertable = true)
    private Start_Getoff_Work start_Getoff_Work;
}