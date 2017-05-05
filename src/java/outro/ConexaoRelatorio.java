/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package outro;

import java.sql.Connection;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import persistence.GerarEntityManager;

/**
 *
 * @author Vinicius
 */
public class ConexaoRelatorio {
    
    public static Connection getEntityManagerJDBCConnection() throws SQLException {
            
        EntityManager em = GerarEntityManager.getInstance().getEntityManager();
            Session session = (Session) em.getDelegate();
            SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
            ConnectionProvider cp = (ConnectionProvider) sfi.getConnectionProvider();
            Connection conexao = cp.getConnection();
            return conexao;
            
    }
    
}
