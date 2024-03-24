package com.sathya.productapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

		public int save(Product product) 
		{
			Connection c =null;
			PreparedStatement preparedStatement=null;
			
			int saveResult = 0;
			try {
				c=DbUtils.proCon();
				preparedStatement = c.prepareStatement("insert into product_data values(?,?,?,?,?,?,?,?,?,?)");
				
				preparedStatement.setString(1, product.getProId());
				preparedStatement.setString(2, product.getProName());
				preparedStatement.setDouble(3, product.getProPrice());
				preparedStatement.setString(4, product.getProBrand());
				preparedStatement.setString(5, product.getProMadeIn());
				preparedStatement.setDate  (6, product.getProMfgDate());
				preparedStatement.setDate  (7, product.getProExpDate());
				preparedStatement.setBytes (8, product.getProImage());
				preparedStatement.setBytes(9, product.getProaudio());
				preparedStatement.setBytes(10, product.getProvideo());
				
				saveResult= preparedStatement.executeUpdate();
		                    
			}
			catch (SQLException s) 
			{
				
				s.printStackTrace();
			}
			
			finally 
			{
				try {
					if(c!=null && preparedStatement!=null) {
					c.close();
					preparedStatement.close();
					}
				}catch (SQLException s) 
				{
					s.printStackTrace();
				}	
			}
			
			return saveResult;	
		}
	


  public List<Product> findAll() { 
	  List<Product> pro=new ArrayList<Product>();
  
  try(Connection connection=DbUtils.proCon(); 
		  Statement statement=
  connection.createStatement()){
  
  ResultSet resultSet=statement.executeQuery("select * from product_data");
  while(resultSet.next()) { 
	  Product product=new Product();
  
  product.setProId(resultSet.getString("proid"));
  product.setProName(resultSet.getString("proname"));
  product.setProPrice(resultSet.getDouble("proprice"));
  product.setProBrand(resultSet.getString("probrand"));
  product.setProMadeIn(resultSet.getString("promadeIn"));
  product.setProMfgDate(resultSet.getDate("promfg"));
  product.setProExpDate(resultSet.getDate("proexp"));
  product.setProImage(resultSet.getBytes("proimage"));
  product.setProaudio(resultSet.getBytes("audio_Description"));
  product.setProvideo(resultSet.getBytes("video_Description"));
  
  pro.add(product); } 
  } 
  catch(SQLException s) 
  { s.printStackTrace(); 
  }
  return pro; 
  }
  
  
  public static boolean deleteProduct(String productId) {
  
   PreparedStatement preparedStatement=null; 
  int result =0;
  
  try(Connection connection=DbUtils.proCon(); Statement statement=
  connection.createStatement()){
  preparedStatement = connection.prepareStatement("DELETE FROM product_data WHERE proid = ?"); //
  preparedStatement.setString(1, productId); //
  result = preparedStatement.executeUpdate(); } 
  catch (SQLException e) {
	  e.printStackTrace(); }
  if(result==0)
return false; 
  else
	  return true;
  }
  
  public void closeResources(Connection conn, Statement stmt, ResultSet rs) {
	    try {
	        if (rs != null) {
	            rs.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    try {
	        if (stmt != null) {
	            stmt.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    try {
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

  public boolean updateProduct(Product productToUpdate) { 
	    Connection conn = null; 
	    PreparedStatement stmt = null; 
	    boolean updated = false;

	    try { 
	        conn = DbUtils.proCon(); 
	        String sql = "UPDATE product_data SET proName=?, proPrice=?, proBrand=?, proMadeIn=?, proMfg=?, proExp=?, proImage=?,audio_Description=?, video_Description=? WHERE proId=?"; 
	        stmt = conn.prepareStatement(sql); 
	        stmt.setString(1, productToUpdate.getProName()); 
	        stmt.setDouble(2, productToUpdate.getProPrice()); 
	        stmt.setString(3, productToUpdate.getProBrand()); 
	        stmt.setString(4, productToUpdate.getProMadeIn()); 
	        stmt.setDate(5, productToUpdate.getProMfgDate()); 
	        stmt.setDate(6, productToUpdate.getProExpDate()); 
	        stmt.setBytes(7, productToUpdate.getProImage());
	        stmt.setBytes(8, productToUpdate.getProaudio());
	        stmt.setBytes(9, productToUpdate.getProvideo());
	        stmt.setString(10, productToUpdate.getProId());

	        int rowsAffected = stmt.executeUpdate(); 
	        updated = rowsAffected > 0;
	    }
	    catch (SQLException e) {
	        e.printStackTrace(); 
	    }
	    finally { 
	        closeResources(conn, stmt, null); 
	    }
	    return updated; 
	}
  
  
  public Product findById(String productId) {
  Connection conn = null;
  PreparedStatement stmt = null; 
  ResultSet rs = null; Product product = null;
  
  try { 
	  conn = DbUtils.proCon(); 
	  String sql = "SELECT * FROM product_data WHERE proId=?"; 
	  stmt =conn.prepareStatement(sql); 
	  stmt.setString(1, productId); 
	  rs = stmt.executeQuery();
  
  if (rs.next()) { 
  product = new Product();
  product.setProId(rs.getString("proId"));
  product.setProName(rs.getString("proName"));
  product.setProPrice(rs.getDouble("proPrice"));
  product.setProBrand(rs.getString("proBrand"));
  product.setProMadeIn(rs.getString("proMadeIn"));
  product.setProMfgDate(rs.getDate("proMfg"));
  product.setProExpDate(rs.getDate("proExp"));
  product.setProImage(rs.getBytes("proImage")); 
  product.setProaudio(rs.getBytes("AUDIO_DESCRIPTION"));
  product.setProvideo(rs.getBytes("VIDEO_DESCRIPTION"));
  } 
  }
  catch (SQLException e) {
  e.printStackTrace(); 
  }
  finally { 
	  closeResources(conn, stmt, rs); 
  }
  return product; 
  }
  
  }
 
		
