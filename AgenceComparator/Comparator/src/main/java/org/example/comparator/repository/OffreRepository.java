package org.example.comparator.repository;

import org.example.comparator.classes.OffreComp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffreRepository extends JpaRepository<OffreComp, String> {
}
