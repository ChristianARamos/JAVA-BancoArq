package br.graduacao.tratativa;

import br.graduacao.banco.Banco;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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

    public void tratarArq(String nomeTabela){
        this.validarArq();
        TratarZip tz = new TratarZip(caminho);
        tz.verifacarZip();
        File arq = new File(caminho);
        File[] lista = arq.listFiles();
        for(File conteudo : lista){
            if(conteudo.getName().endsWith(".csv")){
                System.out.println("Lendo dados do arquivo "+conteudo.getName());
                this.lerDadosArq(conteudo.getName(), nomeTabela);
                this.criarPasta("BKP");
                this.copiarArq(conteudo.getAbsolutePath(), caminho+"\\BKP\\"+conteudo.getName());
                conteudo.delete();
                //System.out.println("Arquivo movido para pasta BKP.");
            }
        }
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
            System.out.println(origem+" movido para "+destino+" com sucesso!");
        }catch(IOException e){
            System.out.println("Erro ao copiar arquivo!"+e);
        }
        System.out.println();
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
            this.criarPasta("ERROR");
            for(File conteudo : listaArq){
                if(!conteudo.isDirectory()){
                    System.out.println("Verificando..."+conteudo.getName()+" "+conteudo.length()+" Bytes.");
                    if(conteudo.length()<= 0){
                        this.copiarArq(conteudo.getAbsolutePath(), caminho+"\\ERROR\\"+conteudo.getName());
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
         * O método lerDados faz leitura de dados do arquivo passado como
         * parâmetro.
         * @param arq
         * @param nomeTabela
         */
    public void lerDadosArq(String arq, String nomeTabela) {
        String line;//Ler linha-a-linha.
        String[] content;//Armazena conteúdo.
        String nome, curso, disc;
        int mat, turma, ano, sem;
        try {
            BufferedReader br = new BufferedReader(new FileReader(caminho + "\\" + arq));
            Banco db = new Banco();
            br.readLine();
            while ((line = br.readLine()) != null) {
                content=line.split(",");
                mat=Integer.valueOf(content[0].trim());
                nome=content[1].trim();
                curso=content[2].trim();
                disc=content[3].trim();
                turma=Integer.valueOf(content[4].trim());
                ano=Integer.valueOf(content[5].trim());
                sem=Integer.valueOf(content[6].trim());
                db.inserirDados(nomeTabela, mat, nome, curso, disc, turma, ano, sem);
                System.out.println("Dados lidos e inseridos no banco com sucesso!");
            }
            System.out.println("------------------------------------");
            br.close();
            System.out.println("Arquivo "+arq+" fechado!");
        } catch (IOException e) {
            System.out.println("Erro ao ler dados no arquivo!" + e);
        }
    }

    /**
     * O método gravarDados realiza a gravação dos dados no arquivo
     * especificado.
     *
     * @param dados.
     * @param arq
     */
    public void gravarDadosArqTxt(String dados, String arq) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(caminho + "\\" + arq));
            bw.write(dados);
            bw.flush();
            bw.close();
            System.out.println("Dados gravados!!!\nArquivo fechado!");
        } catch (IOException e) {
            System.out.println("Erro ao gravar no arquivo!" + e);
        }
    }
}
