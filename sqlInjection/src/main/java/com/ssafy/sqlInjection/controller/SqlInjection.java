package com.ssafy.sqlInjection.controller;

import com.ssafy.sqlInjection.DTO.LoginDTO;
import com.ssafy.sqlInjection.Member;
import com.ssafy.sqlInjection.respository.SqlInjectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SqlInjection {


    private final SqlInjectionRepository sqlInjectionRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String userId,
            @RequestParam String password
    ){
        LoginDTO loginDTO = new LoginDTO(userId,password);
        String returnValue = "로그인 성공";
        /**
         * 인젝션 가능
         */
        Member member1 = sqlInjectionRepository
                .loginInjection(loginDTO)
                .orElse(null);

        /**
         * 인젝션 방어
         */
//        Member member1 = sqlInjectionRepository
//                .loginInjection2(loginDTO)
//                .orElse(null);

        if(member1 == null) return new ResponseEntity<>("로그인 실패",HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(member1,HttpStatus.OK);
    }
}
