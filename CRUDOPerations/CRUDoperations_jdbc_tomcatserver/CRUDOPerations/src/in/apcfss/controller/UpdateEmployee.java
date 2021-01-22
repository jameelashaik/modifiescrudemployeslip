package in.apcfss.controller;

import in.apcfss.dto.Employee;
import in.apcfss.service.CRUDOPerations;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateEmployee extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	
		PrintWriter pw = null; 
		res.setContentType("text/html"); 
		try{
			pw = res.getWriter();
			pw.println("checking...update");

	        pw.println("<h1>Update Employee</h1>");  
	        String sid=req.getParameter("id");  
	        int id=Integer.parseInt(sid);  
	        Employee e=CRUDOPerations.getEmployeeById(id); 
	        pw.print("<form action='updateemp' method='post'>");  
	        pw.print("<input type='hidden' name='id' value='"+e.getId()+"'/>");  
	        pw.print("Name:<input type='text' name='name' value='"+e.getName()+"'/><br> <br>");
	        pw.print("Qualification:<input type='text' name='qualification' value='"+e.getQualification()+"'/><br> <br>");
	        pw.print("Gender:<input type='radio' name='gender' value='male'/>"+"male");
	        //pw.print("<label for='male'>Male</label><br><br>");
	        pw.print("<input type='radio'  name='gender'  value='female'/>"+"female");
	      //  pw.print("<label for='female'>Female</label><br><br>");
	        pw.print("<input type='radio'  name='gender' value='other' />"+"other<br><br>");
	        //pw.print("<label for='other'>Other</label><br><br>");
	        pw.print("Dateofjoining:<input type='date' name='joiningdate' value='"+e.getDoj()+"'/><br> <br>");
	        pw.print("Pancard:<input type='text' name='pancard' value='"+e.getPancard()+"'/><br> <br>");
	        pw.print("DateofBirth:<input type='date' name='dob' value='"+e.getDob()+"'/><br> <br>");
	        pw.print("Designation:<input type='text' name='designation' value='"+e.getDesignation()+"'/><br> <br>");
	        pw.print("address:<input type='text' name='address' value='"+e.getAddress()+"'/><br> <br>");
	        pw.print("MObileNumber:<input type='text' name='mobilenumber' value='"+e.getMobilenumber()+"'/><br> <br>");
	        pw.print("EmailId:<input type='text' name='emailid' value='"+e.getEmailid()+"'/><br> <br>");
	        pw.print("TechnicalSkills:<input type='checkbox' name='technicalskills' value='java'>"+"java");
	       // pw.print("<input type='checkbox'  name='technicalskills' value='java'>");
	        //pw.print("<label for='java'> java</label><br>");
	        pw.print("<input type='checkbox'  name='technicalskills' value='python'>"+"python");
	        //pw.print("<label for='python'> python</label><br>");
	        pw.print("<input type='checkbox'  name='technicalskills' value='c'>"+"c<br><br>");
	       // pw.print("<label for='c'> c</label><br>");
	        pw.print("<input type='submit' value='submit'><br> <br>");
	        pw.print("</form>");
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("doget employee update..exception");
		}
		finally{
			pw.close();
		}

	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String status=null;
		PrintWriter pw = null;

		try{

			pw = res.getWriter(); 
			System.out.println("trychecking..");


			status = CRUDOPerations.updateEmployee(req,res);
			System.out.println(status);
			if("update".equals(status)){
				pw.println("sucess update");
			}
			else{
				pw.println("failed update");
			}
		}
		catch(NumberFormatException ev){
			ev.printStackTrace();
			System.out.println("number exception");
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("exception cathedc for update employee dopost");
		}
		
		    
	}

}
