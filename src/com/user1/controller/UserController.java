package com.user1.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user1.model.UserService;
import com.user1.model.UserVO;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserController() {
        super();
    }

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getOneUpdate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer user_no = new Integer(req.getParameter("user_no"));
				
				UserService userSvc = new UserService();
				UserVO userVO = userSvc.getOne(user_no);
				
				req.setAttribute("userVO", userVO);
				String url = "/User/Q2_1.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
				String url = "/User/Q2_4.jsp";
				RequestDispatcher failView = req.getRequestDispatcher(url);
				failView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			UserVO userVO = new UserVO();
			req.setAttribute("userVO", userVO);
			try {
				Integer user_no = new Integer(req.getParameter("user_no"));
				
				String id = req.getParameter("id");
				String nameReg = "^[\\w] {1,5}$";
				if(id.matches(nameReg) || id==null || id.length() == 0) {
					errorMsgs.add("帳號: 請輸入長度為1-5之英文字母或數字");
				}
				
				String pswd = req.getParameter("pswd");
				if(pswd.matches(nameReg) || pswd==null || pswd.length() == 0) {
					errorMsgs.add("密碼: 請輸入長度為1-5之英文字母或數字");
				}
				
				userVO.setUser_no(user_no);
				userVO.setId(id);
				userVO.setPswd(pswd);
				
				if(!errorMsgs.isEmpty()) {
//					req.setAttribute("userVO",userVO);
					String url = "/User/Q2_1.jsp";
					RequestDispatcher failView = req.getRequestDispatcher(url);
					failView.forward(req, res);
				}
				
				UserService userSvc = new UserService();
				userVO = userSvc.update(id, pswd, user_no);
				
//				req.setAttribute("userVO", userVO);
				String url = "/User/Q2_2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage()); 
				String url = "/User/Q2_3.jsp";
				RequestDispatcher failView = req.getRequestDispatcher(url);
				failView.forward(req, res);
			}
		}
		
		
		
	}

}
