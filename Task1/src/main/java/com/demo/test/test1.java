package com.demo.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;

public class test1 {
	public static void main(String[] args) {
		try {
			// 创建工作簿
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(
					new FileInputStream("C:/Users/Lain/Desktop/Marlabs/additional task/ContactsList.xlsx"));
			System.out.println("xssfWorkbook对象：" + xssfWorkbook);
			// 读取第一个工作表(这里的下标与list一样的，从0开始取，之后的也是如此)
			XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
			System.out.println("sheet对象：" + sheet);

//            //获取第一行的数据
//            XSSFRow row0 = sheet.getRow(0);
//            System.out.println("row对象：" + row0);
//            //获取该行第一个单元格的数据
//            XSSFCell cell0 = row0.getCell(0);
//            System.out.println("cello对象：" + cell0);

			// 获取最后一行的num，即总行数。此处从0开始计数
			int maxRow = sheet.getLastRowNum();

			for (int row = 0; row <= maxRow; row++) {
				// 获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
				int maxCell = sheet.getRow(row).getLastCellNum();
				for (int cell = 0; cell < maxCell; cell++) {
					System.out.print(sheet.getRow(row).getCell(cell) + "  ");
				}
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}