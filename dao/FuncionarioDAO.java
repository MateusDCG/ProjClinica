/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.Connection;
import conexao.Conecta;
import controle.Funcionario;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mateu
 */
public class FuncionarioDAO {
    public void cadastrar(Funcionario f)
   {
        Connection con = Conecta.getConecta();
        String sql = "INSERT INTO funcionario (nome, senha ) VALUES(?,MD5(?))"; // md5 para gerar hash e proteger a senha
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1,f.getNome() );
            smt.setString(2,f.getSenha() );
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Cadastrado com sucesso!");
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null,"**ERRO NO CADASTRO***"+ex.getMessage());
        };
        
        
    }
    
    public void alterar (Funcionario f){
    Connection con = Conecta.getConecta(); 
       String sql = "UPDATE funcionario SET nome = ?  WHERE id_funcionario = ?";
        try(PreparedStatement smt = con.prepareStatement(sql)){
           smt.setString(1,f.getNome() );
           smt.setInt(2, f.getId_funcionario());
           smt.executeUpdate();
           smt.close();
           con.close();
           JOptionPane.showMessageDialog(null,"Alterado com sucesso!");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"**ERRO AO ATUALIZAR***");
        };
    
    }
    
    public void excluir (Funcionario f){
    Connection con = Conecta.getConecta(); 
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
        
        int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir ? ", "Excluir", JOptionPane.YES_NO_OPTION);
        if(opcao == JOptionPane.YES_OPTION){
            try(PreparedStatement smt = con.prepareStatement(sql)){
                smt.setInt(1, f.getId_funcionario());
                smt.executeUpdate();
                smt.close();
                con.close();
                JOptionPane.showMessageDialog(null,"Excluido com sucesso!");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"**ERRO AO EXCLUIR***");
            };
    
    }
    }
    
    public List<Funcionario> listarF(){ 
        Connection con = Conecta.getConecta(); 
         List<Funcionario> listaFuncionario = new ArrayList<>();
         String sql = "SELECT * FROM funcionario ORDER BY nome";
         try(PreparedStatement smt = con.prepareStatement(sql)){
             ResultSet resultado = smt.executeQuery();
             while(resultado.next()){
                Funcionario f = new Funcionario();
                f.setId_funcionario(resultado.getInt("id_funcionario"));
                f.setNome(resultado.getString("nome"));
                listaFuncionario.add(f);
                
             }
             smt.close();
             con.close();
         }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"**ERRO AO EXIBIR A LISTA***"+ex.getMessage());
        }
         return listaFuncionario;
    
    
    }
    
    
}
