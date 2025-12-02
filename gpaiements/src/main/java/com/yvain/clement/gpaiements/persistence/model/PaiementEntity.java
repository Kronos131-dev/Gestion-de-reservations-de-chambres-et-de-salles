import jakarta.persistence.*
import java.time.LocalDate;

@Entity
@Table(name ="paiements")
@Getter
@Setter
public class PaiementEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "paiement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaiement;

    @Id
    @Column(name = "statut_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatut;

    @Column(name = "prix")
    private Double prix;


    @Column(name = "date")
    private LocalDate date;

}