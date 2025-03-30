package br.com.iteam.repository;

import br.com.iteam.infrastructure.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
