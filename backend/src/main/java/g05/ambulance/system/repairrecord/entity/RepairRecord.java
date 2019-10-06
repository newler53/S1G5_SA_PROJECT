package g05.ambulance.system.repairrecord.entity;

import lombok.*;
import javax.persistence.*;
import g05.ambulance.system.entity.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="REPAIRRECORD")
public class RepairRecord {

    @Id
    @SequenceGenerator(name="RepairRecord_SEQ",sequenceName="RepairRecord_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RepairRecord_SEQ")
    @Column(name="RepairRecord_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private long Repairtime;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee CreateBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Am_Vehicle.class)
    @JoinColumn(name = "AM_VEHICLE_ID", insertable = true)
    private Am_Vehicle Vehicle;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = DamageType.class)
    @JoinColumn(name = "DAMAGE_TYPE_ID", insertable = true)
    private DamageType damageType;


}
