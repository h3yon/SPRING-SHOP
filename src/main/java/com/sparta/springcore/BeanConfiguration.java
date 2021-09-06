package com.sparta.springcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // spring이 구동될 때 바라보고 @Bean인 부분을 담는다
public class BeanConfiguration {

    // 어디서 들어오는지 알 수 있음

    @Bean // 왼쪽, 스프링 IoC로 담긴다
    public ProductRepository productRepository() { // 이 스프링 IoC 안으로 넣어줌. 함수명으로 구분.
        String dbId = "sa";
        String dbPassword = "";
        String dbUrl = "jdbc:h2:mem:springcoredb";
        return new ProductRepository(dbId, dbPassword, dbUrl); // 객체화
    }

    @Bean // 생성한 그 객체를 등록
    @Autowired // 이렇게 repository를 빈을 꺼내와서 DI. 얘는 오른쪽, 리파지토리 DI되고 DI 받은 걸로 서비스 생성.
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository); // DI 받겠다
    }
}