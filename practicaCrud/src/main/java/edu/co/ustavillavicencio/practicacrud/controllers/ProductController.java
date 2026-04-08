package edu.co.ustavillavicencio.practicacrud.controllers;

import edu.co.ustavillavicencio.practicacrud.controllers.DTO.ProductRequestDTO;
import edu.co.ustavillavicencio.practicacrud.controllers.DTO.ProductResponseDTO;
import edu.co.ustavillavicencio.practicacrud.entities.ProductEntity;
import edu.co.ustavillavicencio.practicacrud.repositories.ProductRepository;
import edu.co.ustavillavicencio.practicacrud.services.ProductService;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    @PostMapping("/createProduct")
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        return productService.newProduct(productRequestDTO);
    }

    @GetMapping("/showProducts")
    public List<ProductEntity> showProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/searchProduct/{id}")
    public Optional<ProductEntity> searchProduct(@PathVariable Long id){
        return productRepository.findById(id);
    }

    @PostMapping("/updateProduct/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Long id,
                                            @RequestBody ProductRequestDTO productRequestDTO){
        return productService.updateProduct(id, productRequestDTO);
    }

}
