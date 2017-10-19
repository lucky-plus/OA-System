package com.oa.message.web;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oa.staff.entity.dto.PostUserDTO;
import com.oa.staff.service.IStaffService;

@Controller
public class OutProductController {
	
	@Autowired
	private IStaffService staffService;
	
	
	//使用模版打印
	@RequestMapping("/download")
	public String print(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//通用变量
		int rowNo = 0, cellNo = 1;
		Row nRow = null;
		Cell nCell = null;
		
		//1.创建工作簿
		Workbook wb = new HSSFWorkbook();
		
		//2.创建工作表
		Sheet sheet = wb.createSheet();
		
		//设置列宽   本身是个bug会出现一些偏差  
		sheet.setColumnWidth(cellNo++, 26*256);
		sheet.setColumnWidth(cellNo++, 11*256);
		sheet.setColumnWidth(cellNo++, 29*256);
		sheet.setColumnWidth(cellNo++, 12*256);
		sheet.setColumnWidth(cellNo++, 15*256);
		sheet.setColumnWidth(cellNo++, 10*256);
		sheet.setColumnWidth(cellNo++, 10*256);
		sheet.setColumnWidth(cellNo++, 8*256);
		cellNo = 1;   //重置
		
		
		//3.创建行对象
		//=========================================大标题=============================
		//创建行对象
		nRow = sheet.createRow(rowNo++);
		//设置行高
		nRow.setHeightInPoints(36);
		//创建单元格对象
		nCell = nRow.createCell(cellNo);
		
		//合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 6));
		
		//设置单元格的内容
		nCell.setCellValue("通讯录");
		
		//设置单元格样式
		nCell.setCellStyle(this.bigTitle(wb));
		
		
		//=======================================小标题=================================
		String titles[] = {"联系人","部门","职位","联系电话","联系邮箱","QQ"};
		
		
		//创建小标题的行对象
		nRow = sheet.createRow(rowNo++);
		nRow.setHeightInPoints(26.25f);
		
		//创建单元格对象，并设置内容 ，并设置样式
		for(String title : titles) {
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(title);
			nCell.setCellStyle(this.title(wb));
		}
		
		
		//=======================================数据输出=================================================
		List<PostUserDTO> userList = staffService.findAll();
		
		for(PostUserDTO user : userList){
			nRow = sheet.createRow(rowNo++);	//产生数据行
			nRow.setHeightInPoints(24); 		//设置行高
			
			cellNo = 1;
			nCell = nRow.createCell(cellNo++);	//产生单元格对象
			nCell.setCellValue(user.getRealName());	//联系人
			nCell.setCellStyle(this.text(wb));	//设置文本样式
			
			nCell = nRow.createCell(cellNo++);	//产生单元格对象
			nCell.setCellValue(user.getDeptName());	//所属部门
			nCell.setCellStyle(this.text(wb));	//设置文本样式
			
			nCell = nRow.createCell(cellNo++);	//产生单元格对象
			nCell.setCellValue(user.getPostName()); //职务名称
			nCell.setCellStyle(this.text(wb));	//设置文本样式
			
			nCell = nRow.createCell(cellNo++);	//产生单元格对象
			nCell.setCellValue(user.getMobilePhone());	//联系电话 
			nCell.setCellStyle(this.text(wb));	//设置文本样式
			
			nCell = nRow.createCell(cellNo++);	//产生单元格对象
			nCell.setCellValue(user.getMail());	//联系邮箱
			nCell.setCellStyle(this.text(wb));	//设置文本样式
			
			nCell = nRow.createCell(cellNo++);	//产生单元格对象
			nCell.setCellValue(user.getQq_number());	//QQ
			nCell.setCellStyle(this.text(wb));	//设置文本样式
		}

		
		//======================================输出到客户端（下载）========================================
		ByteArrayOutputStream baos = new ByteArrayOutputStream();//流  内存中的缓存区
		wb.write(baos);//将excel表格中的内容输出到缓存
		baos.close();//刷新缓存

		//通知浏览器以下载方式打开
		response.setContentType("application/force-download");// 设置强制下载不打开
		response.addHeader("Content-Disposition","attachment;fileName=" + "message.xls");//设文件名
		response.setContentLength(baos.size());
		
		ServletOutputStream outputstream = response.getOutputStream();	//取得输出流
		baos.writeTo(outputstream);										//写到输出流
		baos.close();													//关闭
		outputstream.flush();											//刷数据
		
		return null;
	}
	
	//大标题的样式
	public CellStyle bigTitle(Workbook wb){
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short)16);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);					//字体加粗
		
		style.setFont(font);
		
		style.setAlignment(CellStyle.ALIGN_CENTER);					//横向居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中
		
		return style;
	}
	
	//小标题的样式
	public CellStyle title(Workbook wb){
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short)12);
		
		style.setFont(font);
		
		style.setAlignment(CellStyle.ALIGN_CENTER);					//横向居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中
		
		style.setBorderTop(CellStyle.BORDER_THIN);					//上细线
		style.setBorderBottom(CellStyle.BORDER_THIN);				//下细线
		style.setBorderLeft(CellStyle.BORDER_THIN);					//左细线
		style.setBorderRight(CellStyle.BORDER_THIN);				//右细线
		
		return style;
	}
		
	//文字样式
	public CellStyle text(Workbook wb){
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("Times New Roman");
		font.setFontHeightInPoints((short)10);
		
		style.setFont(font);
		
		style.setAlignment(CellStyle.ALIGN_LEFT);					//横向居左
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中
		
		style.setBorderTop(CellStyle.BORDER_THIN);					//上细线
		style.setBorderBottom(CellStyle.BORDER_THIN);				//下细线
		style.setBorderLeft(CellStyle.BORDER_THIN);					//左细线
		style.setBorderRight(CellStyle.BORDER_THIN);				//右细线
		
		return style;
	}
	
}
