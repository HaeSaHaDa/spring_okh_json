package edu.kosmo.ex;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.kosmo.ex.vo.SampleVO;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@RestController
@Log4j
@RequestMapping("/sample")
public class SampleController {
	
	 @GetMapping(value ="/getText", produces = "text/plain; charset=UTF-8")
	   public String getText() {
	      
	      log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
	      
	      return "안녕하세요!! 또 만났군요 ~~";
	   }
	   @GetMapping(value = "/getSample")
	   public SampleVO getSample2() {
	      return new SampleVO(113, "로켓", "라쿤");
	   }
	   @GetMapping(value = "/check", params = { "height", "weight" })//height, weight를 get방식으로 보내는 것.
	   public ResponseEntity<SampleVO> check(Double height, Double weight) {

	      SampleVO vo = new SampleVO(0, "" + height, "" + weight);

	      ResponseEntity<SampleVO> result = null;

	      if (height < 150) {
	         result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo); //body에 vo를 넣어 보내겠다.
	      } else {
	         result = ResponseEntity.status(HttpStatus.OK).body(vo); //HttpStatus는 head에 있는 번호를 같이 실어 보내겠다.
	      }

	      return result;
	   }
}
