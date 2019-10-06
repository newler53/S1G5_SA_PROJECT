package g05.ambulance.system.supplyProcurement.entity;

import g05.ambulance.system.entity.Am_Vehicle;
import g05.ambulance.system.entity.Employee;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name="SUPPLYPercurement")
public class SupplyProcurement {
    @Id
    @SequenceGenerator(name="SUPPLY_SEQ",sequenceName="SUPPLY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SUPPLY_SEQ")
    @Column(name="SUPPLY_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull Date purchaseDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee createdBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Am_Vehicle.class)
    @JoinColumn(name = "AMVEHICLE_ID", insertable = true)
    private Am_Vehicle vehicle;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Product.class)
    @JoinColumn(name = "PRODUCT_ID", insertable = true)
    private Product product;
}
