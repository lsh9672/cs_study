package com.ssafy.sqlInjection;

import lombok.Data;

@Data
public class Member {
    private int id;
    private String userId;
    private String password;

    public Member() {
    }

    public Member(int id, String userId, String password) {
        this.id = id;
        this.userId = userId;
        this.password = password;
    }
}
