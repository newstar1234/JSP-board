package com.hds.app.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hds.app.action.Action;
import com.hds.app.action.ActionForword;

public class MemberLogoutOk implements Action {

	@Override
	public ActionForword execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ActionForword forward= new ActionForword();
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		forward.setRedirect(true);
		forward.setPath(req.getContextPath()+"/member/MemberLogin.me");

		
		
		return forward;
	} 

}
