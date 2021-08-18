package com.member.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = req.getParameter("seq");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�s��");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("AAAAAAAAA");
					failureView.forward(req, res);
					return;
				}
				
				Integer seq = null;
				try {
					seq = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�s���榡�����T");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("AAAAAAA");
					failureView.forward(req, res);
					return;
				}
				
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOne(seq);
				if (seq == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("AAAAAAAAA");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("memberVO", memberVO); 
				String url = "AAAAA";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("aaaaaaa");
				failureView.forward(req, res);
			}
		}
		
		if("getOneUpdate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer seq = new Integer(req.getParameter("seq"));
				
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOne(seq);
				req.setAttribute("memberVO", memberVO);
				String url = "/Member/Update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���" + e.getMessage());
				String url = "/Member/All.jsp";
				RequestDispatcher failView = req.getRequestDispatcher(url);
				failView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			MemberVO memberVO = new MemberVO();
			req.setAttribute("memberVO", memberVO);
			try {
				Integer seq = new Integer(req.getParameter("seq"));
				
				String id = req.getParameter("id");
				String nameReg = "^[\\w] {1,5}$";
				if(id.matches(nameReg) || id==null || id.length() == 0) {
					errorMsgs.add("�b��: �п�J���׬�1-5���^��r���μƦr");
				}
				
				String pswd = req.getParameter("pswd");
				if(pswd.matches(nameReg) || pswd==null || pswd.length() == 0) {
					errorMsgs.add("�K�X: �п�J���׬�1-5���^��r���μƦr");
				}
				memberVO.setSeq(seq);
				memberVO.setId(id);
				memberVO.setPswd(pswd);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO",memberVO);
					String url = "/Member/UpdateFail.jsp";
					RequestDispatcher failView = req.getRequestDispatcher(url);
					failView.forward(req, res);
				}
				
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.update(id, pswd, seq);
				req.setAttribute("memberVO",memberVO);
				String url = "/Member/UpdateSuccess.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("�ק��ƥ���" + e.getMessage()); 
				String url = "/Member/UpdateFail.jsp";
				RequestDispatcher failView = req.getRequestDispatcher(url);
				failView.forward(req, res);
			}
		}
		
		 if ("insert".equals(action)) { 
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					String id = req.getParameter("id");
					String nameReg = "^[\\w] {1,5}$";
					if(id.matches(nameReg) || id==null || id.length() == 0) {
						errorMsgs.add("�b��: �п�J���׬�1-5���^��r���μƦr");
					}
					
					String pswd = req.getParameter("pswd");
					if(pswd.matches(nameReg) || pswd==null || pswd.length() == 0) {
						errorMsgs.add("�K�X: �п�J���׬�1-5���^��r���μƦr");
					}

					MemberVO memberVO = new MemberVO();
					memberVO.setId(id);
					memberVO.setPswd(pswd);

					if (!errorMsgs.isEmpty()) {
						req.setAttribute("memberVO", memberVO); 
						RequestDispatcher failureView = req
								.getRequestDispatcher("AAAAAA");
						failureView.forward(req, res);
						return;
					}
					
					MemberService memberSvc = new MemberService();
					memberVO = memberSvc.insert(id, pswd);
					
					String url = "AAAAA";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);				
					
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("AAAA");
					failureView.forward(req, res);
				}
			}
	}

}
