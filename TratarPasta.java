package br.graduacao.tratativa;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * A classe Tratar pasta cria e apaga pastas; valida e copia arquivos.
 * @author ChristianRamos
 */
public class TratarPasta {
    String caminho;
    public TratarPasta(String caminho){
        this.caminho=caminho;
    }
    /**
     * O método criarPasta cria uma pasta com o nome passado como parâmetro.
     * @param nomePasta
     */
    public void criarPasta(String nomePasta){
        try{
            new File(caminho+"\\"+nomePasta).mkdir();
            System.out.println("Pasta "+nomePasta+" criada com sucesso!\n");
        }catch(Exception e){
            System.out.println("Erro ao criar a pasta "+nomePasta);
        }
    }
    
    /**
    * O método apagarPasta deleta a pasta especificada pelo parâmetro.
     * @param nomePasta
    */
    public void apagarPasta(String nomePasta){
        try{
            new File(caminho+"\\"+nomePasta).delete();
        }catch(Exception e){
            System.out.println("Erro ao apagar a pasta "+nomePasta);
        }
    }
    
    /**
     * O método copiarArq copia um arquivo de acordo com os parâmetros origem e
     * destino.
     * Ex.://TratarPasta.copiarArq("Drive:\\PastaOrigem\\ArqOrigem.ext", "Drive:\\PastaDest\\ArqDest.ext");
     * @param origem
     * @param destino
     */
    public void copiarArq(String origem, String destino){
        try{
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(origem));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destino));
            byte[] buffer = new byte[1024];//1MB
            int bytesLidos=in.read(buffer);
            
            while(bytesLidos <= 1024){
                out.write(buffer);
                bytesLidos=in.read(buffer);
                if(bytesLidos == -1){
                    break;
                }
            }
            in.close();
            out.close();
            System.out.println(origem+" copiado para "+destino+" com sucesso!");
        }catch(IOException e){
            System.out.println("Erro ao copiar arquivo!"+e);
        }
    }
    
    /**
     * O método validarArq faz a validação da pasta, verificando a existência de
     * arquivos com tamanho inferior ou igual a 0.
     * Arquivos menor ou igual a 0 são movidos para uma pasta chamada "Error"
     * criada pelo próprio método.
     */
    public void validarArq(){
        try{
            File arq = new File(caminho);
            File[] listaArq = arq.listFiles();
            this.criarPasta("Error");
            for(File conteudo : listaArq){
                if(!conteudo.isDirectory()){
                    System.out.println("Verificando..."+conteudo.getName()+" "+conteudo.length()+" Bytes");
                    if(conteudo.length()<= 0){
                        this.copiarArq(conteudo.getAbsolutePath(), caminho+"\\Error\\"+conteudo.getName());
                        this.apagarPasta(conteudo.getName());
                        System.out.println();
                    }else{
                        System.out.println();
                    }
                }
            }
        }catch(Exception e){
            System.out.println("Erro ao validar arquivo."+e);
        }    
    }
}
