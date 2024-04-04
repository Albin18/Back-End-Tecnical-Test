package com.test.backendapirest.util;

import java.util.regex.Pattern;

public class RegexValidation {

    public boolean patternEmailValidation(String email){
        String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        return !Pattern.compile(regexEmail).matcher(email).matches() ? false : true;

    }

    public boolean patternPasswordValidation(String password){

        String regexPassword =
                        "^(?=.*[0-9])" +     //CONDICION DE NUMEROS ACEPTADOS
                        "(?=.*[A-Z])" +     // CONDICION DE LETRAS MAYUSCULAS ACEPTADAS
                        "(?=.*[a-z])" +     //CONDICION DE LETRAS MINUSCULAS ACEPTADAS
                        "(?=.*[!@#&()–[{}]:;',?/*~$^+=<>])." +//CONDICION DE SIMBOLOS Y CARACTERES ESPECIALES ACEPTADOS
                        "{8,20}$";  // CANTIDAD DE CARACTERES DE LA CONTRASEÑA{CARACTERES MINIMOS , CARACTERES MAXIMOS}


       return Pattern.compile(regexPassword, Pattern.CASE_INSENSITIVE).matcher(password).matches() ? true : false;
    }

}
