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
        
        TratarPasta tp = new TratarPasta(caminho);
        //tp.validarArq();// Objetivo 2 e 3
        
        
        TratarArquivo ta = new TratarArquivo(caminho);
        //ta.criarArq("Teste000.txt");
        //String dados = ta.lerDados("Teste 1.txt");
        //ta.gravarDados(dados, "Teste000.txt");
        
        Banco db = new Banco();
        //db.apagarDados();
        db.conectarBanco();
        db.inserirDados(92164, "Beltrano de Tal", "CST Análise e Desenvolvimento de Sistemas",
                "POOII", 2, 2013, 5);
        System.out.println(db.listarDados());
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