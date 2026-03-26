package com.curso.ecomers.spring_emcommernce.repository;

import com.curso.ecomers.spring_emcommernce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {


}
