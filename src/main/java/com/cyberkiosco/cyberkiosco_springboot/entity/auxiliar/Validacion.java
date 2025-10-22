
package com.cyberkiosco.cyberkiosco_springboot.entity.auxiliar;


public final class Validacion {
    
    // constructor privado para que no sea instanciada
    private Validacion() {}
    
    
    public static void validarString(String str, String nombreAtributo) {
        if (str == null) {
            throw new IllegalArgumentException( nombreAtributo + " no puede ser null.");
        }
        
        if(str.trim().isEmpty()) {
            // .trim() elimina los espacios en blanco al principio y al final del string
            throw new IllegalArgumentException( nombreAtributo + " no puede estar vacio/a.");
        }
    }
    
    
    public static void validarNotNull(Object obj, String nombreAtributo) {
        if (obj == null) {
            throw new IllegalArgumentException( nombreAtributo + " no puede ser null.");
        }
    }
    
    
    public static void validarIntPositivo(int num, String nombreAtributo) {
        if (num <= 0) {
            throw new IllegalArgumentException( nombreAtributo + " debe ser mayor a cero.");
        }
    }
    
    
    public static void validarDoublePositivo(double num, String nombreAtributo) {
        if (num <= 0) {
            throw new IllegalArgumentException( nombreAtributo + " debe ser mayor a cero.");
        }
    }
    
}

