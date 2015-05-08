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
 *
 * @author ChristianRamos
 */
public class Banco {
    Connection connec;
    String URL="jdbc:postgresql://localhost:5432/";
    String DB="ProjetoPOOII";
    String OWNER="postgres";
    String PASS="root";
    public Banco(){
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL - Driver carregado!");
            
        }catch(ClassNotFoundException e){
            System.out.println("Erro ao carregar o driver do banco PostgreSQL."+ e);
        }
    }
    
    public Connection conectarBanco(){
        if(connec == null){
            try{
                connec=DriverManager.getConnection(URL+DB,OWNER,PASS);
                System.out.println(DB+" conectado com sucesso!");
            }catch(SQLException e){
                System.out.println("Erro ao conectar banco "+DB+"."+e);
            }
        }
        return connec;
    }
    
    public void desconectarBanco(){
        try{
            connec.close();
            connec = null;
            System.out.println(DB+" desconectado!");
        }catch(SQLException e){
            System.out.println("Erro ao fechar o banco de dados!"+e);
        }
    }
    //======= Inserir Dados =======
    /**
     * @param matr
     * @param nome
     * @param curso
     * @param discipl
     * @param turma
     * @param ano
     * @param sem
     */
    public void inserirDados(int matr, String nome, String curso, String discipl, int turma, int ano, int sem){
        try{
            String sql="Insert into alunos(matricula, nome, curso, disciplina, "+
                    "turma, ano, semestre) values(?, ?, ?, ?, ?, ?, ?)";
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
        }catch(SQLException e){
            System.out.println("Erro ao inserir dados no banco.\n"+e);
        }
    }
    //======= Listar Dados =======
    public String listarDados(){
        String result="";
        try{    
            String sql="select * from alunos";
            PreparedStatement ps = conectarBanco().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                result += rs.getInt("matricula")+", "+rs.getString("nome")+", "
                        +rs.getString("curso")+", "+rs.getString("disciplina")+", "
                        +rs.getInt("turma")+", "+rs.getInt("ano")+", "+rs.getInt("semestre")+"\n";
            }
            rs.close();
            desconectarBanco();
        }catch(SQLException e){
            System.out.println("Erro ao listar dados.\n"+e);
        }
        return result;
    }
    /**
     * ======= Atualizar Dados =======
     * @param dados*/
    public void atualizarDados(String dados){
    
    }
    
    //======= Apagar Dados =======
    public void apagarDados(){
        try{
            String sql="delete from alunos";
            PreparedStatement ps=conectarBanco().prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
            desconectarBanco();
            
        }catch(SQLException e){
            System.out.println("Erro ao apagar dados da tabela."+e);
        }
    }
}