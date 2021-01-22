package in.apcfss.controller;

import in.apcfss.dto.Employee;
import in.apcfss.dto.Employeslip;
import in.apcfss.service.CRUDOPerations;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployePaySlip extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
	{
		PrintWriter out = null;
		res.setContentType("text/html");  
   
 
        List<Employeslip> employeslipList = null;
        List<Employee> employeesList = null;
		try
		{
			out=res.getWriter();  
	        out.println("<h1>Employees payslip List</h1>"); 
			employeesList=CRUDOPerations.slipEmployee(req,res);
			out.print("<table border='1' width='100%'");  
	        out.print("<tr><th>EmpId</th><th>Name</th><th>Pancard</th><th>grosstotal</th><th>dayspresent</th>" +
	        		"<th>basicpay</th><th>DA</th><th>HRA</th><th>PF</th><th>PT</th><th>NETSALARY</th></tr>");  
	        float totaldays=30;
			for(Employee e:employeesList)
			{
				
				 out.print("<tr><td>"+e.getId()+"</td><td>"+e.getName()+"</td>" +
				 		"<td>"+e.getPancard()+"</td><td>"+((e.getSalary())/totaldays)*(e.getDayspresent())+"</td><td>"+
						 e.getDayspresent()+"</td> <td>"+(((e.getSalary())/totaldays)*(e.getDayspresent())*(4.0/10))+"</td><td>"+
				 		(((e.getSalary())/totaldays)*(e.getDayspresent())*(3.0/10))+"</td><td>"+(((e.getSalary())/totaldays)*(e.getDayspresent())*(3.0/10))+"</td><td>"+
						 (((e.getSalary())/totaldays)*(e.getDayspresent())*(125.0/1000))+"</td><td>"+(((e.getSalary())/totaldays)*(e.getDayspresent())*(1.0/100))+"</td><td>"+
				 		(((e.getSalary())/totaldays)*(e.getDayspresent())-((((e.getSalary())/totaldays)*(e.getDayspresent())*(125.0/1000))+((((e.getSalary())/totaldays)*(e.getDayspresent()))*(1.0/100))))+"</td></tr>");  
				
					
			}

		out.print("");
	        out.print("</table>");  
	        
	        
		}
		catch(Exception e)
		{
			out.print("<h5>Problem in processing your request. Please try again.</h5>");  
			e.printStackTrace();
		}
		finally
		{
			out.close();
		}
	}

}
