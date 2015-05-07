package br.graduacao.tratativa;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 
 * @author ChristianRamos
 */
public class TratarPasta {
    String caminho;
    public TratarPasta(String caminho){
        this.caminho=caminho;
    }
    //======= Criar Pasta =======
    public void criarPasta(String nomePasta){
        try{
            new File(caminho+"\\"+nomePasta).mkdir();
            System.out.println("Pasta "+nomePasta+" criada com sucesso!\n");
        }catch(Exception e){
            System.out.println("Erro ao criar a pasta "+nomePasta);
        }
    }
    
    //======= Apagar Pasta =======
    public void apagarPasta(String nomePasta){
        try{
            new File(caminho+"\\"+nomePasta).delete();
        }catch(Exception e){
            System.out.println("Erro ao apagar a pasta "+nomePasta);
        }
    }
    
    //======= Copiar Arquivo =======
    //TratarPasta.copiarArq("Drive:\\PastaOrigem\\ArqOrigem.ext", "Drive:\\PastaDest\\ArqDest.ext");
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
    
    //======= Tratar Pasta =======    
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
