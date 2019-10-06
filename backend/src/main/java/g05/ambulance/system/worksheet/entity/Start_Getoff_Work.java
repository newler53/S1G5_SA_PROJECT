package g05.ambulance.system.worksheet.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@Table(name="StartGetoffWork")
public class Start_Getoff_Work {

    @Id
    @SequenceGenerator(name="StartGetoffWork_SEQ",sequenceName="StartGetoffWork_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="StartGetoffWork_SEQ")
    @Column(name="StartGetoffWork_ID",unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String ChooseStartGetoff;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Worksheet> worksheet;

}