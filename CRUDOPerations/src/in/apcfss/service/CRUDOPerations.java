package in.apcfss.service;

import in.apcfss.dbplugin.DbPlugin;
import in.apcfss.dto.Employee;
import in.apcfss.dto.Employeslip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CRUDOPerations {
	public static String dayspresent(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		String status=null;
		String sql=null;
		String ename=null;
		int empgross=0;
		int daysnumber=0;
		Connection conn = null;
		PreparedStatement ps = null;
		int did=0;
		int dayspresentresult=0;
		try{
			conn = DbPlugin.getConnection();
			conn.setAutoCommit(false);
//			ename = req.getParameter("user");
//			empgross=Integer.parseInt(req.getParameter("salary"));
			daysnumber=Integer.parseInt(req.getParameter("dayspresent"));
			did=Integer.parseInt(req.getParameter("empId"));
			ps=conn.prepareStatement("INSERT INTO public.employesalary(eid, dayspresent) VALUES( ?, ?)");
			ps.setInt(1, did);
//			ps.setString(2,ename);
//			ps.setInt(3,empgross);
			ps.setInt(2,daysnumber);
			dayspresentresult=ps.executeUpdate(); 
			if(dayspresentresult>0){
				conn.commit();
				status="success...";
			}else{
				conn.rollback();
				status="failure...";
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("dayspresent method error");
		}		
		finally
		{
			ps.close();
			conn.close();
		}
		return status;
	}

	public static String AddEmployee(HttpServletRequest req, HttpServletResponse res) throws Exception
	{	Employee employee = null;

		String ename=null;
		String dob=null;
		String qualify=null;
		String gender =null;
		String joiningdate =null;
		String pancard =null;
		float salary=0;
		int basic_pay=0;
		float basic_sal=0;
		int da=0;
		int hra=0;
		int pf=0;
		int pt=0;
		float net_salary=0;
	ename = req.getParameter("user");
	dob = req.getParameter("dob");
	
	qualify = req.getParameter("qualification");
	gender = req.getParameter("gender");
	joiningdate = req.getParameter("joiningdate");
	pancard = req.getParameter("pancard");
	salary = Integer.parseInt(req.getParameter("salary"));
	System.out.println("add emp salary:"+salary);
		String status = null;
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		int empdetailsUpdateResult=0;
		ResultSet rs=null;
		Statement stmt = null;
		String sql=null;
		int eid =0;
		int employesalaryupdate = 0;
		try
		{
			employee = new Employee();
			conn = DbPlugin.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement( );
			basic_sal =(salary);
			System.out.println("add emp salary calculation:"+basic_sal);
			basic_pay = (int) ((0.4)*(basic_sal));
			da = (int) ((0.3)*(basic_sal));
			hra = (int) ((0.3)*(basic_sal));  
			pf = (int) ((0.125)*(basic_sal));
			if(basic_sal<15000){
				pt=150;
			}else{
				pt=200;
			} 
			net_salary=((basic_sal) - (pf+pt));
			System.out.println("net salary:"+net_salary);
			System.out.println("basic pay: "+basic_pay);
			sql="select nextval('emp_seqence') as emp_id";

			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				eid = rs.getInt("emp_id");
			}	

			ps=conn.prepareStatement("INSERT INTO public.employeedetailsform(id, name, qualification, gender, dateofjoining, pancard, dob,salary) VALUES(?, ?, ?, ?, ?, ?, ?,?)");
			
			ps.setInt(1, eid);
			ps.setString(2,ename);
			ps.setString(3, qualify);
			ps.setString(4, gender);
			ps.setString(5, joiningdate);
			ps.setString(6, pancard); 
			ps.setString(7, dob);
			ps.setFloat(8, salary);
			
			empdetailsUpdateResult=ps.executeUpdate();  
			System.out.println(empdetailsUpdateResult);
			if(empdetailsUpdateResult>0){

			
					conn.commit();
					status="success...";
			}
				else{
					conn.rollback();
					status="failure...";
				} 

		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception();
		}
		finally
		{
			ps.close();
			conn.close();
		}
		return status;
	}
	
    public static Employee getEmployeeById(int id){  
    	Employee employee=new Employee();  
         String sql=null;
        try{  
            Connection con=DbPlugin.getConnection();  
            sql = "SELECT * FROM public.employeedetailsform where id=?";
            PreparedStatement ps=con.prepareStatement(sql);  
            ps.setInt(1,id);  
            ResultSet rs=ps.executeQuery();  
            if(rs.next()){  
            	employee.setId(rs.getInt(1));
				employee.setName(rs.getString(2));
				employee.setQualification(rs.getString(3));
				employee.setGender(rs.getString(4));
				employee.setDoj(rs.getString(5));
				employee.setPancard(rs.getString(6));
				employee.setDob(rs.getString(7));
				employee.setSalary(rs.getFloat(8));
				

            }  
            con.close();  
        }catch(Exception ex){
        	ex.printStackTrace();
        	System.out.println("exception cated for getting id employyee");
        }  
          
        return employee;  
    }  

	
	public static String updateEmployee(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		String status = null;
		int updateResult=0;
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		String sql=null;
		String name=null;
		String dob=null;
		String qualify=null;
		String gender =null;
		String joiningdate =null;
		String pancard =null;
		String salary =null;
		int dayspresent =0;

		name = req.getParameter("name");
		dob = req.getParameter("dob");
		qualify = req.getParameter("qualification");
		gender = req.getParameter("gender");
		joiningdate = req.getParameter("joiningdate");
		pancard = req.getParameter("pancard");
		salary = req.getParameter("salary");
		dayspresent = Integer.parseInt(req.getParameter("dayspresent"));

		try
		{

			int id=Integer.parseInt(req.getParameter("id"));
			conn = DbPlugin.getConnection();
			conn.setAutoCommit(false);
			sql = "UPDATE public.employeedetailsform SET  name=?, qualification=?, gender=?, dateofjoining=?, pancard=?, dob=? ,salary=? WHERE id=?";
			ps = conn.prepareStatement(sql);

			ps.setString(1,name);
			ps.setString(2, qualify);
			ps.setString(3, gender);
			ps.setString(4, joiningdate);
			ps.setString(5, pancard); 
			ps.setString(6, dob);
			ps.setString(7, salary);
			ps.setInt(8,id);
			updateResult=ps.executeUpdate();  
			
			System.out.println(updateResult);
			if(updateResult>0){
				ps = conn.prepareStatement("UPDATE public.employesalary SET  edayspresent=?  WHERE eid=?");

				ps.setInt(1,dayspresent);
				ps.setInt(2,id);
				conn.commit();
				status="update";
				
			}
			else{
				conn.rollback();
				status="updatefail";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("update method error..");
			throw new Exception();
		}
		finally
		{
			ps.close();
			conn.close();
		}
		return status;
	}
	
	public static String deleteEmployee(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		String status = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		String sql= null;
		int del_id=0;
		int deleteresult = 0;
		int deleteresult1 = 0;
		
		try
		{
			conn = DbPlugin.getConnection();
			conn.setAutoCommit(false);
			sql = "DELETE FROM public.employesalary form WHERE eid=?";
			del_id = Integer.parseInt(req.getParameter("id"));
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, del_id);
			
			
			deleteresult=pstmt.executeUpdate();
			if(deleteresult>0){
				pstmt1=conn.prepareStatement("DELETE FROM public.employeedetailsform WHERE id=?");
				pstmt1.setInt(1, del_id);
				deleteresult1=pstmt1.executeUpdate();
				if(deleteresult1>0){
					conn.commit();
					status="success";					
				}else{
					conn.rollback();
					status="failure..";
				}

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception();
		}
		finally
		{
			pstmt.close();
			conn.close();
		}
		return status;
	}
	
	public static List<Employee> viewEmployee(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Employee> employeeList = new ArrayList<Employee>();
		String sql = null;
		ResultSet rs = null;
		Employee employee = null;

		try
		{

			conn = DbPlugin.getConnection();
			sql="SELECT * FROM public.employeedetailsform";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();		
			while(rs.next())
			{
				employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setQualification(rs.getString("Qualification"));
				employee.setGender(rs.getString("gender"));
				employee.setDoj(rs.getString("dateofjoining"));
				employee.setPancard(rs.getString("pancard"));
				employee.setDob(rs.getString("dob"));
				employee.setSalary(rs.getFloat("salary"));
				employeeList.add(employee);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("view employee error..");
			throw new Exception();
		}
		finally
		{
			pstmt.close();
			conn.close();
		}
		return employeeList;
	}
	
	public static List<Employee> slipEmployee(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		List<Employee> employeeList = new ArrayList<Employee>();
		String sql = null;
		String sql1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		Employee employee = null;
  
		try
		{

			conn = DbPlugin.getConnection();
			int id = Integer.parseInt(req.getParameter("id"));
			sql="select t.id,t.name,t.pancard,t.salary,ti.dayspresent from employeedetailsform as t,employesalary as ti where (t.id=ti.eid) and t.id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			rs = pstmt.executeQuery();		
			while(rs.next())
			{
				employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setPancard(rs.getString("pancard"));
				employee.setSalary(rs.getFloat("salary"));
				employee.setDayspresent(rs.getInt("dayspresent"));
				employeeList.add(employee);
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("view employee error..");
			throw new Exception();
		}
		finally
		{
			pstmt.close();
			conn.close();
		}
		return employeeList;
	}

	public static List<Employeslip> getEmployeesAttendence(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    Connection con = null;
    List<Employeslip> employeesList = new ArrayList<Employeslip>();
    String sql = null;
    //String status=null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    Employeslip emp = null;
    int id=0;
    try
    {
			con = DbPlugin.getConnection();
			sql = "SELECT id, name FROM public.employeedetailsform";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = (Integer) rs.getInt("id");
				String name = (String) rs.getString("name");
				emp = new Employeslip();
				emp.setId(id);
				emp.setName(name);
				employeesList.add(emp);
			}
			System.out.println("getemployeatendence runned successfully..");
		} catch (Exception e) {
			System.out.println("getemployeatendence runned fasiled..");
			e.printStackTrace();
		} finally {
			con.close();
			rs.close();
		}
		return employeesList;
   
    }

}
