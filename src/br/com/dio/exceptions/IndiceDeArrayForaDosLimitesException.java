package br.com.dio.exceptions;

public class IndiceDeArrayForaDosLimitesException extends Exception{

    private int indice;
    private int tamanhoArray;

    public IndiceDeArrayForaDosLimitesException(String message, int indice, int tamanhoArray) {
        super(message);
        this.indice = indice;
        this.tamanhoArray = tamanhoArray;
    }
}
