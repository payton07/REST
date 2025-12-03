package org.example.hotel.repository;

import org.example.hotel.types.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OffreRepository extends JpaRepository<Offre, String> {
    Optional<Offre> findByChambreIdAndDateDebutAndDateFinAndPrixTotalAndNbLits(String chambreId, String dateDebut, String dateFin, double prixTotal, int nbLits);
}
