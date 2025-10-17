import jakarta.persistence.*

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

    @Column(name = "montant")
    private Double montant;

    @Enumerated(EnumType.STRING)
    @Column(name = "methode")
    private PaiementMethod methode;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private PaiementStatus statut;
}