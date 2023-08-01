package com.hds.app.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hds.app.action.Action;
import com.hds.app.action.ActionForword;
import com.hds.app.board.dao.BoardDAO;
import com.hds.app.files.dao.FilesDAO;

public class BoardViewOk implements Action {

	@Override
	public ActionForword execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		BoardDAO bDao = new BoardDAO();
		FilesDAO fDao = new FilesDAO();
		ActionForword forward = new ActionForword();
		
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		req.setAttribute("board", bDao.getDetail(boardNum));
		req.setAttribute("files", fDao.getFiles(boardNum));
		
		bDao.updateReadCount(boardNum);
		
		forward.setRedirect(false);
		forward.setPath("/app/board/boardView.jsp");
		
		
		return forward;
	}

}
