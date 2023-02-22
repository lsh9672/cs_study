package com.ssafy.sqlInjection.respository;


import com.ssafy.sqlInjection.DTO.LoginDTO;
import com.ssafy.sqlInjection.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;

@Slf4j
@Repository
public class SqlInjectionRepository {

    public Optional<Member> loginInjection(LoginDTO loginDTO){

        String sql = "SELECT * FROM members WHERE user_id = '" + loginDTO.getUserId() + "' AND pass_word='" + loginDTO.getPassword()+"'";
        ResultSet rs = null;
        Member member = null;

        // JDBC를 이용한 커넥션 얻은 후, 쿼리 요청
        Connection con = getConnection();
        try{
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            log.info("실행되는 sql 구문 : {}",sql);

            if(rs.next()){

                member = new Member(rs.getInt("id"), rs.getString("user_id"),rs.getString("pass_word"));
            }

        } catch(Exception e){
            log.info(e.getMessage());
        }

        return Optional.ofNullable(member);
    }

    public Optional<Member> loginInjection2(LoginDTO loginDTO){

        String sql = "SELECT * FROM members WHERE user_id=? AND pass_word=?";
        ResultSet rs = null;
        Member member = null;

        // JDBC를 이용한 커넥션 얻은 후, 쿼리 요청
        Connection con = getConnection();
        try{
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1,loginDTO.getUserId());
            pstmt.setString(2,loginDTO.getPassword());
            rs = pstmt.executeQuery();

            log.info("실행되는 sql 구문 : {}",pstmt);

            if(rs.next()){
                member = new Member(rs.getInt("id"), rs.getString("user_id"),rs.getString("pass_word"));
            }

        } catch(Exception e){
            log.info(e.getMessage());
        }

        return Optional.ofNullable(member);
    }

    private Connection getConnection() {
        Connection connection = null;

        try {
            String url ="jdbc:mysql://localhost:3306/simpleLogin?serverTimezone=UTC&characterEncoding=UTF-8";
            String username = "root";
            String password = "1234";
            connection = DriverManager.getConnection(url,username,password);

            log.info("get connection = {}, class = {}", connection, connection.getClass());
        } catch (SQLException e) {
            //sql 체크 예외를 런타임 예외로 변경해서 던진다.
            throw new IllegalStateException(e);
        }
        return connection;
    }
}
