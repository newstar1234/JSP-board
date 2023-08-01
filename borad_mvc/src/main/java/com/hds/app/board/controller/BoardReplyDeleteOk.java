package com.hds.app.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hds.app.action.Action;
import com.hds.app.action.ActionForword;
import com.hds.app.board.dao.BoardReplyDAO;

public class BoardReplyDeleteOk implements Action {

	@Override
	public ActionForword execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		BoardReplyDAO dao = new BoardReplyDAO();
		int replyNum = Integer.parseInt(req.getParameter("replyNum"));
		
		dao.deleteReply(replyNum);
		return null;
	}

}
