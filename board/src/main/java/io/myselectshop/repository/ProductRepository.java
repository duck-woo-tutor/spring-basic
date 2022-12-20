package io.myselectshop.repository;

import io.myselectshop.dto.ProductMyPriceRequestDto;
import io.myselectshop.dto.ProductResponseDto;
import io.myselectshop.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final String dbUrl;
    private final String username;
    private final String password;

    public ProductRepository(String dbUrl, String username, String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, username, password);
    }

    public ProductResponseDto createProduct(Product product) throws SQLException {
        Connection connection = getConnection();

        // DB Query 작성
        PreparedStatement ps = connection.prepareStatement("select max(id) as id from product");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // product id 설정 = product 테이블의 마지막 id + 1
            product.setId(rs.getLong("id") + 1);
        } else {
            throw new SQLException("product 테이블의 마지막 id 값을 찾아오지 못했습니다.");
        }

        ps = connection.prepareStatement("insert into product(id, title, image, link, lprice, myprice) values(?, ?, ?, ?, ?, ?)");
        ps.setLong(1, product.getId());
        ps.setString(2, product.getTitle());
        ps.setString(3, product.getImage());
        ps.setString(4, product.getLink());
        ps.setInt(5, product.getLprice());
        ps.setInt(6, product.getMyprice());

        // DB Query 실행
        ps.executeUpdate();

        // DB 연결 해제
        ps.close();
        connection.close();

        return new ProductResponseDto(product);
    }

    public List<ProductResponseDto> getProducts() throws SQLException {
        List<ProductResponseDto> products = new ArrayList<>();

        // DB 연결
        Connection connection = getConnection();

        // DB Query 작성 및 실행
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("select * from product");

        // DB Query 결과를 상품 객체 리스트로 변환
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setImage(rs.getString("image"));
            product.setLink(rs.getString("link"));
            product.setLprice(rs.getInt("lprice"));
            product.setMyprice(rs.getInt("myprice"));
            product.setTitle(rs.getString("title"));
            products.add(new ProductResponseDto(product));
        }

        // DB 연결 해제
        rs.close();
        connection.close();

        // 응답 보내기
        return products;
    }

    public Product getProduct(Long id) throws SQLException {
        Product product = new Product();
        // DB 연결
        Connection connection = getConnection();

        // DB Query 작성
        PreparedStatement ps = connection.prepareStatement("select * from product where id = ?");
        ps.setLong(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            product.setId(rs.getLong("id"));
            product.setImage(rs.getString("image"));
            product.setLink(rs.getString("link"));
            product.setLprice(rs.getInt("lprice"));
            product.setMyprice(rs.getInt("myprice"));
            product.setTitle(rs.getString("title"));
        } else {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }

        // DB 연결 해제
        rs.close();
        ps.close();
        connection.close();

        // 응답 보내기 (업데이트된 상품 id)
        return product;
    }

    public Long updateProduct(Long id, ProductMyPriceRequestDto requestDto) throws SQLException {
        Product product = new Product();
        // DB 연결
        Connection connection = getConnection();

        // DB Query 작성
        PreparedStatement ps = connection.prepareStatement("select * from product where id = ?");
        ps.setLong(1, id);

        // DB Query 실행
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            product.setId(rs.getLong("id"));
            product.setImage(rs.getString("image"));
            product.setLink(rs.getString("link"));
            product.setLprice(rs.getInt("lprice"));
            product.setMyprice(rs.getInt("myprice"));
            product.setTitle(rs.getString("title"));
        } else {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }

        // DB Query 작성
        ps = connection.prepareStatement("update product set myprice = ? where id = ?");
        ps.setInt(1, requestDto.getMyPrice());
        ps.setLong(2, product.getId());

        // DB Query 실행
        ps.executeUpdate();

        // DB 연결 해제
        rs.close();
        ps.close();
        connection.close();

        // 응답 보내기 (업데이트된 상품 id)
        return product.getId();
    }
}
