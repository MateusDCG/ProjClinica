
package dao;

import java.sql.ResultSet;
import java.sql.Connection;
import controle.Paciente;
import conexao.Conecta;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

//para resolver metodos do cadastro

public class PacienteDAO {
    public void cadastrar(Paciente p){
        Connection con = Conecta.getConecta();
        String sql = "INSERT INTO paciente (nome, datanasc, sexo, telefone) VALUES(?,?,?,?)";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1,p.getNome() );
            smt.setString(2,p.getDatanasc() );
            smt.setString(3,p.getSexo() ); 
            smt.setString(4,p.getTelefone() );
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Cadastrado com sucesso!");
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null,"**ERRO NO CADASTRO***"+ex.getMessage());
        };
    }
    public void alterar (Paciente p){
        Connection con = Conecta.getConecta(); 
        String sql = "UPDATE paciente SET nome = ?, datanasc =?, sexo = ?, telefone = ?  WHERE id_paciente = ?";
         try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1,p.getNome() );
            smt.setString(2,p.getDatanasc() );
            smt.setString(3,p.getSexo() ); 
            smt.setString(4,p.getTelefone() );
            smt.setInt(5, p.getId_paciente());
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Alterado com sucesso!");
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null,"**ERRO AO ATUALIZAR***");
        };
    }
    public void excluir (Paciente p){
        Connection con = Conecta.getConecta(); 
        String sql = "DELETE FROM paciente WHERE id_paciente = ?";
        
        int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir ? ", "Excluir", JOptionPane.YES_NO_OPTION);
        if(opcao == JOptionPane.YES_OPTION){
            try(PreparedStatement smt = con.prepareStatement(sql)){
                smt.setInt(1, p.getId_paciente());
                smt.executeUpdate();
                smt.close();
                con.close();
                JOptionPane.showMessageDialog(null,"Excluido com sucesso!");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"**ERRO AO EXCLUIR***");
            };
        }   
    } 
    
    public List<Paciente> listarP(){
         Connection con = Conecta.getConecta(); 
         List<Paciente> lista = new ArrayList<>();
         String sql = "SELECT * FROM paciente ORDER BY nome";
         try(PreparedStatement smt = con.prepareStatement(sql)){
             ResultSet resultado = smt.executeQuery();
             while(resultado.next()){
                Paciente p = new Paciente();
                p.setId_paciente(resultado.getInt("id_paciente"));
                p.setNome(resultado.getString("nome"));
                p.setDatanasc(resultado.getString("datanasc"));
                p.setSexo(resultado.getString("sexo"));
                p.setTelefone(resultado.getString("telefone")); 
                lista.add(p);
                
             }
             smt.close();
             con.close();
         }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"**ERRO AO EXIBIR A LISTA***");
        }
         return lista;
    }
    
}
