package heyoom.first.board.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import heyoom.first.board.domain.Board;
import heyoom.first.board.domain.BoardStatus;
import heyoom.first.board.repository.BoardRepository;
import heyoom.first.security.Seed;

public class BoardService {

	private final BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public List<Board> getQuestionList(){
		return boardRepository.getQuestions();
	}
	
	public List<Board> getAnswerList(Long id){
		return boardRepository.getAnswers(id);
	}
	
	public List<Board> getSearchList(String searchWord, Long value){
		List<Board> results = null;
		
		if (value == 1) {
			results = boardRepository.getBoardsOfTitle(searchWord);
		} else if (value == 2){
			searchWord = Seed.encrypt(searchWord);
			results = boardRepository.getBoardsOfWriter(searchWord);
		}
		return results;
	}
	
	public Optional<Board> getBoardDetail(Long bbdId, Long ansId) {
		return boardRepository.getBoard(bbdId, ansId);
	}
	
	public int getTotal() {
		return boardRepository.getTotalBoards();
	}
	
	public List<BoardStatus> getStatus(String date){
		List<String> week = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
			try {
				week.add(date);
				
				for (int i = 1; i <= 6; i++) {
					// string -> date
					Date typeDate = dateFormat.parse(date);
					
					// date -> calender
					Calendar typeCalendar = Calendar.getInstance();
					typeCalendar.setTime(typeDate);

					// calender += 1 day
					typeCalendar.add(Calendar.DATE, i);
					
					// + 1day -> string
					String typeString = dateFormat.format(typeCalendar.getTime());
					week.add(typeString);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return boardRepository.getChart(week);
	}
	
	@Transactional
	public Board createQuestion(Board board) {
		System.out.println(board.getBbd_attach_1());
		System.out.println(board.getBbd_attach_2());
		System.out.println(board.getBbd_attach_3());
		System.out.println(board.getBbd_attach_4());
		System.out.println(board.getBbd_attach_5());
		System.out.println(board.getBbd_attach_length());
		Board boardForm = new Board();
		boardForm.setReg_writer(Seed.encrypt(board.getReg_writer()));
		boardForm.setBbd_title(board.getBbd_title());
		boardForm.setBbd_content(board.getBbd_content());
		boardForm.setBbd_attach_1(board.getBbd_attach_1());
		boardForm.setBbd_attach_2(board.getBbd_attach_2());
		boardForm.setBbd_attach_3(board.getBbd_attach_3());
		boardForm.setBbd_attach_4(board.getBbd_attach_4());
		boardForm.setBbd_attach_5(board.getBbd_attach_5());
		boardForm.setBbd_password(Seed.encrypt(board.getBbd_password()));
		boardForm.setInq_security_yn(board.getInq_security_yn());
		return boardRepository.postQuestion(boardForm);
	}
	
	@Transactional
	public Board createAnswer(Board board) {
		Board boardForm = new Board();
		boardForm.setBbd_seq(board.getBbd_seq());
		boardForm.setReg_writer(Seed.encrypt(board.getReg_writer()));
		boardForm.setBbd_title(board.getBbd_title());
		boardForm.setBbd_content(board.getBbd_content());
		boardForm.setBbd_attach_1(board.getBbd_attach_1());
		boardForm.setBbd_attach_2(board.getBbd_attach_2());
		boardForm.setBbd_attach_3(board.getBbd_attach_3());
		boardForm.setBbd_attach_4(board.getBbd_attach_4());
		boardForm.setBbd_attach_5(board.getBbd_attach_5());
		boardForm.setBbd_password(Seed.encrypt(board.getBbd_password()));
		boardForm.setInq_security_yn(board.getInq_security_yn());
		return boardRepository.postAnswer(boardForm);
	}
	
	@Transactional
	public String eraseBoard(Long bbdId, Long ansId) {
		String message = null;
		if (ansId == 0) {
			int count = boardRepository.checkAnswersForDelete(bbdId);
			if (count > 0) {
				message = "답변이 존재해 삭제할 수 없습니다!";
			} else {
				message = boardRepository.deleteBoard(bbdId, ansId);
			}
		} else {
			message = boardRepository.deleteBoard(bbdId, ansId);
			;
		}
		return message;
	}
	
	public Board editBoard(Board board) {
		board.setReg_writer(Seed.encrypt(board.getReg_writer()));
		board.setBbd_password(Seed.encrypt(board.getBbd_password()));
		return boardRepository.updateBoard(board);
	}
	
	public String updateDayViews(Long bbdId, Long ansId) {
		int count = boardRepository.checkView(bbdId, ansId);
		return boardRepository.plusView(bbdId, ansId, count);
	}
	
	public String updateDayWrites(Long bbdId, Long ansId) {
		return boardRepository.plusWrite(bbdId, ansId);
	}
	
	
}
