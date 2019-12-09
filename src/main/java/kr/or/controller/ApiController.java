package kr.or.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import kr.or.dao.TotalDao;
import kr.or.domain.Customer;
import kr.or.domain.Total;
import kr.or.util.ExcelFile;

@Controller
public class ApiController {
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	TotalDao totalDao;

	@ResponseBody
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public List<Total> api(Model model, HttpServletRequest request, HttpSession session) {
		logger.info("api controller");

		List<Total> totalList = new ArrayList<Total>();
		if (request.getSession().getAttribute("Auth").equals("testId")) {
			totalList = totalDao.selectTotalList();
		}
		return totalList;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String uploadGet() {
		logger.info("uploadGet controller");

		return "api/upload";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadPost(@RequestParam String fileName) throws SQLException {
		logger.info("uploadPost controller");

		ExcelFile excelFile = new ExcelFile();
		List<Total> list = excelFile.readExcelFile(fileName);
		
		System.out.println(list);
		
		for(Total total : list) {
			System.out.println(total.getCustomerNumber());
			System.out.println(total.getCustomerName());
			Customer customer = new Customer();
			customer.setCustomerNumber(total.getCustomerNumber());
			customer.setCustomerName(total.getCustomerName());
			
			totalDao.updateCustomer(customer);
		}
		
		return "redirect:/api/upload";
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/upload", method = RequestMethod.POST)
//	public String uploadPost(MultipartHttpServletRequest request) {
//		logger.info("uploadPost controller");
//
//		MultipartFile excelFile =request.getFile("excelFile");
//        System.out.println("엑셀 파일 업로드 컨트롤러");
//        if(excelFile==null || excelFile.isEmpty()){
//            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
//        }
//        System.out.println(excelFile.getOriginalFilename());
//        File destFile = new File("c:\\"+excelFile.getOriginalFilename());
//        try{
//            excelFile.transferTo(destFile);
//        }catch(IllegalStateException | IOException e){
//            throw new RuntimeException(e.getMessage(),e);
//        }
//        
////        userService.excelUpload(destFile);
//        
//        
//        return "ssss";
//
//	}
}
