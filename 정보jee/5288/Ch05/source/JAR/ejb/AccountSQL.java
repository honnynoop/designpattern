/*
 * AccountSQL.java
 *
 * Created on 27 April 2002, 11:11
 */

package ejb;

/**
 *
 * @author  pznwc5
 */
public interface AccountSQL {
    
    public static final String BALANCE_SQL = 
    "select balance " +
    "from WR_ACCOUNT, WR_USER " +
    "where WR_ACCOUNT.user_id = WR_USER.id " +
    "and WR_USER.login_id = ?";
    
    public static final String STATEMENT_SQL = 
    "select transaction_date, amount, transaction_type, description, ref_num " +
    "from WR_ACCOUNT, WR_USER, WR_ACCOUNT_DETAIL " +
    "where WR_ACCOUNT.user_id = WR_USER.id " +
    "and WR_ACCOUNT.id = WR_ACCOUNT_DETAIL.account_id " +
    "and WR_USER.login_id = ?";
    
}
