/*
 * Christian de Avila Ramos.
 */
package br.graduacao.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A classe Banco faz a conexão com o banco de dados; insere, altera, apaga,
 * lista e atualiza os dados.
 *
 * @author ChristianRamos
 */
public class Banco {

    Connection connec;
    String URL = "jdbc:postgresql://localhost:5432/";
    String DB = "ProjetoPOOII";
    String OWNER = "postgres";
    String PASS = "root";

    public Banco() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL - Driver carregado!");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver do banco PostgreSQL." + e);
        }
    }

    /**
     * O método conectarBanco faz a conexão com o banco de dados utilizando o
     * driver específico. Retorna a conexão.
     */
    public Connection conectarBanco() {
        if (connec == null) {
            try {
                connec = DriverManager.getConnection(URL + DB, OWNER, PASS);
                System.out.println(DB + " conectado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao conectar banco " + DB + "." + e);
            }
        }
        return connec;
    }
    /**
     * O método desconectarBanco realiza a desconexão com o banco de dados.
     */
    public void desconectarBanco() {
        try {
            connec.close();
            connec = null;
            System.out.println(DB + " desconectado!");
        } catch (SQLException e) {
            System.out.println("Erro ao fechar o banco de dados!" + e);
        }
    }
    /**
     * O metodo inserirDados insere dados na tabela do banco de dados de acordo
     * com os parâmetros especificados.
     * @param nomeTabela
     * @param matr
     * @param nome
     * @param curso
     * @param discipl
     * @param turma
     * @param ano
     * @param sem
     */
    public void inserirDados(String nomeTabela, int matr, String nome, String curso, String discipl, int turma, int ano, int sem) {
        try {
            String sql = "Insert into "+nomeTabela+"(matricula, nome, curso, disciplina, "
                    + "turma, ano, semestre) values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conectarBanco().prepareStatement(sql);
            ps.setInt(1, matr);
            ps.setString(2, nome);
            ps.setString(3, curso);
            ps.setString(4, discipl);
            ps.setInt(5, turma);
            ps.setInt(6, ano);
            ps.setInt(7, sem);

            ps.execute();
            ps.close();
            desconectarBanco();
            System.out.println("Dados inseridos!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no banco.\n" + e);
        }
    }
    /**
     * O método listarDados lista dados da tabela do banco de dados, retornando
     * um String.
     * @param nomeTabela
     * @return 
     */
    public String listarDados(String nomeTabela) {
        String result = "";
        try {
            String sql = "select * from "+nomeTabela;
            PreparedStatement ps = conectarBanco().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result += rs.getInt("matricula") + ", " + rs.getString("nome") + ", "
                        + rs.getString("curso") + ", " + rs.getString("disciplina") + ", "
                        + rs.getInt("turma") + ", " + rs.getInt("ano") + ", " + rs.getInt("semestre") + "\n";
            }
            rs.close();
            desconectarBanco();
        } catch (SQLException e) {
            System.out.println("Erro ao listar dados.\n" + e);
        }
        return result;
    }

    /**
     * O método atualizarDados atualiza os dados da tabela do banco de dados de
     * acordo com os parâmetros especificados.
     * @param matricula
     * @param nome
     * @param curso
     * @param disciplina
     * @param turma
     * @param ano
     * @param semestre
     */
    public void atualizarDados(String nomeTabela, int matricula, String nome, String curso, String disciplina, int turma, int ano, int semestre) {
        try {
            String sql = "update "+nomeTabela+" set nome=?, curso=?, disciplina=?, turma=?, ano=?, semestre=? where matricula = ?";
            PreparedStatement ps = conectarBanco().prepareStatement(sql);
            ps.setInt(7, matricula);
            ps.setString(1, nome);
            ps.setString(2, curso);
            ps.setString(3, disciplina);
            ps.setInt(4, turma);
            ps.setInt(5, ano);
            ps.setInt(6, semestre);
            ps.executeUpdate();//Atualiza.
            System.out.println("Atualização concluída com sucesso!");
            ps.close();
            desconectarBanco();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar dados." + e);
        }
    }
    /**
     * O método apagarDados elimina todos os dados da tabela.
     * @param nomeTabela
     */
    public void apagarDados(String nomeTabela) {
        try {
            String sql = "delete from "+nomeTabela;
            PreparedStatement ps = conectarBanco().prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            desconectarBanco();

        } catch (SQLException e) {
            System.out.println("Erro ao apagar dados da tabela "+nomeTabela+".\n" + e);
        }
    }
    /**
     * A classe criarTabela cria uma tabela no GBD PostgreSQL.
     * @param nomeTabela
     */
    public void criarTabela(String nomeTabela){
        try{
            String sql="CREATE TABLE "+nomeTabela+"(matricula integer NOT NULL,\n" +
            "  nome character varying(45), curso character varying(45),\n" +
            "  disciplina character varying(45), turma integer, ano integer,\n" +
            "  semestre integer, CONSTRAINT "+nomeTabela+"_pkey PRIMARY KEY (matricula))";            
            PreparedStatement ps=conectarBanco().prepareStatement(sql);
            ps.execute();
            System.out.println("Tabela "+nomeTabela+" criada com sucesso!");
            ps.close();
            desconectarBanco();
            
        }catch(SQLException e){
            System.out.println("Erro ao criar tabela!"+e);
        }
    }
}