package com.hds.app.board.controller;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hds.app.action.Action;
import com.hds.app.action.ActionForword;
import com.hds.app.board.dao.BoardDAO;
import com.hds.app.board.vo.BoardVO;
import com.hds.app.files.dao.FilesDAO;
import com.hds.app.files.vo.FilesVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardModifyOk implements Action{
	@Override
	public ActionForword execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String saveFolder = "C:\\2-jsp_ksb\\workspace\\borad_mvc\\WebContent\\upload";
		int fileSize = 5 * 1024 * 1024;
		BoardVO board = new BoardVO();
		BoardDAO  bDao = new BoardDAO();
		FilesDAO fDao = new FilesDAO();
		ActionForword forward = new ActionForword();
		MultipartRequest multi = null;
		
		multi = new MultipartRequest(req, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		int boardNum = Integer.parseInt(multi.getParameter("boardNum"));
		
		for(FilesVO file : fDao.getFiles(boardNum)) {
			File f = new File(saveFolder, file.getFileName());
			if(f.exists()) {
				f.delete();
			}
		}
		
		//DB에서 삭제
		fDao.deleteFiles(boardNum);
		fDao.insertFile(multi, boardNum);
		
		//게시글 정보 수정
		board.setBoardNum(boardNum);
		board.setBoardTitle(multi.getParameter("boardTitle"));
		board.setBoardContent(multi.getParameter("boardContent"));
		bDao.updateBoard(board);
		
		forward.setRedirect(false);
		forward.setPath("/board/BoardViewOk.bo?boardNum=" + boardNum);
		return forward;
	}
}
