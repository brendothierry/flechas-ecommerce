package com.flechas.project.flechas.repository;

import com.flechas.project.flechas.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Métodos personalizados podem ser criados depois, ex:
    // List<Produto> findByNomeContainingIgnoreCase(String nome);
}
