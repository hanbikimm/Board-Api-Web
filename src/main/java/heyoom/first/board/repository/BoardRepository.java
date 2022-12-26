package heyoom.first.board.repository;

import java.util.List;
import java.util.Optional;

import heyoom.first.board.domain.Board;
import heyoom.first.board.domain.BoardStatus;

public interface BoardRepository {
	
	List<Board> getQuestions();
	List<Board> getAnswers(Long id);
	List<Board> getBoardsOfTitle(String searchWord);
	List<Board> getBoardsOfWriter(String searchWord);
	Optional<Board> getBoard(Long bbdId, Long ansId);
	int getTotalBoards();
	List<BoardStatus> getChart();
	Board postQuestion(Board board);
	Board postAnswer(Board board);
	String deleteBoard(Long bbdId, Long ansId);
	Board updateBoard(Board board);
	int checkAnswersForDelete(Long bbdId);
	String checkWrite();
	int checkView(Long bbdId, Long ansId);
	String plusView(Long bbdId, Long ansId, int count);
	String plusWrite(Long bbdId, Long ansId);
	
	
	

}
