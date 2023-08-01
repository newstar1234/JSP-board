package com.hds.app.board.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hds.app.action.Action;
import com.hds.app.action.ActionForword;
import com.hds.app.board.dao.BoardDAO;
import com.hds.app.files.dao.FilesDAO;
import com.hds.app.files.vo.FilesVO;

public class BoardDeleteOk implements Action {

	@Override
	public ActionForword execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String saveFolder = "C:\\jsp_ksb\\workspace\\borad_mvc\\WebContent\\upload";
		FilesDAO fDao = new FilesDAO();
		BoardDAO bDao = new BoardDAO();
		ActionForword forward = new ActionForword();
		
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		for(FilesVO file : fDao.getFiles(boardNum)) {
			File f = new File(saveFolder, file.getFileName());
			if(f.exists()) {
				f.delete();
			}
		}
		fDao.deleteFiles(boardNum);
		bDao.deleteBoard(boardNum);
		
		forward.setRedirect(false);
		forward.setPath("/board/BoardListOk.bo");
		
		return forward;
	}

}
