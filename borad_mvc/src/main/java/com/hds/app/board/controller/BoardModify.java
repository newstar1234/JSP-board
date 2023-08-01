package com.hds.app.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hds.app.action.Action;
import com.hds.app.action.ActionForword;
import com.hds.app.board.dao.BoardDAO;

public class BoardModify implements Action {

	@Override
	public ActionForword execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int boardNum = Integer.parseInt(req.getParameter("boardNum"));
		ActionForword forward = new ActionForword();
		
		BoardDAO bDao = new BoardDAO();
		
		req.setAttribute("board", bDao.getDetail(boardNum));
		
		forward.setRedirect(false);
		forward.setPath("/app/board/boardModify.jsp");
		
		return forward;
	}

}
