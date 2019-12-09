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
import kr.or.domain.Customer;
import kr.or.domain.Total;
import kr.or.service.TotalService;
import kr.or.util.ExcelUpload;

@Controller
public class ApiController {
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Autowired
	TotalService totalService;

	@ResponseBody
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public List<Total> api(Model model, HttpServletRequest request, HttpSession session) {
		logger.info("api controller");

		List<Total> totalList = new ArrayList<Total>();
		if (request.getSession().getAttribute("Auth").equals("testId")) {
			totalList = totalService.selectTotalList();
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

		ExcelUpload excelFile = new ExcelUpload();
		List<Total> totalList = excelFile.readExcelFile(fileName);
		
		for(Total total : totalList) {
			Customer customer = new Customer();
			customer.setCustomerNumber(total.getCustomerNumber());
			customer.setCustomerName(total.getCustomerName());
			
			totalService.updateCustomer(customer);
		}
		
		return "api/upload";
	}
}
