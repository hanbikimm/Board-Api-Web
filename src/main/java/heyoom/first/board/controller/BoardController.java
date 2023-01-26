package heyoom.first.board.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import heyoom.first.board.domain.Board;
import heyoom.first.board.domain.BoardStatus;
import heyoom.first.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bbd")
public class BoardController {
	
	private final BoardService boardService;
	
	@Value("${file.dir}")
	private String fileDir;
	
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
	@PostMapping(value="/question/contents")
	public Board registeQuestion(@RequestBody Board board) {
		return boardService.createContentsForQuestion(board);
	}
	
	// 답변 등록
	@PostMapping(value="/answer/contents")
	public Board registerAnswer(@RequestBody Board board) {
		return boardService.createContentsForAnswer(board);
	}
	
	// 사진 등록
	@PostMapping(value="/board/file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public String registeImg(@RequestParam MultipartFile img,
							@RequestParam String bbd_title,
							@RequestParam Long img_seq) throws Exception, IOException {
		return boardService.createImgForBoard(img, img_seq, bbd_title);
	}
	
	// 게시글 상세
	@GetMapping(value="board/{bbdId}")
	public Optional<Board> boardDetail(@PathVariable("bbdId") Long bbdId, Long ansId) {
		return boardService.getBoardDetail(bbdId, ansId);
	}
	
//	//이미지 불러오기
//	@ResponseBody
//	@GetMapping(value = "images/{fileDate}/{boardId}/{fileName}")
//	public Resource downloadImage(@PathVariable String fileDate, @PathVariable String boardId, @PathVariable String fileName) throws MalformedURLException {
//		log.info("filePath= {}", fileName);
//		return new UrlResource("file:" + fileDir + fileDate + "/" + boardId + "/" + fileName);
//	}
	
	// 첨부파일 다운로드
	@GetMapping("/attach/{fileDate}/{boardId}/{fileName}")
	public ResponseEntity<Resource> downloadAttach(@PathVariable String fileDate, @PathVariable String boardId, @PathVariable String fileName) throws MalformedURLException{
		
		UrlResource urlResource = new UrlResource("file:" + fileDir + fileDate + "/" + boardId + "/" + fileName);
		
		log.info("fileDate = {}", fileDate);
		log.info("boardId= {}", boardId);
		log.info("fileName= {}", fileName);
		
		String encodedUploadFileName = UriUtils.encode(fileName, StandardCharsets.UTF_8);
		String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
				.body(urlResource);
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + fileName);
//        File file = new File(fileDir + fileDate + "/" + boardId + "/" + fileName);
//        log.info("fullPath= {}", fileDir + fileDate + "/" + boardId + "/" + fileName);
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.IMAGE_JPEG)
//                .body(resource);
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
