package heyoom.first.board.service;

import java.util.List;
import java.util.Optional;

import heyoom.first.board.domain.Board;
import heyoom.first.board.repository.BoardRepository;

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
	
	public Optional<Board> getBoardDetail(Long bbdId, Long ansId) {
		updateDayViews(bbdId, ansId);
		return boardRepository.getBoard(bbdId, ansId);
	}
	
	public int getTotal() {
		return boardRepository.getTotalBoards();
	}
	
	
	public Board createQuestion(Board board) {
		Board boardForm = new Board();
		boardForm.setReg_writer(board.getReg_writer());
		boardForm.setBbd_title(board.getBbd_title());
		boardForm.setBbd_content(board.getBbd_content());
		boardForm.setBbd_attach_1(board.getBbd_attach_1());
		boardForm.setBbd_password(board.getBbd_password());
		boardForm.setInq_security_yn(board.getInq_security_yn());
		return boardRepository.postQuestion(boardForm);
	}
	
	public Board createAnswer(Board board) {
		Board boardForm = new Board();
		boardForm.setBbd_seq(board.getBbd_seq());
		boardForm.setReg_writer(board.getReg_writer());
		boardForm.setBbd_title(board.getBbd_title());
		boardForm.setBbd_content(board.getBbd_content());
		boardForm.setBbd_attach_1(board.getBbd_attach_1());
		boardForm.setBbd_password(board.getBbd_password());
		boardForm.setInq_security_yn(board.getInq_security_yn());
		return boardRepository.postAnswer(boardForm);
	}
	
	public String eraseBoard(Long bbdId, Long ansId) {
		return boardRepository.deleteBoard(bbdId, ansId);
	}
	
	public Board editBoard(Board board) {
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
