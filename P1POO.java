/*
 * 
 */

package br.graduacao.principal;

import br.graduacao.banco.Banco;
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
        String nomeTabela="alunos";
        TratarPasta tp = new TratarPasta(caminho);
        Banco db = new Banco();
        //db.criarTabela(nomeTabela);
        
        //------- Objetivos ----------------------
        tp.tratarArq(nomeTabela);

        db.listarDados(nomeTabela);
        //db.apagarDados(nomeTabela);
        //ta.criarArq("Zero1.txt");
        //String dados = ta.lerDadosArqTxt("Teste 1.txt");

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