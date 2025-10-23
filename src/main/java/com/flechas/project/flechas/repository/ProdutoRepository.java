package com.flechas.project.flechas.repository;

import com.flechas.project.flechas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Métodos personalizados podem ser criados depois, ex:
    // List<Produto> findByNomeContainingIgnoreCase(String nome);
}
