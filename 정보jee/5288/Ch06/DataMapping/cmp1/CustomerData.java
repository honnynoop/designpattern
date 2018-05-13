package cmp1;

public class CustomerData implements java.io.Serializable {

   public String customerId = null;
   public String name = null;
   public String address = null;
   public int noOfPhoneAccounts = 0;
   
   public CustomerData () {
   }
   
   public CustomerData (String id, String nm, String ad, int na) {
      customerId = id;
      name = nm;
      address = ad;
      noOfPhoneAccounts = na;
   }
   
   public String getCustomerId() { 
     return customerId;
   }
   public void setCustomerId(String id) {
     if (customerId==null)
       customerId = id;
   }
   
   public String getName() { 
     return name;
   }
   public void setName(String nm) {
     name = nm;
   }
   
   public String getAddress() { 
     return address;
   }
   public void setAddress(String ad) {
     address = ad;
   }

   public int getNoOfPhoneAccounts() { 
     return noOfPhoneAccounts;
   }
   public void setNoOfPhoneAccounts(int pa) {
     noOfPhoneAccounts = pa;
   }
   
}
