package br.com.dio.exceptions;

import javax.swing.*;
import java.io.*;

public class ExceptionPersonalizada {

    public static void main(String[] args) {

        String nomeDoArquivo = JOptionPane.showInputDialog("Nome do arquivo a ser exibido: ");

        imprimirArquivoNoConsole(nomeDoArquivo);
        // Dessa vez estou lendo e imprimindo em métodos diferentes
        System.out.println("\nCom exception ou não o programa continua...");
    }

    public static void imprimirArquivoNoConsole(String nomeDoArquivo) {

        try {
            BufferedReader br = lerArquivo(nomeDoArquivo); // Tente ler o arquivo. Para isso chamamos o método criado expecificamente para ler o arquivo
            String line = br.readLine(); // Não sei o que significa ainda
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            do{
                bw.write(line);
                bw.newLine();
                line = br.readLine();
            }while (line != null); // Como tem null no final do arquivo essa é a minha condição de parada
            bw.flush(); // A professora disse que era para descarregar
            bw.close(); // Fechar a escrita sei lá
            // essa exception se refere ao método ler arquivo
        } catch (ImpossivelAberturaDeArquivoException e){ // Caso não tenha conseguido encontrar o arquivo essa exceção será lançada
            // Foi aqui que eu tratei a minha exception personalizada, tanto que aqui não tem throws
            JOptionPane.showMessageDialog(null, e.getMessage()); // Esse e.getMessage() é um Método da Classe Exceptions. E eu passei a mensagem que eu queria por parâmetro lá no super() do construtor
            //e.printStackTrace();
        } catch (IOException ex) { // Caso o arquivo tenha sido encontrado, masssss aconteceu outro erro qualquer então será lançada a exceção IOException
            JOptionPane.showMessageDialog(null, "Ocorreu um erro não esperado, por favor, fale com o suporte. " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // O método abaixo é especificamente para a leitura do arquivo
    // Aqui eu estou lançado o throws ImpossivelAberturaDeArquivoException, ou seja, a classe ou método que usar ele vai ter que tratar essa bagaça
    public static BufferedReader lerArquivo(String nomeDoArquivo) throws ImpossivelAberturaDeArquivoException{
        File file = new File(nomeDoArquivo);
        // Aqui eu fiz o tratamento para o caso de der falha na leitura do arquivo. Que seria basicamente o nome do arquivo não foi encontrado.
        try {
            return new BufferedReader(new FileReader(nomeDoArquivo));
        } catch (FileNotFoundException e) {
            // tô lançando a minha exception
            throw new ImpossivelAberturaDeArquivoException(file.getName(), file.getPath());
        }
    }
}

class ImpossivelAberturaDeArquivoException extends Exception {

    private String nomeDoArquivo;
    private String diretorio;

    public ImpossivelAberturaDeArquivoException(String nomeDoArquivo, String diretorio) {
        super("O arquivo " + nomeDoArquivo + " não foi encontrado no diretório " + diretorio);
        this.nomeDoArquivo = nomeDoArquivo;
        this.diretorio = diretorio;
    }
}
