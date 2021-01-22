package in.apcfss.controller;

import in.apcfss.service.CRUDOPerations;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteEmployee extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String status=null;
		PrintWriter out = null;
		res.setContentType("text/html");  
		try{
			status = CRUDOPerations.deleteEmployee(req,res);
			out=res.getWriter(); 
			out.println("deleted...");
			if("success".equals(status)){
				out.println("sucess deleted");
			}
			else{
				out.println("failed deleted");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
