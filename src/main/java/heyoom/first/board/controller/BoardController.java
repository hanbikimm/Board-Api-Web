package heyoom.first.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import heyoom.first.board.domain.Board;
import heyoom.first.board.service.BoardService;

@RestController
@RequestMapping("/bbd")
public class BoardController {
	
	private final BoardService boardService;
	
	@Autowired
	 public BoardController(BoardService boardService) {
		this.boardService = boardService;
	 }
	
	// 게시글 목록
	@GetMapping(value="/boards")
	@ResponseBody
	public List<Board> boardList() {
		return boardService.getBoardList();
	}
	
	// 게시글 등록
	@PostMapping(value="/boards")
	public Board registerBoard(@RequestBody Board board) {
		return boardService.createBoard(board);
	}

}
