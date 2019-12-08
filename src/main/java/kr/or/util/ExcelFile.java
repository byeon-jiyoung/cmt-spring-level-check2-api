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
import kr.or.domain.Total;

public class ExcelFile {
	private List<Total> list = new ArrayList<>();
	
	public List<Total> getList() {
		return list;
	}

	public void setList(List<Total> list) {
		this.list = list;
	}

	public List<Total> readExcelFile(String fileName) {
		try {
			FileInputStream file = new FileInputStream(new File("c:/shop/" + fileName));
			/* 파일의 확장자가 .XLS 라면 HSSFWorkbook에, .XLSX라면 XSSFWorkbook에 각각 초기화 한다. */
			HSSFWorkbook workbook = new HSSFWorkbook(file);
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
					System.out.println(j +" : " + valueStr);
					
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
			
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
