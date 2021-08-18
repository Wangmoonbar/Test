package com.animal.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.animal.model.*;


@WebServlet("/AnimalServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class AnimalServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入動物ID");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/anilmal/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer id = null;
				try {
					id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("動物ID格式不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/animal/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AnimalService animalSvc = new AnimalService();
				AnimalVO animalVO = animalSvc.getOneAnimal(id);
				if (animalVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/animal/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("animalVO", animalVO);
				String url = "/animal/listOneAnimal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/animal/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer id = new Integer(req.getParameter("id"));
				
				/***************************2.開始查詢資料****************************************/
				AnimalService animalSvc = new AnimalService();
				AnimalVO animalVO = animalSvc.getOneAnimal(id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("animalVO", animalVO);        
				String url = "/animal/update_animal_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/listAllAnimal.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer id = new Integer(req.getParameter("id").trim());
				
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("動物名稱: 請勿空白");
				} else if(!name.trim().matches(nameReg)) { 
					errorMsgs.add("動物名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				Integer age = new Integer(req.getParameter("age").trim());
				
				Double weight = null;
				try {
					weight = new Double(req.getParameter("weight").trim());
				} catch (NumberFormatException e) {
					weight = 0.0;
					errorMsgs.add("體重請填數字.");
				}
				
				Part part = req.getPart("picture");
				InputStream in = part.getInputStream();
				byte[] picture = new byte[in.available()];
				
				if (in.available() == 0) {

					AnimalService animalSvc = new AnimalService();
					AnimalVO animalVO = animalSvc.getOneAnimal(id);
					picture = animalVO.getPicture();
					in.read(picture);
					in.close();

				} else {
					in.read(picture);
					in.close();
					
				}
				
				Integer areano = new Integer(req.getParameter("areano").trim());
				

				AnimalVO animalVO = new AnimalVO();
				animalVO.setId(id);
				animalVO.setName(name);
				animalVO.setAge(age);
				animalVO.setWeight(weight);
				animalVO.setPicture(picture);
				animalVO.setAreano(areano);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("animalVO", animalVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/animal/update_animal_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始修改資料*****************************************/
				AnimalService animalSvc = new AnimalService();
				animalVO = animalSvc.updateAnimal(id, name, age, weight, picture ,areano);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("animalVO", animalVO); 
				String url = "/animal/listOneAniaml.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/animal/update_animal_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("動物名稱: 請勿空白");
				} else if(!name.trim().matches(nameReg)) { 
					errorMsgs.add("動物名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				Integer age = new Integer(req.getParameter("age").trim());
				
				Double weight = null;
				try {
					weight = new Double(req.getParameter("weight").trim());
				} catch (NumberFormatException e) {
					weight = 0.0;
					errorMsgs.add("體重請填數字.");
				}
				
				Part part = req.getPart("picture");
				InputStream in = part.getInputStream();
				byte[] picture = new byte[in.available()];
				
				if (in.available() == 0) {
					in.read(picture);
					in.close();

				} else {
					in.read(picture);
					in.close();
				}
				
				Integer areano = new Integer(req.getParameter("areano").trim());

				AnimalVO animalVO = new AnimalVO();
				animalVO.setName(name);
				animalVO.setAge(age);
				animalVO.setWeight(weight);
				animalVO.setPicture(picture);
				animalVO.setAreano(areano);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("animalVO", animalVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/animal/addAnimal.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AnimalService animalSvc = new AnimalService();
				animalVO = animalSvc.addAnimal(name, age, weight, picture, areano);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/animal/listAllAnimal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/animal/addAnimal.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer id = new Integer(req.getParameter("id"));
				
				/***************************2.開始刪除資料***************************************/
				AnimalService animalSvc = new AnimalService();
				animalSvc.deleteAnimal(id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/animal/listAllAnimal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/animal/listAllAnimal.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
