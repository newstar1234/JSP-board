package com.hds.app.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hds.app.action.Action;
import com.hds.app.action.ActionForword;
import com.hds.app.member.dao.MemberDAO;

public class MemberFindIdOk implements Action {

	@Override
	public ActionForword execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ActionForword forward = new ActionForword();
		String memberEmail = req.getParameter("memberEmail");
		String memberPw = req.getParameter("memberPw");
		
		MemberDAO dao = new MemberDAO();
		req.setAttribute("memberId", dao.findId(memberEmail, memberPw));
		forward.setRedirect(false);
		forward.setPath("/app/member/showId.jsp");
		
		return forward;
	}

}
