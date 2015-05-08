package br.graduacao.principal;

import br.graduacao.tratativa.TratarArquivo;
import br.graduacao.tratativa.TratarPasta;

/**
 *
 * @author ChristianRamos
 */
public class P1POO {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String caminho="I:\\POOII"; //Objetivo 1
        
        //TratarPasta tp = new TratarPasta(caminho);
        //tp.validarArq();// Objetivo 2 e 3
        
        TratarArquivo ta = new TratarArquivo(caminho);
        ta.criarArq("Teste000.txt");
    }
    
}
