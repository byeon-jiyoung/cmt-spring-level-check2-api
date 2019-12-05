package kr.or.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import kr.or.dao.TotalDao;
import kr.or.domain.Total;

@Controller
public class ApiController {
	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
	@Autowired
	TotalDao ordersDao;
	
	@ResponseBody
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public List<Total> api(Model model, HttpServletRequest request) {
		logger.info("api controller");
		
System.out.println(request.getRequestURI());
System.out.println("api");
		
		model.addAttribute("authority", "authority");

		List<Total> totalList = ordersDao.selectTotalList();
		
        return totalList;
	}
}
