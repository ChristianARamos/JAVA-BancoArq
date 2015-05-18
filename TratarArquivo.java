package br.graduacao.tratativa;

import br.graduacao.banco.Banco;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A classe TratarArquivo pode ser utilizada para criação de arquivos bem como
 * na leitura e gravação de dados em arquivos.
 *
 * @author ChristianRamos
 */
public class TratarArquivo {

    String caminho;

    /**
     * O contrutor TratarArquivo recebe o caminho para o tratamento dos
     * arquivos.
     *
     * @param caminho
     */
    public TratarArquivo(String caminho) {
        this.caminho = caminho;
    }

    /**
     * O método criarArq cria um arquivo conforme o parâmetro informado.
     *
     * @param nomeArq
     */
    public void criarArq(String nomeArq) {
        try {
            File arq = new File(caminho + "\\" + nomeArq);
            arq.createNewFile();
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo!");
        }
    }
        /**
         * O método lerDados faz leitura de dados do arquivo.txt passado como
         * parâmetro retornando um String.
         *
         * @param arq
         * @return
         */
    public String lerDadosArq(String arq) {
        String line="";//Ler linha-a-linha.
        String content="";//Armazena conteúdo.
        try {
            BufferedReader br = new BufferedReader(new FileReader(caminho + "\\" + arq));
            Banco db = new Banco();
            br.readLine();
            while ((line = br.readLine()) != null) {
                content+=line+"\n";
            }
            System.out.println("------------------------------------");
            br.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler dados no arquivo!" + e);
        }
        return content;
    }

    /**
     * O método gravarDados realiza a gravação dos dados no arquivo
     * especificado.
     *
     * @param dados.
     * @param arq
     */
    public void gravarDados(String dados, String arq) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(caminho + "\\" + arq));
            bw.write(dados);
            bw.flush();
            bw.close();
            //System.out.println("Dados gravados!!!");
        } catch (IOException e) {
            System.out.println("Erro ao gravar no arquivo!" + e);
        }
    }
}
