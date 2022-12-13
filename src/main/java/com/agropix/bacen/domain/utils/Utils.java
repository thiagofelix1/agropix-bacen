package com.agropix.bacen.domain.utils;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

public class Utils {
    public static boolean validaCpf(String cpf) {
        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String  nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            digitoCPF = Integer.parseInt(cpf.substring(nCount - 1, nCount));

            d1 = d1 + (11 - nCount) * digitoCPF;

            d2 = d2 + (12 - nCount) * digitoCPF;
        }

        resto = (d1 % 11);

        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        d2 += 2 * digito1;

        resto = (d2 % 11);

        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        String nDigVerific = cpf.substring (cpf.length() - 2);

        nDigResult = String.valueOf(digito1) + digito2;

        return nDigVerific.equals(nDigResult);
    }

    public static boolean validaCelular(String celular) {

        Objects.requireNonNull(celular);

        String celularSanitizado = removeCaracteres(celular);

        Pattern pattern = Pattern.compile("^[1-9]{2}9[0-9]{8}$");

        return pattern.matcher(celularSanitizado).matches();
    }

    public static String removeCaracteres(String str) {

        Objects.requireNonNull(str);

        StringBuilder nonNumerics = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            if (Character.isDigit(letter)) {
                nonNumerics.append(str.charAt(i));
            }
        }

        return nonNumerics.toString();
    }

    public static boolean validaEmail(String email) {
        Pattern pattern = Pattern.compile("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$");

        return pattern.matcher(email).matches();
    }

    public static boolean validaUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
