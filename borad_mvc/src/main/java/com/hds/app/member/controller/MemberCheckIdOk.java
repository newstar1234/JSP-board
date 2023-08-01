package com.hds.app.member.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.hds.app.action.Action;
import com.hds.app.action.ActionForword;
import com.hds.app.member.dao.MemberDAO;

public class MemberCheckIdOk implements Action {

	@Override
	public ActionForword execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String memberId = req.getParameter("memberId");
		MemberDAO dao = new MemberDAO();
		JSONObject obj = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		if(dao.checkId(memberId)) {
			obj.put("status", "not-ok");//아이디 중복검사 -->중복이 없음
		}else {
			obj.put("status", "ok");
		}
		
		out.println(obj.toJSONString());
		out.close();
		return null;
	}
	
}
