package com.example.lab11.repository;

import com.example.lab11.entity.Distribuidoras;
import com.example.lab11.entity.Juegos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistribuidorasRepository extends JpaRepository<Distribuidoras, Integer> {
}