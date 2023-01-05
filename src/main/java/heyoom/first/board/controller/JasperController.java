package heyoom.first.board.controller;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import heyoom.first.board.domain.BoardStatus;
import heyoom.first.board.service.BoardService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/bbd")
public class JasperController {

		private final BoardService boardService;
		
		@Autowired
		 public JasperController(BoardService boardService) {
			this.boardService = boardService;
		 }
	
	// jasper 출력
		@GetMapping(value="generate/{date}")
		public ResponseEntity<byte[]> generateStatus(@PathVariable("date") String date) throws Exception, JRException {
			List<BoardStatus> result = boardService.getStatus(date);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// string -> date
			Date typeDate = dateFormat.parse(date);
			// date -> calender
			Calendar typeCalendar = Calendar.getInstance();
			typeCalendar.setTime(typeDate);
			// calender + 6day = Sunday
			typeCalendar.add(Calendar.DATE, 6);
			// + Sunday -> string
			String typeString = dateFormat.format(typeCalendar.getTime());

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(date).append(" ~ ").append(typeString);
			String dateForTitle = stringBuilder.toString();
			
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(result);
			JasperReport compileReport = JasperCompileManager.compileReport(new FileInputStream("src/main/resources/StatusReport.jrxml"));
			HashMap<String, Object> map =new HashMap<>();
			map.put("dateForTitle", dateForTitle);
			
			JasperPrint report = JasperFillManager.fillReport(compileReport, map, beanCollectionDataSource);
//			JasperExportManager.exportReportToPdfFile(report, "BoardStatus.pdf");
			
			byte[] data = JasperExportManager.exportReportToPdf(report);
			HttpHeaders headers = new HttpHeaders();
			headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=BoardStatus.pdf");
			
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
		}
}
