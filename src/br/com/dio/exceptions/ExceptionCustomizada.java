package br.com.dio.exceptions;

import javax.swing.*;

public class ExceptionCustomizada {

    public static void main(String[] args) {
        int[] numerador = {4, 5, 8, 10};
        int[] denominador = {2, 4, 0, 2, 8};
        for (int i=0; i < denominador.length; i++){
            try {
                if(numerador[i] %2 != 0)
                    throw new DivisaoNaoExataException("Divisão de " + numerador[i] + " por " + denominador[i] + " não é uma divisão exata", numerador[i], denominador[i]);

                if(denominador[i] == 0)
                    throw new DivisaoPorZeroException("Divisão de " + numerador[i] + " por " + denominador[i] + ". Não é possível realizar divisão por 0.", numerador[i], denominador[i]);
            } catch (DivisaoNaoExataException | DivisaoPorZeroException | ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            int resultado = numerador[i] / denominador[i];
            System.out.println(resultado);
        }

        System.out.println("o programa continua...");
    }

}
