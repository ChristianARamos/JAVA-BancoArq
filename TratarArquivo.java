package br.graduacao.tratativa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author ChristianRamos
 */
public class TratarArquivo {
    String caminho;
    public TratarArquivo(String caminho){
        this.caminho=caminho;
    }
    //======= Criar Arquivo =======
    public void criarArq(String nomeArq){
        try{
            File arq = new File(caminho+"\\"+nomeArq);
            arq.createNewFile();
        }catch(IOException e){
            System.out.println("Erro ao criar arquivo!");
        }
    }
    
    //======= Ler Dados =======
    public String lerDados(String arq){
        String linha="";//Ler linha-a-linha.
        String conteudo="";//Armazena conteúdo.
        try{
            BufferedReader br= new BufferedReader(new FileReader(caminho + arq));
            while((linha = br.readLine())!=null){
                conteudo += linha + "\n";
            }
            br.close();//Fecha o arquivo.
        }catch(IOException e){
            System.out.println("Erro ao gravar dados no arquivo!");
        }
        return conteudo;
    }
}
