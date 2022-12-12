CREATE TABLE IF NOT EXISTS t_bbd (
    bbd_seq NUMERIC(10,0) NOT NULL,
    ans_seq NUMERIC(10,0) NOT NULL DEFAULT '0',
    reg_datetime DATETIME NOT NULL,
    reg_writer VARCHAR(260) NOT NULL,
    bbd_title VARCHAR(300) NOT NULL,
    bbd_content TEXT(10000) NOT NULL,
    bbd_attach_1 TEXT(500),
    bbd_attach_2 TEXT(500), 
    bbd_attach_3 TEXT(500), 
    bbd_attach_4 TEXT(500), 
    bbd_attach_5 TEXT(500), 
    bbd_password VARCHAR(260),
    inq_security_yn CHAR(1),
    CONSTRAINT pk_bbd_ans PRIMARY KEY (bbd_seq, ans_seq)
);

CREATE SEQUENCE IF NOT EXISTS seq_bbd;
CREATE SEQUENCE IF NOT EXISTS seq_ans;

CREATE TABLE IF NOT EXISTS t_inq_cnt (
	inq_date DATE NOT NULL,
    bbd_seq NUMERIC(10,0) NOT NULL,
    ans_seq NUMERIC(10,0) NOT NULL,
	day_views NUMERIC(10,0) DEFAULT '0',
    CONSTRAINT pk_inq_date_bbd_ans PRIMARY KEY (inq_date, bbd_seq, ans_seq)
);

CREATE TABLE IF NOT EXISTS t_ans_cnt (
	write_date DATE NOT NULL,
    bbd_seq NUMERIC(10,0) NOT NULL,
    ans_seq NUMERIC(10,0) NOT NULL,
	day_writes NUMERIC(10,0) DEFAULT '0',
    CONSTRAINT pk_ans_date_bbd_ans PRIMARY KEY (write_date, bbd_seq, ans_seq)
);

commit;