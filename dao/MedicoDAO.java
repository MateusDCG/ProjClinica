
package dao;

import java.sql.ResultSet;
import java.sql.Connection;
import controle.Medico;
import conexao.Conecta;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;




//para resolver metodos do cadastro
public class MedicoDAO {
    //metodo para cadastrar no mysql 
    public void cadastrar(Medico m){
        Connection con = Conecta.getConecta();
        String sql = "INSERT INTO medico (nome, telefone, especialidade) VALUES(?,?,?)";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1,m.getNome() );
            smt.setString(2,m.getTelefone() );
            smt.setString(3,m.getEspecialidade() );
            smt.executeUpdate();
            smt.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Cadastrado com sucesso!");
        }catch(Exception ex){
        JOptionPane.showMessageDialog(null,"**ERRO NO CADASTRO***"+ex.getMessage());
        };
    }
     
    //metodo para alterar no mysql
    public void alterar (Medico m){
       Connection con = Conecta.getConecta(); 
       String sql = "UPDATE medico SET nome = ?, telefone = ?, especialidade = ?  WHERE id_medico = ?";
        try(PreparedStatement smt = con.prepareStatement(sql)){
           smt.setString(1,m.getNome() );
           smt.setString(2,m.getTelefone() );
           smt.setString(3,m.getEspecialidade() );
           smt.setInt(4, m.getId_medico());
           smt.executeUpdate();
           smt.close();
           con.close();
           JOptionPane.showMessageDialog(null,"Alterado com sucesso!");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"**ERRO AO ATUALIZAR***");
        };
    }
    
    // metodo para excluir no mysql
    public void excluir (Medico m){
        Connection con = Conecta.getConecta(); 
        String sql = "DELETE FROM medico WHERE id_medico = ?";
        
        int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir ? ", "Excluir", JOptionPane.YES_NO_OPTION);
        if(opcao == JOptionPane.YES_OPTION){
            try(PreparedStatement smt = con.prepareStatement(sql)){
                smt.setInt(1, m.getId_medico());
                smt.executeUpdate();
                smt.close();
                con.close();
                JOptionPane.showMessageDialog(null,"Excluido com sucesso!");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"**ERRO AO EXCLUIR***");
            };
        }   
    } 
    
    //metodo para listar todos os medicos cadastrados no mysql
    public List<Medico> listarM(){
         Connection con = Conecta.getConecta(); 
         List<Medico> listaMedico = new ArrayList<>();
         String sql = "SELECT * FROM medico ORDER BY nome";
         try(PreparedStatement smt = con.prepareStatement(sql)){
             ResultSet resultado = smt.executeQuery();
             while(resultado.next()){
                Medico m = new Medico();
                m.setId_medico(resultado.getInt("id_medico"));
                m.setNome(resultado.getString("nome"));
                m.setTelefone(resultado.getString("telefone")); 
                m.setEspecialidade(resultado.getString("especialidade")); 
                listaMedico.add(m);
                
             }
             smt.close();
             con.close();
         }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"**ERRO AO EXIBIR A LISTA***"+ex.getMessage());
        }
         return listaMedico;
    }   
    
    
}
