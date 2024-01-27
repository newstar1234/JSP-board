package com.hds.app.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hds.app.action.Action;
import com.hds.app.action.ActionForword;

public class fileDownloadOk implements Action {

	@Override
	public ActionForword execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String saveFolder = "C:\\2-jsp_ksb\\workspace\\borad_mvc\\WebContent\\upload";
		String fileName = req.getParameter("fileName");
		boolean check = true;
		
		InputStream in = null;
		OutputStream out = null;
		
		File file = null;
		
		try {
			file = new File(saveFolder, fileName);
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			check = false;
			e.printStackTrace();
		}
		
		resp.setContentType("application/ocatat-stream"); // 파일 다운로드를 위한 전송할 데이터 타입 설정
		resp.setHeader("Content-Description", "JSP Generated Data"); // 헤더에 담을 데이터의 내용에 대한 설명 수정
		if(check) {
			try {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				resp.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
				resp.setHeader("Content-Type", "application/ocatat-stream;charset=utf-8");
				resp.setHeader("Content-length", ""+file.length());
				
				out = resp.getOutputStream();
				byte[] b = new byte[(int)(file.length())];
				int data = 0;
				
				while((data = in.read(b))>0) {
					out.write(b, 0, data);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(in != null) {
						in.close();
					}
					if(out != null) {
						out.close();
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		return null;
	}

}
