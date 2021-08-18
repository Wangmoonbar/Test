package com.user1.model;

import java.util.List;

public interface UserDAO_interface {

	public UserVO update(UserVO userVO);
	public UserVO getOne(Integer user_no);
	public List<UserVO> getAll();
	
}
