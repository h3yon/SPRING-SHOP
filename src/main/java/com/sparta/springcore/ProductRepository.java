package com.sparta.springcore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public interface ProductRepository {
    public List<Product> selectProducts(){
        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:springcoredb", "sa", "");
        // DB Query 작성 및 실행
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from product");
        // DB Query 결과를 상품 객체 리스트로 변환
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            product.setModifiedAt(rs.getTimestamp("modified_at").toLocalDateTime());
            product.setImage(rs.getString("image"));
            product.setLink(rs.getString("link"));
            product.setLprice(rs.getInt("lprice"));
            product.setMyprice(rs.getInt("myprice"));
            product.setTitle(rs.getString("title"));
            products.add(product);
        }
        // DB 연결 해제
        rs.close();
        connection.close();
        // 응답 보내기
        return products;
    }
}
