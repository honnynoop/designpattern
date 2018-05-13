CREATE TABLE counter(
  seq_num      INT      NOT NULL,
  counter_name VARCHAR(25) NOT NULL,
  PRIMARY KEY(counter_name)
) ;

INSERT INTO counter(seq_num, counter_name) VALUES(1,'BOOKING');
INSERT INTO counter VALUES(1,'CITY');
INSERT INTO counter VALUES(1,'CUSTOMER');
INSERT INTO counter VALUES(1,'FLIGHT');
INSERT INTO counter VALUES(1,'HOTEL');



