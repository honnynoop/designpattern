DROP TABLE sql_code;
CREATE TABLE sql_code(
  name         VARCHAR(50)         NOT NULL,
  sql          BLOB                NOT NULL,
  PRIMARY KEY(name)
) TYPE=BDB;


INSERT INTO sql_code VALUES("titledao.findbyprimarykey().select",
                            "SELECT        
	                       title_id, 
		               title_descr,
		               title_cost,  
			       row_version  
			     FROM
		               title 
			     WHERE  
			       title_id=?");

INSERT INTO sql_code VALUES("titledao.update().update",
                            "UPDATE title              
			     SET title_descr = ?      ,
			         title_cost  = ?      ,
			         row_version = ?       
		             WHERE title_id  = ?      
			     AND   row_version = ?");   

INSERT INTO sql_code VALUES("titledao.delete().deletetitle",
                            "DELETE FROM title WHERE title_id = ?");

INSERT INTO sql_code VALUES("titledao.delete().deletetitleeditor",
                            "DELETE FROM title_editor WHERE title_id= ?");

INSERT INTO sql_code VALUES("titledao.insert().insert",
                            "INSERT INTO title 
			     (           
			       title_id,  
			       title_descr,
			       title_cost , 
			       row_version  
			     )      
			     VALUES  
			     (        
			       ?,?,?,? 
			     )");
			     
INSERT INTO sql_code VALUES("titledao.insert().inserttitleeditor()",
                            "INSERT INTO title_editor
			     (                      
			        title_id   ,       
			        editor_id         
			     )                   
			     VALUES             
			     (  ?,?   )");        

								   
