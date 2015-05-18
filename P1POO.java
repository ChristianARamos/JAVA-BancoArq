/*
 * Christian de Avila Ramos.
 */

package br.graduacao.principal;

import br.graduacao.banco.Banco;
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
        String nomeTabela="alunos";
        //----------------------------------------
        TratarPasta tp = new TratarPasta(caminho);
        //tp.validarArq();// Objetivo 2 e 3
        
        //----------------------------------------
        Banco db = new Banco();
        db.conectarBanco();
        //db.criarTabela(nomeTabela);
        //db.listarDados(nomeTabela);
        //db.apagarDados(nomeTabela);
        //-----------------------------------------
        TratarArquivo ta = new TratarArquivo(caminho);
        //ta.criarArq("Teste000.txt");
        //String dados = ta.lerDadosArqTxt("Teste 1.txt");
        System.out.println(ta.lerDadosArq("AnexoI.csv"));
        //ta.gravarDados(dados, "Teste000.txt");
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