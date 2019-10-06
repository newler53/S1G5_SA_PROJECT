package g05.ambulance.system.ambulancePurchase.entity;
import g05.ambulance.system.entity.Am_Vehicle;
import g05.ambulance.system.entity.Employee;
import lombok.*;
import lombok.NonNull;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name="AMBULANCE_PURCHASE")
public class AmbulancePurchase {

    @Id
    @SequenceGenerator(name="AMBULANCE_PURCHASE_SEQ",sequenceName="AMBULANCE_PURCHASE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AMBULANCE_PURCHASE_SEQ")
    @Column(name="AMBULANCE_PURCHASE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "PURCHASE_DATE")
    private @NonNull Date date;

    @Column(name = "Vehicle_Identification_Number" ,unique = true)
    private @NonNull String vin;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee createdBy;

    @OneToOne(cascade = CascadeType.MERGE) //MERGE,REMOVE,DETACH
    @JoinColumn(name = "AMVEHICLE_ID",unique = true) //@JsonManagedReference
    private Am_Vehicle ambulabceCode;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Am_Product.class)
    @JoinColumn(name = "AM_PRODUCT__ID", insertable = true)
    private Am_Product ambulabceBuy;

}
