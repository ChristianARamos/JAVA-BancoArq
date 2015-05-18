/*
 * Christian de Avila Ramos.
 *//*
 * Christian de Avila Ramos.
 */

package br.graduacao.principal;

import br.graduacao.banco.Banco;
import br.graduacao.tratativa.TratarArquivo;
import br.graduacao.tratativa.TratarPasta;
import br.graduacao.tratativa.TratarZip;

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
        String nomeTabela="alunos";
        
        Banco db = new Banco();
        TratarPasta tp = new TratarPasta(caminho);
        TratarArquivo ta = new TratarArquivo(caminho);
        //db.criarTabela(nomeTabela);
        
        //------- Objetivos ----------------------
        //tp.validarArq();// Objetivo 2 e 3
        //db.listarDados(nomeTabela);
        //db.apagarDados(nomeTabela);
        //-----------------------------------------

        //ta.criarArq("Zero1.txt");
        //String dados = ta.lerDadosArqTxt("Teste 1.txt");
        //ta.lerDadosArq("AnexoI.csv", nomeTabela);
        
        TratarZip tz = new TratarZip(caminho);
        tz.verifacarZip();
    }
}
/*
CREATE TABLE alunos
(
  matricula integer NOT NULL,
  nome character varying(45),
  curso character varying(45),
  disciplina character varying(45),
  turma integer,
  ano integer,
  semestre integer,
  CONSTRAINT alunos_pkey PRIMARY KEY (matricula)
)
 */