package heyoom.first.board.domain;

import org.springframework.web.multipart.MultipartFile;

public class Board {

	private Long bbd_seq;
	private Long ans_seq;
	private String reg_writer;
	private String bbd_title;
	private String bbd_content;
	private Long bbd_attach_length;
	private MultipartFile bbd_attach_1;
	private MultipartFile bbd_attach_2;
	private MultipartFile bbd_attach_3;
	private MultipartFile bbd_attach_4;
	private MultipartFile bbd_attach_5;
	private String bbd_password;
	private String inq_security_yn;
	private String reg_datetime;
	
	private Long answer_count;
	private Long total_views;
	
	
	public Long getBbd_seq() {
		return bbd_seq;
	}
	public void setBbd_seq(Long bbd_seq) {
		this.bbd_seq = bbd_seq;
	}
	
	public Long getAns_seq() {
		return ans_seq;
	}
	public void setAns_seq(Long ans_seq) {
		this.ans_seq = ans_seq;
	}
	
	public String getReg_writer() {
		return reg_writer;
	}
	public void setReg_writer(String reg_writer) {
		this.reg_writer = reg_writer;
	}
	
	public String getBbd_title() {
		return bbd_title;
	}
	public void setBbd_title(String bbd_title) {
		this.bbd_title = bbd_title;
	}
	
	public String getBbd_content() {
		return bbd_content;
	}
	public void setBbd_content(String bbd_content) {
		this.bbd_content = bbd_content;
	}
	
	public MultipartFile getBbd_attach_1() {
		return bbd_attach_1;
	}
	public void setBbd_attach_1(MultipartFile bbd_attach_1) {
		this.bbd_attach_1 = bbd_attach_1;
	}
	
	public MultipartFile getBbd_attach_2() {
		return bbd_attach_2;
	}
	public void setBbd_attach_2(MultipartFile bbd_attach_2) {
		this.bbd_attach_2 = bbd_attach_2;
	}
	
	public MultipartFile getBbd_attach_3() {
		return bbd_attach_3;
	}
	public void setBbd_attach_3(MultipartFile bbd_attach_3) {
		this.bbd_attach_3 = bbd_attach_3;
	}
	
	public MultipartFile getBbd_attach_4() {
		return bbd_attach_4;
	}
	public void setBbd_attach_4(MultipartFile bbd_attach_4) {
		this.bbd_attach_4 = bbd_attach_4;
	}
	
	public MultipartFile getBbd_attach_5() {
		return bbd_attach_5;
	}
	public void setBbd_attach_5(MultipartFile bbd_attach_5) {
		this.bbd_attach_5 = bbd_attach_5;
	}
	
	public String getBbd_password() {
		return bbd_password;
	}
	public void setBbd_password(String bbd_password) {
		this.bbd_password = bbd_password;
	}
	
	public String getInq_security_yn() {
		return inq_security_yn;
	}
	public void setInq_security_yn(String inq_security_yn) {
		this.inq_security_yn = inq_security_yn;
	}
	
	public String getReg_datetime() {
		return reg_datetime;
	}
	public void setReg_datetime(String reg_datetime) {
		this.reg_datetime = reg_datetime;
	}
	
	public Long getAnswer_count() {
		return answer_count;
	}
	public void setAnswer_count(Long answer_count) {
		this.answer_count = answer_count;
	}
	
	public Long getTotal_views() {
		return total_views;
	}
	public void setTotal_views(Long total_views) {
		this.total_views = total_views;
	}
	public Long getBbd_attach_length() {
		return bbd_attach_length;
	}
	public void setBbd_attach_length(Long bbd_attach_length) {
		this.bbd_attach_length = bbd_attach_length;
	}


	
	

	
	
	
}
