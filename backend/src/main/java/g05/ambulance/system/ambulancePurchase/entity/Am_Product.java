package g05.ambulance.system.ambulancePurchase.entity;
import lombok.*;
import lombok.NonNull;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name="AM_PRODUCT")
public class Am_Product {

    @Id
    @SequenceGenerator(name="AM_PRODUCT_SEQ",sequenceName="AM_PRODUCT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AM_PRODUCT_SEQ")
    @Column(name="AM_PRODUCT_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    private @NonNull double price;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Company.class)
    @JoinColumn(name = "COMPANY_ID", insertable = true)
    private Company company;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<AmbulancePurchase> am_purchase;


}
