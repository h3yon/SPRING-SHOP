package com.sparta.springcore.service;

import com.sparta.springcore.repository.ProductRepository;
import com.sparta.springcore.dto.ProductMypriceRequestDto;
import com.sparta.springcore.dto.ProductRequestDto;
import com.sparta.springcore.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    // 멤버 변수 선언
    private final ProductRepository productRepository;
    private static final int MIN_PRICE = 100;

    // 생성자: ProductService() 가 생성될 때 호출됨, 빈으로 등록해줌
    @Autowired
    public ProductService(ProductRepository productRepository) { // 빈을 DI 받아서 멤버 변수로 선언 받음
        // 멤버 변수 생성
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        // 멤버 변수 사용
        return productRepository.findAll();
    }

    public Product createProduct(ProductRequestDto requestDto) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);
        productRepository.save(product);
        return product;
    }

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다.")); // Optional이기 때문에.

        /**
         * 1번째 방법(@Transactional X)
         */
//        int myPrice = requestDto.getMyprice();
//        product.setMyprice(myPrice);
//        productRepository.save(myPrice); // JPA가 자동으로 업데이트 해줌

        /**
         * 2번째 방법(@Transactional O)
         */
        int myPrice = requestDto.getMyprice();
        if (myPrice < MIN_PRICE) {
            throw new IllegalArgumentException("유효하지 않은 관심 가격입니다. 최소 " + MIN_PRICE + " 원 이상으로 설정해 주세요.");
        }
        product.updateMyPrice(myPrice);

        return product;
    }
}