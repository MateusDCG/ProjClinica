
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class Conecta {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String usuario = "root";
    private static final String senha = "1234";
    private static final String url = "jdbc:mysql://localhost:3306/clinica";
    public static Connection getConecta(){
        Connection con = null;
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, usuario, senha);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Erro ao conectar BD"+ex.getMessage());
    }
        return con;
} 
    
}
//  o ex.getMessage() ajuda a achar o erro!
