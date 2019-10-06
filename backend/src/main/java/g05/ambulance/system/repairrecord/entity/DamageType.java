package g05.ambulance.system.repairrecord.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;
import g05.ambulance.system.entity.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="DAMAGE_TYPE")
public class DamageType {

    @Id
    @SequenceGenerator(name="DAMGAGE_TYPE_SEQ",sequenceName="DAMGE_TYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DAMAGE_TYPE_SEQ")
    @Column(name="DAMAGE_TYPE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String type;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<RepairRecord> RepairRecord;


}