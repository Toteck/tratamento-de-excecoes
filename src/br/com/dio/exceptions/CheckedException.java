package br.com.dio.exceptions;

import javax.swing.*;
import java.io.*;

public class CheckedException {

    public static void main(String[] args) {

        String nomeDoArquivo = "minhafamilia.txt";

        try {
            imprimirArquivoNoConsole(nomeDoArquivo); // Tente chamar o método "imprimirArquivoNoConsole"
        } catch (FileNotFoundException e) { //  Caso o nome do arquivo esteja errado lançe a exceção "FileNotFoundException" com o tratamento abaixo
            JOptionPane.showMessageDialog(null, "Revise o nome do arquivo que você deseja imprimir! " + e.getCause());
            e.printStackTrace(); // Essa parte é para o programador identificar onde tá o bo
        } catch (IOException e) { // Aqui a exceção mais generica, IOException é a mãe de FileNotFoundException, se eu coloca-se em cima nem precisaria de FileNotFoundException por essa já é genérica o suficiente
            JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado! Entre em contato com o suporte! " + e.getMessage());
        } finally {
            System.out.println("Chegou no finally!");
        }
    }

    // Aqui o throws informa ao chamador que este método pode lançar uma das exceções listadas no escopo do método.
    // Isso obriga a fazer a captura dessa exception (try-catch) ou relançar o throws
    // O que eu entendi é que ao colocar throws a classe que receber esse método vai ter que tratar e capturar as exceções deste método
    // Basicamente seria jogar o tratamento para frente kkk, e parece que eu posso fazer isso quantas vezes eu quiser sair jogando o próblema
    // Para o próximo até não dar mais.
    // como eu usei throws aqui e tô usando o método no main eu vou ter que tratar as exceções no main
    public static void imprimirArquivoNoConsole(String nomeDoArquivo) throws IOException {

        File file = new File((nomeDoArquivo));

        BufferedReader br = new BufferedReader(new FileReader(file.getName()));
        String line = br.readLine();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        do {
            bw.write(line);
            bw.newLine();
            line=br.readLine();
        } while (line != null);
        bw.flush();
        bw.close();
    }

}