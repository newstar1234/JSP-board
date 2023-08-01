package com.hds.app.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hds.app.action.Action;
import com.hds.app.action.ActionForword;
import com.hds.app.board.dao.BoardDAO;
import com.hds.app.board.vo.BoardVO;
import com.hds.app.files.dao.FilesDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteOk implements Action {

	@Override
	public ActionForword execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String saveFolder = "C:\\jsp_ksb\\workspace\\borad_mvc\\WebContent\\upload";
		int fileSize = 1024 * 1024 * 5; //5메가바이트
		
		BoardDAO bDao = new BoardDAO();
		FilesDAO fDao = new FilesDAO();
		BoardVO board = new BoardVO();
		
		ActionForword forward = new ActionForword();
		
		MultipartRequest multi = null;
		multi = new MultipartRequest(req, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		board.setBoardTitle(multi.getParameter("boardTitle"));
		board.setBoardContent(multi.getParameter("boardContent"));
		board.setBoardId(multi.getParameter("boardId"));
		
		bDao.insertBoard(board);
		fDao.insertFile(multi, bDao.getSeq());
		
		forward.setRedirect(true);
		forward.setPath(req.getContextPath() + "/board/BoardListOk.bo");
		
		
		return forward;
	}

}
