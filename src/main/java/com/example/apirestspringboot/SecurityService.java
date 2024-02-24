package com.example.apirestspringboot;

import org.springframework.stereotype.Service;

/**
 * Servicio encargado de la validacion de tokens de autenticacion
 */
@Service
public class SecurityService {

    /**
     * Valida un token de autenticacion
     * @param token El token a validar
     * @return true si el token es válido, false en caso contrario
     */
    public boolean validateToken(String token){
        return (token.equals("t0k3n"));
    }

}
