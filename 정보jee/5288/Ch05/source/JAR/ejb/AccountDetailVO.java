/*
 * AccountDetailVO.java
 *
 * Created on 27 April 2002, 10:52
 */

package ejb;

import java.sql.Timestamp;

/**
 *
 * @author  pznwc5
 */
public class AccountDetailVO {
    
    public Timestamp transactionDate;
    public double amount;
    public String transactionType;
    public String description;
    public String refNum;    
    
}
