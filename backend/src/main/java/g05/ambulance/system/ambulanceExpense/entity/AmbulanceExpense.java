package g05.ambulance.system.ambulanceExpense.entity;
import g05.ambulance.system.entity.Employee;
import g05.ambulance.system.usingAmbulance.entity.UsingAmbulance;

import lombok.*;
import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table(name="AMBULANCE_EXPENSE")
public class AmbulanceExpense {

    @Id
    @SequenceGenerator(name="AMBULANCE_EXPENSE_SEQ",sequenceName="AMBULANCE_EXPENSE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AMBULANCE_EXPENSE_SEQ")
    @Column(name="AMBULANCE_EXPENSE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull double budget;

    private @NonNull String note;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = true)
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ExpenseType.class)
    @JoinColumn(name = "EXPENSE_TYPE_ID", insertable = true)
    private ExpenseType expenseType;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = UsingAmbulance.class)
    @JoinColumn(name = "USE_ID", insertable = true)
    private UsingAmbulance using;


}
