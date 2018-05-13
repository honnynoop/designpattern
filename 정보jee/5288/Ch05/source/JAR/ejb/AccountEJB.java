/*
 * AccountEJB.java
 *
 * Created on 27 April 2002, 10:45
 */

package ejb;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.ejb.SessionBean;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;

import java.util.ArrayList;
/**
 *
 * @author  pznwc5
 */
public class AccountEJB implements SessionBean {
    
    private SessionContext ctx;
    
    public void ejbCreate() {}
    public void ejbActivate() {}      
    public void ejbPassivate() {}       
    public void ejbRemove() {}    
    public void setSessionContext(SessionContext ctx) { this.ctx = ctx; }
    
    public Double getBalance() throws AccountException {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        InitialContext iCtx = null;
        DataSource ds = null;
        
        try {
            
            String loginId = ctx.getCallerPrincipal().getName();
            
            iCtx = new InitialContext();
            ds = (DataSource)iCtx.lookup("java:/AccountDS");
            
            con = ds.getConnection();
            stmt = con.prepareCall(AccountSQL.BALANCE_SQL);
            stmt.setString(1, loginId);
            
            rs = stmt.executeQuery();
            
            if(rs.next()) 
                return new Double(rs.getDouble(1));
            
            throw new AccountException("Account not found.");
            
        }catch(NamingException ex) {
            throw new EJBException(ex);
        }catch(SQLException ex) {
            throw new EJBException(ex);
        }finally {
            
            try {
                if(iCtx != null) iCtx.close();
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(con != null) con.close();
            }catch(Throwable th) {
                th.printStackTrace();
            }
            
        }
            
    }
    
    public ArrayList getStatement() throws AccountException {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        InitialContext iCtx = null;
        DataSource ds = null;
        
        try {
            
            String loginId = ctx.getCallerPrincipal().getName();
        
            iCtx = new InitialContext();
            ds = (DataSource)iCtx.lookup("java:/AccountDS");
            
            con = ds.getConnection();
            stmt = con.prepareCall(AccountSQL.STATEMENT_SQL);
            stmt.setString(1, loginId);
            
            rs = stmt.executeQuery();
            
            ArrayList statement = new ArrayList();
            AccountDetailVO vo;
            while(rs.next()) {
                
                vo = new AccountDetailVO();
                vo.transactionDate = rs.getTimestamp(1);
                vo.amount = rs.getDouble(2);
                vo.transactionType = rs.getString(3);
                vo.description = rs.getString(4);
                vo.refNum = rs.getString(5);
                
                statement.add(vo);
                
            }
            
            if(statement.size() > 0)
                return statement;
            
            throw new AccountException("Account not found.");
            
        }catch(NamingException ex) {
            throw new EJBException(ex);
        }catch(SQLException ex) {
            throw new EJBException(ex);
        }finally {
            
            try {
                if(iCtx != null) iCtx.close();
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(con != null) con.close();
            }catch(Throwable th) {
                th.printStackTrace();
            }
            
        }
            
    }
    
}
