/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        String dados = ta.lerDados("Teste 1.txt");
        ta.gravarDados(dados, "Teste000.txt");
    }
    
}
