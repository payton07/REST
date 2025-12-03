package org.example.comparator.repository;

import org.example.comparator.model.ResponseComp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseCompRepository extends JpaRepository<ResponseComp, String> {
}
