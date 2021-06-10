package com.MyGymRoutine.myapp.view.components.utils;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class ValidateInput {

    private static final String REGEX_EMAIL = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@(([a-zA-Z]+[\\w-]+\\.){1,2}[a-zA-Z]{2,4})$";
    private static final String REGEX_PHONENUMBER = "^[67](\\d){2}\\s?(\\d){3}\\s?(\\d){3}$";
    private static final String REGEX_HEIGHT = "^[12][\\.][\\d]{1,2}$";

    public static boolean checkPattern(TextInputLayout inputLayout, String pattern, String errorMsg) {
        Pattern patron = Pattern.compile(pattern);
        String inputNumber = inputLayout.getEditText().getText().toString();
        if (patron.matcher(inputNumber).matches()) {
            inputLayout.setErrorEnabled(false);
            return true;
        } else {
            inputLayout.setError(errorMsg);
            return false;
        }
    }

    /**
     * @param inputLayout
     * @return return true if the input has any text
     */
    public static boolean typeText(TextInputLayout inputLayout) {

        if (inputLayout.getEditText().getText().length() != 0) {
            // Tiene texto escrito
            inputLayout.setErrorEnabled(false);
            return true;
        } else {
            // Está vacío
            inputLayout.setError("Campo incompleto");
            return false;
        }
    }

    public static boolean typePassword(TextInputLayout inputLayout1, TextInputLayout inputLayout2) {
        String password1 = inputLayout1.getEditText().getText().toString().trim();
        String password2 = inputLayout2.getEditText().getText().toString().trim();

        if (password1.equals(password2)) {
            // Contraseñas iguales
            inputLayout1.setErrorEnabled(false);
            inputLayout2.setErrorEnabled(false);

            // Si contraseña menor de 8 cifras
            if (password1.length() < 8) {
                inputLayout1.setError("Contraseña demasiado corta");
                return false;
            } else {
                inputLayout1.setErrorEnabled(false);
                return true;
            }
        } else {
            // Contraseñas diferentes
            inputLayout1.setError("Las contraseñas son diferentes");
            inputLayout2.setError("Las contraseñas son diferentes");
            return false;
        }
    }

    public static boolean typePhoneNumber(TextInputLayout inputLayout) {
        if (checkPattern(inputLayout, REGEX_PHONENUMBER, "Telefono incorrecto")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean typeEmail(TextInputLayout inputLayout) {
        if (checkPattern(inputLayout, REGEX_EMAIL, "Email incorrecto")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean typeUserHeight(TextInputLayout inputLayout) {
        if (checkPattern(inputLayout, REGEX_HEIGHT, "Altura incorrecta")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean typeUserWeight(TextInputLayout inputLayout) {
        double weight = Double.parseDouble(inputLayout.getEditText().getText().toString());

        if (weight > 300) {
            inputLayout.setError("Peso incorrecto");
            return false;
        } else if (weight == 0) {
            inputLayout.setError("Introduce peso");
            return false;
        } else {
            return true;
        }
    }
}
