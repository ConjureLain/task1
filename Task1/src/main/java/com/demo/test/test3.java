package com.demo.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;

public class test3 {
	public static void main(String[] args) {
		try {
			// 创建工作簿
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(
					new FileInputStream("C:/Users/Lain/Desktop/Marlabs/additional task/Java April 2020- Session 10 - Attendee Report.xlsx"));
			System.out.println("xssfWorkbook对象：" + xssfWorkbook);
			// 读取第一个工作表(这里的下标与list一样的，从0开始取，之后的也是如此)
			XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
			System.out.println("sheet对象：" + sheet);

			// 获取最后一行的num，即总行数。此处从0开始计数
			int maxRow = sheet.getLastRowNum();

			for (int row = 0; row <= maxRow; row++) {
				// 获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
				int maxCell = sheet.getRow(row).getLastCellNum();
				String cell6 = sheet.getRow(row).getCell(6) + "  ";
				System.out.print(cell6);
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}