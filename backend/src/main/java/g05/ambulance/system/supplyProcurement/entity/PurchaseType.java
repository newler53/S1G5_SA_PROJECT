package g05.ambulance.system.supplyProcurement.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name="PTYPE")
public class PurchaseType {
    @Id
    @SequenceGenerator(name="PTYPE_SEQ",sequenceName="PTYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PTYPE_SEQ")
    @Column(name="PTYPE_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String type;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Product> products;
}
