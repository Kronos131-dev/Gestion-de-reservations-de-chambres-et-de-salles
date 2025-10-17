import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<PaiementEntity, Integer>{
    List<PaiementEntite> findByStatus(PaiementStatus statut);

    List<PaiementEntite> findByMethod(PaiementMethod methode);
}