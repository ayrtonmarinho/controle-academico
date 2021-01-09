/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admescola.util;

import java.util.Arrays;
import javafx.scene.control.TextField;

/**
 *
 * @author iTuhh Z
 */
public class TratamentoDado {

    //Validação de somente números
    public static boolean isNumber(TextField inputTextField) {
        boolean isNumeric = true;

        if (!inputTextField.getText().matches("[0-9]+")) {
            isNumeric = false;
        }
        return isNumeric;
    }

    //Validação de somente letras
    public static boolean textAlphabet(TextField inputTextField) {
        boolean isAlphabet = true;
        if (!inputTextField.getText().matches("[a-z A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]+")) {
            isAlphabet = false;
        }
        return isAlphabet;
    }

    //Validação de E-mail
    public static boolean emailFormat(TextField inputTextField) {
        boolean isEmail = true;
        if (!inputTextField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+\\.com") && !inputTextField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+\\.br")) {
            isEmail = false;
        }
        return isEmail;
    }

//Pega uma String e splita onde tem o simbolo especificado. Receb ate 2 simbolos.
    public static String filtroString(String inputField, String simbolo1, String simbolo2) {
        String s1 = null, result = null;
        String[] str1 = inputField.split(simbolo1);
        for (int i = 0; i < str1.length; i++) {
            s1 += str1[i];
        }
        String[] str2 = s1.split(simbolo2);
        for (int i = 0; i < str2.length; i++) {
            result += str2[i];
        }

        return result;
    }
    
    public static boolean codigoFormat(TextField inputTextField){
        boolean isValid = true;
        if(!inputTextField.getText().matches("[A-Z]+[A-Z]+[A-Z]+[0-9]+[0-9]+[0-9]+[0-9]")){
            return false;
        }
        return isValid;
    }
}
