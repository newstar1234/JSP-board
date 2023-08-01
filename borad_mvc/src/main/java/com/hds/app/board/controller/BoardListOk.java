package com.hds.app.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hds.app.action.Action;
import com.hds.app.action.ActionForword;
import com.hds.app.board.dao.BoardDAO;

public class BoardListOk implements Action {

	@Override
	public ActionForword execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		BoardDAO dao = new BoardDAO();
		ActionForword forward = new ActionForword();
		
		String temp = req.getParameter("page");
		int page = temp == null ? 1: Integer.parseInt(temp);
		int pageSize = 10;
		int totalCount = dao.getTotal();
		
		int endRow = page * pageSize;
		int startRow = endRow - (pageSize - 1);
		
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + 9;
		int realEndPage = (int)(Math.ceil((double)totalCount / pageSize));
		
		endPage = endPage > realEndPage ? realEndPage : endPage;
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("realEndPage", realEndPage);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("nowPage", page);
		req.setAttribute("boardList", dao.getList(startRow, endRow));
		
		forward.setRedirect(false);
		forward.setPath("/app/board/boardList.jsp");

		
		return forward;
	}

}
