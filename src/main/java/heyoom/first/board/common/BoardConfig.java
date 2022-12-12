package heyoom.first.board.common;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import heyoom.first.board.repository.BoardRepository;
import heyoom.first.board.repository.BoardRepositoryImpl;
import heyoom.first.board.service.BoardService;

@Configuration
public class BoardConfig {
	
	private final DataSource dataSource;
	
	public BoardConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	 public BoardService boardService() {
	 return new BoardService(boardRepository());
	 }
	
	 @Bean
	 public BoardRepository boardRepository() {
	return new BoardRepositoryImpl(dataSource);
	 }
}
