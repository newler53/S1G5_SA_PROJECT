package g05.ambulance.system.supplyProcurement.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name="PRODUCT")
public class Product {
    @Id
    @SequenceGenerator(name="PRODUCT_SEQ",sequenceName="PRODUCT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PRODUCT_SEQ")
    @Column(name="PRODUCT_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String name;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PurchaseType.class)
    @JoinColumn(name = "PTYPE_ID", insertable = true)
    private PurchaseType purchaseType;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = SupplyProcurement.class)
    private Collection<SupplyProcurement> purchase;

}
