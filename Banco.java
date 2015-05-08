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
    public Banco(){
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL - Driver carregado!");
            
        }catch(ClassNotFoundException e){
            System.out.println("Erro ao carregar o driver do banco PostgreSQL."+ e);
        }
    }
    
    public Connection conectarPostgreSQL(){
        String db="ProjetoPOOII";
        if(connec == null){
            try{
                connec=DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+db,"postgres","root");
                System.out.println("PostgreSQL - "+db+" conectado com sucesso!");
            }catch(SQLException e){
                System.out.println("Erro ao conectar "+db+"."+e);
            }
        }
        return connec;
    }
    
    public void desconectarPostgreSQL(){
        try{
            connec.close();
            connec = null;
        }catch(SQLException e){
            System.out.println("Erro ao fechar o banco de dados!"+e);
        }
    }
}