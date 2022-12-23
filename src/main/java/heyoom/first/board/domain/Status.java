package heyoom.first.board.domain;

public class Status {
	
	private String date;
	private String day;
	private Long dailyView;
	private Long dailyWrite;
	
	private Long bbdIdOfFirstView;
	private Long ansIdOfFirstView;
	private String bbdTitleOfFirstView;
	
	private Long bbdIdOfFirstAnswer;
	private Long ansIdOfFirstAnswer;
	private String bbdTitleOfFirstAnswer;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Long getDailyView() {
		return dailyView;
	}
	public void setDailyView(Long dailyView) {
		this.dailyView = dailyView;
	}
	public Long getDailyWrite() {
		return dailyWrite;
	}
	public void setDailyWrite(Long dailyWrite) {
		this.dailyWrite = dailyWrite;
	}
	public Long getBbdIdOfFirstView() {
		return bbdIdOfFirstView;
	}
	public void setBbdIdOfFirstView(Long bbdIdOfFirstView) {
		this.bbdIdOfFirstView = bbdIdOfFirstView;
	}
	public Long getAnsIdOfFirstView() {
		return ansIdOfFirstView;
	}
	public void setAnsIdOfFirstView(Long ansIdOfFirstView) {
		this.ansIdOfFirstView = ansIdOfFirstView;
	}
	public String getBbdTitleOfFirstView() {
		return bbdTitleOfFirstView;
	}
	public void setBbdTitleOfFirstView(String bbdTitleOfFirstView) {
		this.bbdTitleOfFirstView = bbdTitleOfFirstView;
	}
	public Long getBbdIdOfFirstAnswer() {
		return bbdIdOfFirstAnswer;
	}
	public void setBbdIdOfFirstAnswer(Long bbdIdOfFirstAnswer) {
		this.bbdIdOfFirstAnswer = bbdIdOfFirstAnswer;
	}
	public Long getAnsIdOfFirstAnswer() {
		return ansIdOfFirstAnswer;
	}
	public void setAnsIdOfFirstAnswer(Long ansIdOfFirstAnswer) {
		this.ansIdOfFirstAnswer = ansIdOfFirstAnswer;
	}
	public String getBbdTitleOfFirstAnswer() {
		return bbdTitleOfFirstAnswer;
	}
	public void setBbdTitleOfFirstAnswer(String bbdTitleOfFirstAnswer) {
		this.bbdTitleOfFirstAnswer = bbdTitleOfFirstAnswer;
	}

	
	
}
