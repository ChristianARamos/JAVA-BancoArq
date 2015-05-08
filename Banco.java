/*
 * Christian de Avila Ramos.
 */

package br.graduacao.banco;

import java.sql.Connection;
import java.sql.DriverManager;
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
    
    public Connection conectarPostgreSQL(){
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
    
    public void desconectarPostgreSQL(){
        try{
            connec.close();
            connec = null;
            System.out.println(DB+" desconectado!");
        }catch(SQLException e){
            System.out.println("Erro ao fechar o banco de dados!"+e);
        }
    }
    
    public void inserirDados(String dado){
    
    }
    
    public String listarDados(){
        
        
        return null;
    }
    
    public void atualizarDados(String dado){
    
    }
    
    public void apagarDados(){
    
    }
}