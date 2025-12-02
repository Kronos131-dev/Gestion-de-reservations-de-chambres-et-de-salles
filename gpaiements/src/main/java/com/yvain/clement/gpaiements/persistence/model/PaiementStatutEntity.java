import jakarta.persistence.*
import java.time.LocalDate;

@Entity
@Table(name ="satut_paiement")
@Getter
@Setter
public class PaiementEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "satut_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatut;


    @Column(name = "code")
    private integer code;


    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description, length = 500")
    private String description;

}