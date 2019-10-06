package g05.ambulance.system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name="EMPPOSITION")
public class EmpPosition {

    @Id
    @SequenceGenerator(name="POSITION_SEQ",sequenceName="POSITION_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="POSITION_SEQ")
    @Column(name="POSITION_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String position;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Employee> employee;
}
