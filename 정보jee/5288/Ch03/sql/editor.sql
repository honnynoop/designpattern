DROP TABLE title;
CREATE TABLE title
(
 title_id        BIGINT     UNSIGNED NOT NULL,
 title_descr     VARCHAR(255)        NOT NULL,
 title_cost      FLOAT(10,2)         NOT NULL,
 row_version     BIGINT       NOT NULL,
 PRIMARY KEY(title_id)
) TYPE=BDB;
INSERT INTO title VALUES(1,'Java Today', 35.99,1);
INSERT INTO title VALUES(2,'Real Oracle Adventures', 54.32,2);
INSERT INTO title VALUES(3,'Technology Illustrated', 45.86,3);
INSERT INTO title VALUES(4,'Basket-weaving Weekly', 87.99,4);
INSERT INTO title VALUES(5,'Dolphins Quarterly', 19.99,5);
INSERT INTO title VALUES(6,'PC-Gamer Insights', 34.33,6);
INSERT INTO title VALUES(7,'New World Order - Conspiracy Theories and Other Things', 20.55,7);

DROP TABLE title_editor;
CREATE TABLE title_editor(
  title_id BIGINT UNSIGNED NOT NULL        ,
  editor_id BIGINT UNSIGNED NOT NULL        ,
  PRIMARY KEY(title_id, editor_id)
) TYPE=BDB;
INSERT INTO title_editor VALUES(1,2);
INSERT INTO title_editor VALUES(7,2);
INSERT INTO title_editor VALUES(2,2);
INSERT INTO title_editor VALUES(5,4);
INSERT INTO title_editor VALUES(6,4);
INSERT INTO title_editor VALUES(4,3);
INSERT INTO title_editor VALUES(3,3);
INSERT INTO title_editor VALUES(2,3);
INSERT INTO title_editor VALUES(1,3);

DROP TABLE editor;
CREATE TABLE editor(
  editor_id   BIGINT UNSIGNED NOT NULL   ,
  first_name    VARCHAR(30)   NOT NULL   ,
  middle_name   VARCHAR(30)              ,
  last_name     VARCHAR(30)   NOT NULL   ,
  row_version     BIGINT      NOT NULL,
  PRIMARY KEY(editor_id)
) TYPE=BDB;
INSERT INTO editor VALUES(1,'John','W','Wei',1);
INSERT INTO editor VALUES(2,'Dale','R','Busse',2);
INSERT INTO editor VALUES(3,'Kamlesh','C','Patel',3);
INSERT INTO editor VALUES(4,'Joel','D','Barbieri',4);

