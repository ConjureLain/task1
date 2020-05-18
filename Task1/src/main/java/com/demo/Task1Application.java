package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



@SpringBootApplication
public class Task1Application {

	public int calDur(String num) {
		char[] nu = num.toCharArray();
		String nownum = "";
		boolean flag = false;
		int res = 0;

		for (int i = 0; i < nu.length; i++) {
			if (Integer.valueOf(nu[i]) == '(')
				flag = true;
			if (Integer.valueOf(nu[i]) == ')')
				flag = false;
			if (flag == true) {
				if (Integer.valueOf(nu[i]) >= 48 && Integer.valueOf(nu[i]) <= 57)
					nownum = nownum + nu[i];
				else if (Integer.valueOf(nu[i]) == 'h') {
					res += Integer.parseInt(nownum) * 60;
					nownum = "";
				} else if (Integer.valueOf(nu[i]) == 'm') {
					res += Integer.parseInt(nownum) * 1;
					nownum = "";
				}
			}
		}
		//System.out.println(res + " minutes");
		return res;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Task1Application.class, args);
		
		try {
			// Reading ContactsList.xlsx Sheet1
			XSSFWorkbook xssfWorkbook1 = new XSSFWorkbook(
					new FileInputStream("./src/main/resources/ContactsList.xlsx"));
			XSSFSheet sheet1 = xssfWorkbook1.getSheetAt(0);

			// Reading Attendee Report.xlsx Sheet1
			XSSFWorkbook xssfWorkbook2 = new XSSFWorkbook(new FileInputStream(
					"./src/main/resources/Java April 2020- Session 10 - Attendee Report.xlsx"));
			XSSFSheet sheet2 = xssfWorkbook2.getSheetAt(0);
			
			//Creating Output Excel
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sh = wb.createSheet();

			//
			int maxRow1 = sheet1.getLastRowNum();
			int maxRow2 = sheet2.getLastRowNum();
			int count = 0;
			
			//for each row of Attendee Report.xlsx
			for (int row2 = 8; row2 <= maxRow2; row2++) {
				String cell6 = sheet2.getRow(row2).getCell(6) + "";
				String cell2 = sheet2.getRow(row2).getCell(2) + "";
				String cell3 = sheet2.getRow(row2).getCell(3) + "";
				
				//Calculating duration
				Task1Application funs = new Task1Application();
				int res = funs.calDur(cell6);
				
				//if less than 150min(2h30min) then return his/her first name and last name
				if(res<=150) {
					System.out.println(res + " minutes");
					System.out.println(cell3 +" " + cell2);
					Row row = sh.createRow(count);
					count++;
					//Searching the same first&last name in ContactList.xlsx row by row
					for (int row1 = 0; row1 <= maxRow1; row1++) {
						int maxCell1 = sheet1.getRow(row1).getLastCellNum();
						String str1 = sheet1.getRow(row1).getCell(4) + "";
						String str2 = sheet1.getRow(row1).getCell(3) + "";
						if (str1.contentEquals(cell2) && str2.contentEquals(cell3)) {
							//print out those results into the AbsList.xlsx
							for (int cell = 0; cell < maxCell1; cell++) {
								System.out.print(sheet1.getRow(row1).getCell(cell) + "  ");
								Cell c = row.createCell(cell);
								c.setCellValue(sheet1.getRow(row1).getCell(cell)+"");
							}
							System.out.println();
							System.out.println();
						}
					}
				}
				
			}
			//writing the file.
			wb.write(new FileOutputStream("./src/main/resources/AbsList.xlsx"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
