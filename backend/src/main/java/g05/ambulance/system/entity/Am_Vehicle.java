package g05.ambulance.system.entity;

import g05.ambulance.system.supplyProcurement.entity.SupplyProcurement;
import g05.ambulance.system.worksheet.entity.Worksheet;
import g05.ambulance.system.usingAmbulance.entity.UsingAmbulance;
import g05.ambulance.system.ambulancePurchase.entity.AmbulancePurchase;
import g05.ambulance.system.repairrecord.entity.RepairRecord;


import lombok.*;
import javax.persistence.*;
import java.util.Collection;
import com.fasterxml.jackson.annotation.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="AMVEHICLE")
public class Am_Vehicle {

    @Id
    @SequenceGenerator(name="AMVEHICLE_SEQ",sequenceName="AMVEHICLE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AMVEHICLE_SEQ")
    @Column(name="AMVEHICLE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String Am_Code;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<SupplyProcurement> purchase;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Worksheet> worksheet;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<UsingAmbulance> using;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<RepairRecord> RepairRecord;

    @OneToOne(mappedBy = "ambulabceCode")
    @JsonIgnore
    private AmbulancePurchase am_purchase;
}
