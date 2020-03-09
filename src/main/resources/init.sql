-- ----------------------------
-- Table structure for T_USER
-- ----------------------------
CREATE TABLE T_USER (
   ID NUMBER NOT NULL ,
   USERNAME VARCHAR2(20 BYTE) NOT NULL ,
   PASSWD VARCHAR2(128 BYTE) NOT NULL ,
   CREATE_TIME DATE NULL ,
   STATUS CHAR(1 BYTE) NOT NULL 
);
COMMENT ON COLUMN T_USER.USERNAME IS '用户名';
COMMENT ON COLUMN T_USER.PASSWD IS '密码';
COMMENT ON COLUMN T_USER.CREATE_TIME IS '创建时间';
COMMENT ON COLUMN T_USER.STATUS IS '是否有效 1：有效  0：锁定';
-- ----------------------------
-- Records of T_USER
-- ----------------------------
INSERT INTO T_USER VALUES ('2', 'test', '7a38c13ec5e9310aed731de58bbc4214', TO_DATE('2019-11-19 17:20:21', 'YYYY-MM-DD HH24:MI:SS'), '1');
INSERT INTO T_USER VALUES ('1', 'Kobe', 'bfb30eafbf1f4979e02e3aff0f1d54ff', TO_DATE('2017-11-19 10:52:48', 'YYYY-MM-DD HH24:MI:SS'), '1');
-- ----------------------------
-- Primary Key structure for table T_USER
-- ----------------------------
ALTER TABLE T_USER ADD PRIMARY KEY (ID);