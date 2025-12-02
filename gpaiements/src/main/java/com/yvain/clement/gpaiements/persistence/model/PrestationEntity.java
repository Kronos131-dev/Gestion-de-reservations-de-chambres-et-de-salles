import jakarta.persistence.*
import java.time.LocalDate;

@Entity
@Table(name ="prestation")
@Getter
@Setter
public class PrestationEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "prestation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrestation;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Integer idReservation;

    @OneToOne
    @JoinColumn(name = "paiement_id")
    private Integer idPaiement;

    @Column(name = "prix")
    private Double prix;

}