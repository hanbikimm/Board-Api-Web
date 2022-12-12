package heyoom.first.board.repository;

import java.util.List;

import heyoom.first.board.domain.Board;

public interface BoardRepository {
	
	Board postBoard(Board board);
	List<Board> getBoards();

}
