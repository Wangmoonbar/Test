package com.animal.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class AnimalDAO implements AnimalDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/test");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
			"insert into Animal (NAME,AGE,WEIGHT,PICTURE,AREA_NO) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"update Animal set NAME=?, AGE=?, WEIGHT=?, PICTURE=?, AREA_NO=? where ID = ?";
	private static final String DELETE_STMT = 
			"delete from Animal where ID = ?";
	private static final String GET_ONE_STMT = 
			"select * from Animal where ID = ?";
	private static final String GET_ALL_STMT = 
			"select * from Animal ";
	
	
	@Override
	public void insert(AnimalVO animalVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, animalVO.getName());
			pstmt.setInt(2, animalVO.getAge());
			pstmt.setDouble(3, animalVO.getWeight());
			pstmt.setBytes(4, animalVO.getPicture());
			pstmt.setInt(5, animalVO.getAreano());
			

			pstmt.executeUpdate();

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void update(AnimalVO animalVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, animalVO.getName());
			pstmt.setInt(2, animalVO.getAge());
			pstmt.setDouble(3, animalVO.getWeight());
			pstmt.setBytes(4, animalVO.getPicture());
			pstmt.setInt(5, animalVO.getAreano());
			pstmt.setInt(6, animalVO.getId());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	public void delete(Integer id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, id);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	@Override
	public AnimalVO findByPrimaryKey(Integer id) {
		
		AnimalVO animalVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				animalVO = new AnimalVO();
				animalVO.setId(rs.getInt("ID"));
				animalVO.setName(rs.getString("NAME"));
				animalVO.setAge(rs.getInt("AGE"));
				animalVO.setWeight(rs.getDouble("WEIGHT"));
				animalVO.setPicture(rs.getBytes("PICTURE"));
				animalVO.setAreano(rs.getInt("AREA_NO"));
				
				
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return animalVO;
	}
	
	@Override
	public List<AnimalVO> getAll() {
		List<AnimalVO> list = new ArrayList<AnimalVO>();
		AnimalVO animalVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				animalVO = new AnimalVO();
				animalVO.setId(rs.getInt("ID"));
				animalVO.setName(rs.getString("NAME"));
				animalVO.setAge(rs.getInt("AGE"));
				animalVO.setWeight(rs.getDouble("WEIGHT"));
				animalVO.setPicture(rs.getBytes("PICTURE"));
				animalVO.setAreano(rs.getInt("AREA_NO"));
				list.add(animalVO); 
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());

		
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
