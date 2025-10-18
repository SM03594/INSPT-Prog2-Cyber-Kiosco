package com.cyberkiosco.cyberkiosco_springboot.entity.exceptions;

public class FondosInsuficientesException extends RuntimeException {
    public FondosInsuficientesException(String mensaje) {
        super(mensaje);
    }
}
