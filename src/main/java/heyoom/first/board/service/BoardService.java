package heyoom.first.board.service;

import java.util.List;

import heyoom.first.board.domain.Board;
import heyoom.first.board.repository.BoardRepository;

public class BoardService {

	private final BoardRepository boardRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	public List<Board> getBoardList(){
		return boardRepository.getBoards();
		
	}
	
	public Board createBoard(Board board) {
		Board boardForm = new Board();
		boardForm.setReg_writer(board.getReg_writer());
		boardForm.setBbd_title(board.getBbd_title());
		boardForm.setBbd_content(board.getBbd_content());
		boardForm.setBbd_attach_1(board.getBbd_attach_1());
		boardForm.setBbd_password(board.getBbd_password());
		boardForm.setInq_security_yn(board.getInq_security_yn());
		return boardRepository.postBoard(boardForm);
	}
}
