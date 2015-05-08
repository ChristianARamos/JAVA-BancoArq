package br.graduacao.tratativa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author ChristianRamos
 */
public class TratarArquivo {
    String caminho;

    /** @param caminho */
    public TratarArquivo(String caminho){
        this.caminho=caminho;
    }
    //======= Criar Arquivo =======
    /** @param nomeArq
     * Cria um arquivo. */
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
        String line="";//Ler line-a-line.
        String content="";//Armazena conteúdo.
        try{
            BufferedReader br= new BufferedReader(new FileReader(caminho +"\\"+ arq));
            while((line = br.readLine())!=null){
                content += line + "\n";
            }
            br.close();
        }catch(IOException e){
            System.out.println("Erro ao ler dados no arquivo!"+e);
        }
        return content;
    }
    
    //======= Gravar Dados =======
    /** Grava dados no arquivo especificado
     * @param dados.
     * @param arq*/
    public void gravarDados(String dados, String arq){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(caminho+"\\"+arq));
            bw.write(dados);
            bw.flush();
            bw.close();
            System.out.println("Dados gravados!!!");
        }catch(IOException e){
            System.out.println("Erro ao gravar no arquivo!"+e);
        }
    }
}
