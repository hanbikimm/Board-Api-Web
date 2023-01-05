package heyoom.first.board.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import heyoom.first.board.domain.Board;
import heyoom.first.board.domain.BoardStatus;
import heyoom.first.board.service.BoardService;

@RestController
@RequestMapping("/bbd")
public class BoardController {
	
	private final BoardService boardService;
	
	@Autowired
	 public BoardController(BoardService boardService) {
		this.boardService = boardService;
	 }
	
	// 총 게시물 수
	@GetMapping(value="/total")
	public int totalBoard() {
		return boardService.getTotal();
	}
	
	// 질문 목록
	@GetMapping(value="/questions")
	@ResponseBody
	public List<Board> questionList() {
		return boardService.getQuestionList();
	}
	
	// 답변 목록
	@GetMapping(value="/answers/{id}")
	public List<Board> answerList(@PathVariable Long id){
		return boardService.getAnswerList(id);
	}
	
	// 검색 목록
	@GetMapping(value="/boards/{searchWord}")
	public List<Board> searchBoard(@PathVariable("searchWord") String searchWord, Long value){
		return boardService.getSearchList(searchWord, value);
	}
	
	// 질문 등록
	@PostMapping(value="/question", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Board registeQuestion(@RequestPart Board board) {
		return boardService.createQuestion(board);
	}
	
	// 답변 등록
	@PostMapping(value="/answer")
	public Board registerAnswer(@RequestBody Board board) {
		return boardService.createAnswer(board);
	}
	
	// 게시글 상세
	@GetMapping(value="board/{bbdId}")
	public Optional<Board> boardDetail(@PathVariable("bbdId") Long bbdId, Long ansId) {
		return boardService.getBoardDetail(bbdId, ansId);
	}
	
	// 게시글 수정
	@PutMapping(value="/board/{id}")
	public Board ModifyBoard(@PathVariable Long id, @RequestBody Board board) {
		return boardService.editBoard(board);
	}
	
	// 게시글 삭제
	@DeleteMapping(value="board/{bbdId}")
	public String removeBoard(@PathVariable("bbdId") Long bbdId, Long ansId) {
		return boardService.eraseBoard(bbdId, ansId);
	}
	
	// 조회수
	@PutMapping(value="board/view")
	public String updateView(Long bbdId, Long ansId) {
		return boardService.updateDayViews(bbdId, ansId);
	}
	
	// 현황판
	@GetMapping(value="status/{date}")
	public List<BoardStatus> statusOfBoard(@PathVariable("date") String date){
		return boardService.getStatus(date);
	}
	

}
