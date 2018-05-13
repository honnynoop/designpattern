DROP TABLE counter;
CREATE TABLE counter(
  seq_num      BIGINT      NOT NULL,
  counter_name VARCHAR(25) NOT NULL,
  PRIMARY KEY(counter_name)
) TYPE=BDB;

INSERT INTO counter VALUES(8,"title_pk");
INSERT INTO counter VALUES(8,"title_rv");

INSERT INTO counter VALUES(5,"editor_pk");
INSERT INTO counter VALUES(5,"editor_rv");
