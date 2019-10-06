package g05.ambulance.system.ambulancePurchase.entity;
import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name="COMPANIES")
public class Company {

    @Id
    @SequenceGenerator(name="COMPANY_SEQ",sequenceName="COMPANY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COMPANY_SEQ")
    @Column(name="COMPANY_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Am_Product> am_product;

}
