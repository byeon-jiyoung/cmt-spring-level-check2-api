package kr.or.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import kr.or.domain.Total;

public class ExcelUpload {
	private List<Total> list = new ArrayList<>();
	
	public List<Total> getList() {
		return list;
	}

	public void setList(List<Total> list) {
		this.list = list;
	}

	public List<Total> readExcelFile(String filePath) {
		try {
			File file = new File("c:/shop/" + filePath);
			FileInputStream fileInputStream = new FileInputStream(file);
			Workbook workbook = null;
			
			// 파일의 확장자가 .XLS 라면 HSSFWorkbook에, .XLSX라면 XSSFWorkbook에 각각 초기화 시켜야 한다.
			if (file.getName().toLowerCase().endsWith("xlsx")) { // 엑셀 파일의 확장자(버전)에 따라서 생성해야 할 Workbook 객체가 다르다.
				workbook = new XSSFWorkbook(fileInputStream);
			}else{
				workbook = new HSSFWorkbook(fileInputStream);
			}
			Sheet sheet = workbook.getSheetAt(0); //첫번째 Sheet

			int rowSize = sheet.getLastRowNum(); // 행의 총 개수 (행은 0부터 시작함)
			for(int i=1; i<=rowSize; i++){ // i를 1부터 시작해야 두번째 행부터 데이터가 입력된다.
				Row row = sheet.getRow(i);
				int cellLength = (int) row.getLastCellNum(); // 열의 총 개수
				String valueStr = ""; // 엑셀에서 뽑아낸 데이터를 담아놓을 String 변수 선언 및 초기화
				
				Total total = new Total();
				for(int j=0; j<cellLength; j++){
					Cell cell = row.getCell(j);
					valueStr = cell.getStringCellValue();
					
					if(j==0) {
						total.setOrderNumber(Integer.parseInt(valueStr));
					} else if(j==1) {
						total.setCustomerNumber(Integer.parseInt(valueStr));
					} else if(j==2) {
						total.setCustomerName(valueStr);
					} else if(j==3) {
						total.setProductNumber(Integer.parseInt(valueStr));
					} else if(j==4) {
						total.setProductName(valueStr);
						list.add(total);
					}
				}//cell반복문 끝
			}//Rows반복문 끝
			
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
