/*
 * AccountDelegate.java
 *
 * Created on 27 April 2002, 10:43
 */

package web;

import ejb.Account;
import ejb.AccountHome;
import ejb.AccountException;

import javax.rmi.PortableRemoteObject;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

import java.util.ArrayList;

/**
 *
 * @author  pznwc5
 */
public class AccountDelegate {
    
    private Account account;
    
    /** Creates a new instance of AccountDelegate */
    public AccountDelegate() {
        
        try {
            
            InitialContext ctx = new InitialContext();
            Object obj = ctx.lookup("AccountBean");
            AccountHome home = 
            (AccountHome)PortableRemoteObject.narrow(obj, AccountHome.class);
            account = home.create();
            
        }catch(NamingException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }catch(RemoteException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }catch(CreateException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
        
    }
    
    public Double getBalance() throws AccountException {
        
        try {
            
            return account.getBalance();
            
        }catch(AccountException ex) {
            throw ex;
        }catch(Throwable ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
        
    }
    
    public ArrayList getStatement() throws AccountException {
        
        try {
            
            return account.getStatement();
            
        }catch(AccountException ex) {
            throw ex;
        }catch(Throwable ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
        
    }
    
}
