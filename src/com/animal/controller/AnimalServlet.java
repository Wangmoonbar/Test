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
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�ʪ�ID");
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
					errorMsgs.add("�ʪ�ID�榡�����T");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/animal/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				AnimalService animalSvc = new AnimalService();
				AnimalVO animalVO = animalSvc.getOneAnimal(id);
				if (animalVO == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/animal/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("animalVO", animalVO);
				String url = "/animal/listOneAnimal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/animal/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer id = new Integer(req.getParameter("id"));
				
				/***************************2.�}�l�d�߸��****************************************/
				AnimalService animalSvc = new AnimalService();
				AnimalVO animalVO = animalSvc.getOneAnimal(id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("animalVO", animalVO);        
				String url = "/animal/update_animal_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/listAllAnimal.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				Integer id = new Integer(req.getParameter("id").trim());
				
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("�ʪ��W��: �ФŪť�");
				} else if(!name.trim().matches(nameReg)) { 
					errorMsgs.add("�ʪ��W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				Integer age = new Integer(req.getParameter("age").trim());
				
				Double weight = null;
				try {
					weight = new Double(req.getParameter("weight").trim());
				} catch (NumberFormatException e) {
					weight = 0.0;
					errorMsgs.add("�魫�ж�Ʀr.");
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
				
				/***************************2.�}�l�ק���*****************************************/
				AnimalService animalSvc = new AnimalService();
				animalVO = animalSvc.updateAnimal(id, name, age, weight, picture ,areano);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("animalVO", animalVO); 
				String url = "/animal/listOneAniaml.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
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
					errorMsgs.add("�ʪ��W��: �ФŪť�");
				} else if(!name.trim().matches(nameReg)) { 
					errorMsgs.add("�ʪ��W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				Integer age = new Integer(req.getParameter("age").trim());
				
				Double weight = null;
				try {
					weight = new Double(req.getParameter("weight").trim());
				} catch (NumberFormatException e) {
					weight = 0.0;
					errorMsgs.add("�魫�ж�Ʀr.");
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
				
				/***************************2.�}�l�s�W���***************************************/
				AnimalService animalSvc = new AnimalService();
				animalVO = animalSvc.addAnimal(name, age, weight, picture, areano);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/animal/listAllAnimal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
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
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer id = new Integer(req.getParameter("id"));
				
				/***************************2.�}�l�R�����***************************************/
				AnimalService animalSvc = new AnimalService();
				animalSvc.deleteAnimal(id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/animal/listAllAnimal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/animal/listAllAnimal.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
