import jakarta.persistence.*
import java.time.LocalDate;

@Entity
@Table(name ="paiement")
@Getter
@Setter
public class PaiementEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "paiement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaiement;

    @OneToOne
    @JoinColumn(name = "statut_id")
    private Integer idStatut;

    @Column(name = "prix")
    private BigDecimal prix;


    @Column(name = "date")
    private LocalDate date;

}