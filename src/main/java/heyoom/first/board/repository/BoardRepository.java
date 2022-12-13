package heyoom.first.board.repository;

import java.util.List;
import java.util.Optional;

import heyoom.first.board.domain.Board;

public interface BoardRepository {
	
	Board postBoard(Board board);
	String deleteBoard(Long bbdId, Long ansId);
	Board updateBoard(Board board);
	List<Board> getBoards();
	Optional<Board> getBoard(Long id);

}
