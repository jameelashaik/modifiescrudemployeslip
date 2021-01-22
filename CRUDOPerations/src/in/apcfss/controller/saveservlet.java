package in.apcfss.controller;

import in.apcfss.dto.Employee;
import in.apcfss.dto.Employeslip;
import in.apcfss.service.CRUDOPerations;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class saveservlet extends HttpServlet {

private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
 {
		PrintWriter out = null;
		try {
			response.setContentType("text/html");
			out = response.getWriter();
			out.println("<h2>hey</h2>");
			out.println("<h1>Employee Attendence</h1>");
			List<Employeslip> employeeList = CRUDOPerations.getEmployeesAttendence(request, response);
			System.out.println(employeeList);

			request.setAttribute("employeeList", employeeList);
			System.out.println(employeeList);
			out.print("hiiii");
			/*
			 * out.print("<form action='add_days' method='post'>");
			 * out.print("<table>"); out.print(
			 * "<tr><td></td><td><select name='empId' id='empId> <option value='mercedes'>Mercedes</option>"
			 * + " <option value='audi'>Audi</option>" + "</select>" + "" +
			 * "</td></tr>"); ; out.print(
			 * "<tr><td colspan='2'><input type='submit' value='go' /></td></tr>"
			 * ); out.print("</table>"); out.print("</form>");
			 */

			RequestDispatcher rd = request.getRequestDispatcher("/attendence.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			out.print("problem in displaying form:");
		} finally {
			out.close();
		}
	}
}