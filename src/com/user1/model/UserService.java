package com.user1.model;

import java.util.List;

public class UserService {
	private UserDAO_interface dao;
	
	public UserService() {
		dao = new UserDAO();
	}
	
	public UserVO update(String id, String pswd, Integer user_no) {
		UserVO userVO = new UserVO();
		userVO.setId(id);
		userVO.setPswd(pswd);
		userVO.setUser_no(user_no);
		
		return dao.update(userVO);
	}
	
	public UserVO getOne(Integer user_no) {
		return dao.getOne(user_no);
	}
	
	public List<UserVO> getAll(){
		return dao.getAll();
	}
}
