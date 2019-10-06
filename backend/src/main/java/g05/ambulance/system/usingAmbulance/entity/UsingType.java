package g05.ambulance.system.usingAmbulance.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name="UTYPE")
public class UsingType {
    @Id
    @SequenceGenerator(name="UTYPE_SEQ",sequenceName="UTYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="UTYPE_SEQ")
    @Column(name="UTYPE_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String type;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<UsingAmbulance> using;
}
