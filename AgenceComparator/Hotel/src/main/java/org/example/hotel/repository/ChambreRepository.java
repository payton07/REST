package org.example.hotel.repository;


import org.example.hotel.model.Chambre;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, String> {
}
