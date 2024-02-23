package com.example.apirestspringboot;

import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public boolean validateToken(String token){
        return (token.equals("t0k3n"));
    }

}
