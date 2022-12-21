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
	
	// �� �Խñ� ����
	@GetMapping(value="/total")
	public int totalBoard() {
		return boardService.getTotal();
	}
	
	// ���� ���
	@GetMapping(value="/questions")
	@ResponseBody
	public List<Board> questionList() {
		return boardService.getQuestionList();
	}
	
	// ��� ���
	@GetMapping(value="/answers/{id}")
	public List<Board> answerList(@PathVariable Long id){
		return boardService.getAnswerList(id);
	}
	
	// ���� ���
	@PostMapping(value="/question")
	public Board registeQuestion(@RequestBody Board board) {
		return boardService.createQuestion(board);
	}
	
	// �亯 ���
	@PostMapping(value="/answer")
	public Board registerAnswer(@RequestBody Board board) {
		return boardService.createAnswer(board);
	}
	
	// �Խñ� ��
	@GetMapping(value="board/{bbdId}")
	public Optional<Board> boardDetail(@PathVariable("bbdId") Long bbdId, Long ansId) {
		return boardService.getBoardDetail(bbdId, ansId);
	}
	
	// �Խñ� ����
	@PutMapping(value="/board/{id}")
	public Board ModifyBoard(@PathVariable Long id, @RequestBody Board board) {
		return boardService.editBoard(board);
	}
	
	// �Խñ� ����
	@DeleteMapping(value="board/{bbdId}")
	public String removeBoard(@PathVariable("bbdId") Long bbdId, Long ansId) {
		return boardService.eraseBoard(bbdId, ansId);
	}
	
	// ��ȸ��
//	@PutMapping(value="/board/")
	
	
	
	

}
