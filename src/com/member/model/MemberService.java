package com.member.model;

import java.util.List;


public class MemberService {
	private MemberDAO_interface dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public MemberVO gogogo(String id,String pswd) {
		return dao.gogogo(id,pswd);
	}
	
	public List<MemberVO> getAll(){
		return dao.getAll();
	}
	
	public MemberVO getOne(Integer seq) {
		return dao.getOne(seq);
	}
	
	public MemberVO update(String id, String pswd, Integer seq) {
		MemberVO memberVO = new MemberVO();
		memberVO.setId(id);
		memberVO.setPswd(pswd);
		memberVO.setSeq(seq);
		dao.update(memberVO);
		
		return memberVO;
	}
	
	public MemberVO insert(String id, String pswd) {
		MemberVO memberVO = new MemberVO();
		memberVO.setId(id);
		memberVO.setPswd(pswd);
		dao.insert(memberVO);

		return memberVO;
	}

}
