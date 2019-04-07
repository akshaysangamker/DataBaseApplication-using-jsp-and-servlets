package com.akshay.jdbc;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;


import javax.sql.DataSource;

public class StudentDBUtil {
	
	private DataSource datasource;

	public StudentDBUtil(DataSource thedatasource) {
		
	datasource = thedatasource;
	}
	
    public List<Student> getstudents() throws Exception{
    	List<Student> students=new ArrayList<>();
    	Connection con=null;
    	Statement mystmt=null;
    	ResultSet rs=null;
    	
    	
    	try {
    		con=datasource.getConnection();
    		String sql="select * from student order by last_name";
    		mystmt=con.createStatement();
    		rs=mystmt.executeQuery(sql);
    		
    		while(rs.next()) {
    			int id=rs.getInt("id");
    			String firstname=rs.getString("first_name");
    			String lastname=rs.getString("last_name");
    			String email=rs.getString("email");
    			Student tempstudent=new Student(id,firstname,lastname,email);
    			students.add(tempstudent);
    		}
    		return students;
    	}
    	
    	
    	
    	finally {
    		close(con,mystmt,rs);
    	}
    }

	private void close(Connection con, Statement mystmt, ResultSet rs) {
		
		try {
			if(rs!=null) {
				rs.close();
			}
			if(mystmt!=null) {
				mystmt.close();
			}
			if(con!=null) {
				con.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void addStudent(Student thestudent) throws Exception{
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=datasource.getConnection();
			
			String sql="insert into student"+"(first_name,last_name,email)"+"values(?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, thestudent.getFirstname());
			ps.setString(2, thestudent.getLastname());
			ps.setString(3, thestudent.getEmail());
			
			ps.execute();
			
		}
		finally {
			close(con,ps,null);
		}
		
	}

	public Student getstudent(String studentid) throws Exception {
		
		Student thestudent;
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		int thestudentid;
		
		
		try {
			
		thestudentid=Integer.parseInt(studentid);
		con=datasource.getConnection();
		String sql="select * from student where id=?";
		stmt=con.prepareStatement(sql);
		stmt.setInt(1, thestudentid);
		rs=stmt.executeQuery();
		
		if(rs.next()) {
			String firstname=rs.getString("first_name");
			String lastname=rs.getString("last_name");
			String email=rs.getString("email");
			
			thestudent=new Student(thestudentid,firstname,lastname,email);
			
			
		}
		
		else {
			throw new Exception("Could not find student id"+thestudentid);
		}
		return thestudent;
		
		}
		
		
		finally {
			close(con,stmt,rs);
		}
	
		
	}

	public void updatestudent(Student thestudent) throws Exception {
		Connection con=null;
		PreparedStatement stmt=null;
		try {
			con=datasource.getConnection();
			String sql="update student"+" set first_name=?"+",last_name=?"+",email=?"+" where id=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1,thestudent.getFirstname());
			stmt.setString(2,thestudent.getLastname());
			stmt.setString(3,thestudent.getEmail());
			stmt.setInt(4,thestudent.getId());
			stmt.execute();
		}
		
		finally {
			close(con,stmt,null);
		}
		// TODO Auto-generated method stub
		
	}

	public void deletestudent(String studentid) throws Exception {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			int thestudentid=Integer.parseInt(studentid);
			con=datasource.getConnection();
			String sql="delete from student where id=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1,thestudentid);
			ps.execute();
		}
		finally {}
		
	}
}
