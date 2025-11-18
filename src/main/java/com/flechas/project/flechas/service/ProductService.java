package com.flechas.project.flechas.service;

import com.flechas.project.flechas.model.Product;
import com.flechas.project.flechas.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> listAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Produto não encontrado!"));
    }

    public Product save(Product produto) {
        return repository.save(produto);
    }

    public Product update(Long id, Product produtoAtualizado) {
        Product existente = getById(id);
        existente.setNome(produtoAtualizado.getNome());
        existente.setPreco(produtoAtualizado.getPreco());
        existente.setEstoque(produtoAtualizado.getEstoque());
        existente.setDescricao(produtoAtualizado.getDescricao());
        existente.setImagemUrl(produtoAtualizado.getImagemUrl());
        return repository.save(existente);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
