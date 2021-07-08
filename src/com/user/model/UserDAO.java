package com.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/test01?serverTimezone=Asia/Taipei";
	String user_id = "root";
	String password = "123456";
	
	private static final String UPDATE = "update USER set ID = ?, PSWD = ? where USER_NO = ?";
	private static final String GET_ONE = "select * from USER where USER_NO = ?";
	private static final String GET_ALL = "select * from USER order by user_no";
	

	@Override
	public UserVO update(UserVO userVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user_id, password);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, userVO.getId());
			pstmt.setString(2, userVO.getPswd());
			pstmt.setInt(3, userVO.getUser_no());
					
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("Couldn't load database driver. " + se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return userVO;
	}

	@Override
	public UserVO getOne(Integer user_no) {
		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user_id, password);
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setInt(1, user_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				userVO = new UserVO();
				userVO.setUser_no(rs.getInt("USER_NO"));
				userVO.setId(rs.getString("ID"));
				userVO.setPswd(rs.getString("PSWD"));
			}
					
		}catch(ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("Couldn't load database driver. " + se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return userVO;
	}

	@Override
	public List<UserVO> getAll() {
		List<UserVO> list = new ArrayList<UserVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user_id, password);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserVO userVO = new UserVO();
				userVO.setUser_no(rs.getInt("USER_NO"));
				userVO.setId(rs.getString("ID"));
				userVO.setPswd(rs.getString("PSWD"));
				list.add(userVO);
			}
					
		}catch(ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("Couldn't load database driver. " + se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
//		List<UserVO> list = new ArrayList<UserVO>();
//		list = dao.getAll();
//		for(UserVO userVO : list) {
//			System.out.println(userVO.getUser_no());
//			System.out.println(userVO.getId());
//			System.out.println(userVO.getPswd());
//		}
		
//		UserVO userVO = dao.getOne(1);
//		System.out.println(userVO.getId());
		UserVO userVO = new UserVO();
		userVO.setId("123");
		userVO.setPswd("789");
		userVO.setUser_no(1);
		
		dao.update(userVO);
		
		
	}

}
