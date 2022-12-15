package heyoom.first.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	// �Խñ� ���
	@GetMapping(value="/boards")
	@ResponseBody
	public List<Board> boardList() {
		return boardService.getBoardList();
	}
	
	// �Խñ� ���
	@PostMapping(value="/boards")
	public Board registerBoard(@RequestBody Board board) {
		return boardService.createBoard(board);
	}
	
	// �Խñ� ����
	@PutMapping(value = "/board/{id}")
	public Board ModifyBoard(@PathVariable Long id, @RequestBody Board board) {
		return boardService.editBoard(board);
	}
	
	// �Խñ� ����
	@DeleteMapping(value = "board/{bbdId}")
	public String removeBoard(@PathVariable("bbdId") Long bbdId, Long ansId) {
		return boardService.eraseBoard(bbdId, ansId);
	}
	
	// �Խñ� ��
	@GetMapping(value = "board/{id}")
	public Optional<Board> boardDetail(@PathVariable Long id) {
		return boardService.getBoardDetail(id);
	}

}
