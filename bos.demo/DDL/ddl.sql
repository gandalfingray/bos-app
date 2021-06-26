create database mybos;

drop table tsycd_comm_cd_dtl;
create table tsycd_comm_cd_dtl (
	comm_cd_grp varchar(10) not null,
	comm_cd varchar(10) not null,
	comm_nm varchar(200) not null,
	ref_cd1 varchar(500) null,
	ref_cd2 varchar(500) null,
	ref_cd3 varchar(500) null,
	deflt_yn char(1) not null default 'Y',
	rmrk varchar(1000) null,
	sort_no int4 null,
	use_yn char(1) not null default 'Y',
	sys_use_yn char(1) not null default 'Y',
	inpt_menu_id varchar(10) null,
	regr_id varchar(50) null,
	reg_date timestamp null,
	chgr_id varchar(50) null,
	chg_date timestamp null,
	id serial not null,
	primary key(comm_cd)
);

drop table products;
CREATE TABLE `products` (
  `prod_id` int(11) NOT NULL AUTO_INCREMENT,
  `prod_name` varchar(255)  NOT NULL,
  `prod_price` int(11) NOT NULL,
  `reg_date` datetime DEFAULT NULL,
  `spec` varchar(100) DEFAULT NULL,
  `model_name` varchar(100)  DEFAULT NULL,
  PRIMARY KEY (`prod_id`)
)