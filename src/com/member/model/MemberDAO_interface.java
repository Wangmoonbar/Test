package com.member.model;

import java.util.List;


public interface MemberDAO_interface {

	public MemberVO gogogo(String id,String pswd);
	public List<MemberVO> getAll();
	public MemberVO getOne(Integer seq);
	public void update(MemberVO memberVO);
	public void insert(MemberVO memberVO);
    public void delete(Integer seq);


	
}
