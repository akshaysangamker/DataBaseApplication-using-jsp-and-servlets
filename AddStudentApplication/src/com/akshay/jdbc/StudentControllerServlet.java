package com.akshay.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDBUtil studentdbutil;
	@Resource(name="jdbc/web_student_tracker")
	private DataSource datasource;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			studentdbutil=new StudentDBUtil(datasource);
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Student> students=studentdbutil.getstudents();
		request.setAttribute("STUDENT_LIST", students);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
		
	} 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String thecommand=request.getParameter("command");
			
			if(thecommand==null) {
				thecommand="LIST";
			}
			
			switch(thecommand) {
			
			case "LIST":
				listStudents(request,response);
				break;
			
			
			case "LOAD":
				loadStudent(request,response);
				break;
				
			case "UPDATE":
				updateStudent(request,response);
				break;
				
			case "DELETE":
				deleteStudent(request,response);
				break;
			    
			default:
				listStudents(request,response);
			
			}
		
		}
		catch(Exception e) {
 			throw new ServletException(e);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
					
			// route to the appropriate method
			switch (theCommand) {
							
			case "ADD":
				addStudent(request, response);
				break;
								
			default:
				listStudents(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studentid=request.getParameter("studentid");
		studentdbutil.deletestudent(studentid);
		listStudents(request,response);
		
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int studentid=Integer.parseInt(request.getParameter("studentid"));
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		Student thestudent=new Student(studentid,firstname,lastname,email);
		studentdbutil.updatestudent(thestudent);
		listStudents(request,response);
		
		// TODO Auto-generated method stub
		
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String Studentid=request.getParameter("studentid");
		
		Student thestudent=studentdbutil.getstudent(Studentid);
		
		request.setAttribute("THE_STUDENT", thestudent);
		
		RequestDispatcher requestdispatcher= request.getRequestDispatcher("/update-student-form.jsp");
		requestdispatcher.forward(request, response);
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		String email=request.getParameter("email");
		
		
		
		Student thestudent= new Student(firstname,lastname,email);
		
		studentdbutil.addStudent(thestudent);
		response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
		
		
				
	}

	

}
