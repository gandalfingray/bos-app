-- 시스템 관리 주제 영역 이름 SM
-- 테이블 명명 규칙
  -- 주제영역 + 용어조합 + 테이블분류어
-- 테이블 분류어
  -- 기본 : M
  -- 관계 : A
  -- 로그 : L
  -- 명세 : P
  -- 백업 : B
  -- 복제 : R
  -- 상세 : D
  -- 이력 : H
  -- 임시 : T
  -- 전문 : J
  -- 집계 : S
  -- 차원 : E
  -- 코드 : C
  -- 팩트 : F

create database mybos;

-- 공통 코드 테이블 SM_CMNS_CD_M
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

-- 공통 코드 테이블 SM_CMNS_CD_M
CREATE TABLE SM_CMNS_CD_M (
    CMNS_GRP_CD VARCHAR(20),
    HRNK_CMNS_GRP_CD VARCHAR(20),
    CMNS_GRP_CD_NM VARCHAR(200),
    USE_YN VARCHAR(1),
    DSC VARCHAR(1000)


)

-- 공통 코드 테이블 SM_CMNS_CD_D
CREATE TABLE SM_CMNS_CD_D (
    CMNS_GRP_CD,
    CMNS_CD_VAL,
    CMNS_CD_NM,
    REFR1_GRP_CD,
    REFR2_GRP_CD,
    REFR3_GRP_CD


)

drop table products;
CREATE TABLE `products` (
  `prod_id` int(11) NOT NULL AUTO_INCREMENT,
  `prod_name` varchar(255)  NOT NULL,
  `prod_price` int(11) NOT NULL,
  `reg_date` datetime DEFAULT NULL,
  `spec` varchar(100) DEFAULT NULL,
  `model_name` varchar(100)  DEFAULT NULL,
  PRIMARY KEY (`prod_id`)
);

-- 메뉴관리
drop table SM_MENU_M;
CREATE TABLE SM_MENU_M (
   SERS_NUM INT(10) NOT NULL AUTO_INCREMENT     comment '일련번호',
   MENU_ID VARCHAR(10)	NOT NULL		        comment '메뉴아이디',
   HRNK_MENU_ID VARCHAR(10)	NOT NULL	        comment '상위메뉴아이디',
   MENU_STEP INT(2)			                    comment '메뉴단계' ,
   MENU_NM NVARCHAR(100)	NOT NULL		    comment '메뉴명',
   MENU_ENG_NM VARCHAR(100)		                comment '메뉴영문명',  -- 신청필요
   SORT_SQNC INT(2)			                    comment '정렬순서' ,
   MENU_URL_ADDR VARCHAR(200)	                comment '메뉴URL주소',
   SRE_YN NVARCHAR(1) NOT NULL                  comment '화면여부',  - 신청필요구
   PRIMARY KEY(SERS_NUM)
);

-- 메뉴 권한
CREATE TABLE SM_MENU_AUTH_A (
    AUTH_SERS_NUM INT(10) NOT NULL              comment '권한일련번호',  -- 신청필요
    MENU_SERS_NUM INT(10) NOT NULL              comment '메뉴일련번호'   -- 신청필요
);


-- 권한
CREATE TABLE SM_AUTH_M (
    SERS_NUM INT(10) NOT NULL AUTO_INCREMENT    comment '일련번호',
    AUTH_ID VARCHAR(10)	NOT NULL		        comment '권한아이디',
    AUTH_NM NVARCHAR(100)	NOT NULL		    comment '권한명',
    AUTH_ENG_NM VARCHAR(100)                    comment '권한영문명',
    DSC VARCHAR(1000)		                comment '설명',
);

