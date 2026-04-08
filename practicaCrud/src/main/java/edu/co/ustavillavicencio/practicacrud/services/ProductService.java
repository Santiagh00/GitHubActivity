package edu.co.ustavillavicencio.practicacrud.services;

import edu.co.ustavillavicencio.practicacrud.controllers.DTO.ProductRequestDTO;
import edu.co.ustavillavicencio.practicacrud.controllers.DTO.ProductResponseDTO;
import edu.co.ustavillavicencio.practicacrud.entities.ProductEntity;
import edu.co.ustavillavicencio.practicacrud.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service

public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponseDTO newProduct(ProductRequestDTO productRequestDTO){
        ProductEntity productToSave = ProductEntity.builder()
                .name(productRequestDTO.getName())
                .price(productRequestDTO.getPrice())
                .stock(productRequestDTO.getStock())
                .build();
        ProductEntity productSaved = productRepository.save(productToSave);

        ProductResponseDTO productResponseDTO = ProductResponseDTO.builder()
                .id(productSaved.getId())
                .name(productSaved.getName())
                .price(productSaved.getPrice())
                .stock(productSaved.getStock())
                .build();

        return productResponseDTO;
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO){

        ProductEntity existingProduct = productRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "No se encontro el producto"));

        existingProduct.setName(productRequestDTO.getName());
        existingProduct.setPrice(productRequestDTO.getPrice());
        existingProduct.setStock(productRequestDTO.getStock());

        ProductEntity updatedProduct = productRepository.save(existingProduct);

        ProductResponseDTO productResponseDTO = ProductResponseDTO.builder()
                .name(updatedProduct.getName())
                .price(updatedProduct.getPrice())
                .stock(updatedProduct.getStock())
                .build();

        return productResponseDTO;
    }
}