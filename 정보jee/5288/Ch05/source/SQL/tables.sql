drop table WR_ACCOUNT;
create table WR_ACCOUNT (
  id number primary key,
  user_id number,
  balance number);

drop table WR_ACCOUNT_DETAIL;
create table WR_ACCOUNT_DETAIL (
  id number primary key,
  account_id number,
  transaction_date date,
  amount number,
  transaction_type varchar(30),
  description varchar(60),
  ref_num varchar(30));

drop table WR_USER;
create table WR_USER (
  id number primary key,
  login_id varchar2(30),
  pwd  varchar2(30));

drop table WR_USER_ROLE;
create table WR_USER_ROLE (
  id number primary key,
  login_id varchar2(30),
  role_name varchar2(30),
  role_group varchar2(30));
  
insert into WR_USER values (1, 'zola', 'striker');
insert into WR_USER values (2, 'gallas', 'defender');

insert into WR_USER_ROLE values (1, 'zola', 'standard', 'Roles');
insert into WR_USER_ROLE values (2, 'gallas', 'preferred', 'Roles');

insert into WR_ACCOUNT values (1, 1, 1000.00);
insert into WR_ACCOUNT values (2, 2, 20000.00);

insert into WR_ACCOUNT_DETAIL values (1, 1, '12-Dec-2000', 1000.00, 'Paid in by cheque', 'No Description', '012345');
insert into WR_ACCOUNT_DETAIL values (2, 2, '12-Dec-2000', 20000.00, 'Paid in by cheque', 'No Description', '123456');
insert into WR_ACCOUNT_DETAIL values (3, 2, '12-Dec-2000', 1000.00, 'Paid in by cash', 'No Description', '234567');
insert into WR_ACCOUNT_DETAIL values (4, 2, '12-Dec-2000', -1000.00, 'Paid out by cheque', 'No Description', '345678');

commit;