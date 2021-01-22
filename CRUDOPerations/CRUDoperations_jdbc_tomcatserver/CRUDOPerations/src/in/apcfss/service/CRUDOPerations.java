package in.apcfss.service;

import in.apcfss.dbplugin.DbPlugin;
import in.apcfss.dto.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CRUDOPerations {

	
	public static String AddEmployee(HttpServletRequest req, HttpServletResponse res) throws Exception
	{	Employee employee = null;
		
		String name=null;
		String dob=null;
		String designation =null;
		String qualify=null;
		String gender =null;
		String joiningdate =null;
		String pancard =null;
		String address =null;
		String mobileNumber =null;
		String emailId =null;
	//	int id = Integer.parseInt(req.getParameter("id"));
	name = req.getParameter("user");
	dob = req.getParameter("dob");
	designation=req.getParameter("designation");
	qualify = req.getParameter("qualification");
	gender = req.getParameter("gender");
	joiningdate = req.getParameter("joiningdate");
	pancard = req.getParameter("pancard");
	address = req.getParameter("address");
	mobileNumber = req.getParameter("mobilenumber");
	emailId = req.getParameter("emailid");
	String technicalskillsarray[] = req.getParameterValues("technicalskills");

	String technicalskills = "";
    for(int i=0;i<technicalskillsarray.length;i++){
    	technicalskills+=technicalskillsarray[i]+" ";
    }
		String status = null;
		Connection conn = null;
		PreparedStatement ps = null;
		int empdetailsUpdateResult=0;
		ResultSet rs=null;
		Statement stmt = null;
		String sql=null;
		int id =0;
		try
		{employee = new Employee();
			conn = DbPlugin.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement( );
			sql="select nextval('emp_seqence') as emp_id";

			rs=stmt.executeQuery(sql);
			
			while(rs.next())
			{
				id = rs.getInt("emp_id");
			}	
			ps=conn.prepareStatement("INSERT INTO public.employeedetailsform(id, name, qualification, gender, dateofjoining, pancard, dob, designation, address, mobilenumber, emailid, technicalskills) VALUES(?, ?, ?, ?, ?, ?, ?,?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2,name);
			ps.setString(3, qualify);
			ps.setString(4, gender);
			ps.setString(5, joiningdate);
			ps.setString(6, pancard); 
			ps.setString(7, dob);
			ps.setString(8,designation);
			ps.setString(9,address);
			ps.setString(10,mobileNumber);
			ps.setString(11,emailId);
			ps.setString(12,technicalskills);
			empdetailsUpdateResult=ps.executeUpdate();  
			System.out.println(empdetailsUpdateResult);
			if(empdetailsUpdateResult>0){
				conn.commit();		
				status="success...";
			}else{
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
				employee.setDesignation(rs.getString(8));
				employee.setAddress(rs.getString(9));
				employee.setMobilenumber(rs.getString(10));
				employee.setEmailid(rs.getString(11));
				employee.setTechnicalskills(rs.getString(12));

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
		String sql=null;
		
	
		String name=null;
		String dob=null;
		String designation =null;
		String qualify=null;
		String gender =null;
		String joiningdate =null;
		String pancard =null;
		String address =null;
		String mobileNumber =null;
		String emailId =null;
	//	String s=req.getParameter("id");
	//	int id = Integer.parseInt(s);
		name = req.getParameter("name");
		dob = req.getParameter("dob");
		designation=req.getParameter("designation");
		qualify = req.getParameter("qualification");
		gender = req.getParameter("gender");
		joiningdate = req.getParameter("joiningdate");
		pancard = req.getParameter("pancard");
		address = req.getParameter("address");
		mobileNumber = req.getParameter("mobilenumber");
		emailId = req.getParameter("emailid");
		Employee employee=new Employee();
		String technicalskillsarray[] = req.getParameterValues("technicalskills");

		String technicalskills = "";
        for(int i=0;i<technicalskillsarray.length;i++){
        	technicalskills+=technicalskillsarray[i]+" ";
        }
		
		try
		{
		//	int id = employee.getId();
			int id=Integer.parseInt(req.getParameter("id"));
			conn = DbPlugin.getConnection();
			conn.setAutoCommit(false);
			sql = "UPDATE public.employeedetailsform SET  name=?, qualification=?, gender=?, dateofjoining=?, pancard=?, dob=?, designation=?, address=?, mobilenumber=?, emailid=?, technicalskills=? WHERE id=?";
			ps = conn.prepareStatement(sql);
			
			//Employee e=new Employee();
			ps.setString(1,name);
			ps.setString(2, qualify);
			ps.setString(3, gender);
			ps.setString(4, joiningdate);
			ps.setString(5, pancard); 
			ps.setString(6, dob);
			ps.setString(7,designation);
			ps.setString(8,address);
			ps.setString(9,mobileNumber);
			ps.setString(10,emailId);
			ps.setString(11,technicalskills);
			ps.setInt(12,id);
			updateResult=ps.executeUpdate();  
			
			System.out.println(updateResult);
			if(updateResult>0){
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
		String sql= null;
		int deleteresult = 0;
		
		try
		{
			conn = DbPlugin.getConnection();
			conn.setAutoCommit(false);
			sql = "DELETE FROM public.employeedetailsform WHERE id=?";
			int del_id = Integer.parseInt(req.getParameter("id"));
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, del_id);
			
			
			deleteresult=pstmt.executeUpdate();
			if(deleteresult>0){
				conn.commit();
				status="success";
			}else{
				conn.rollback();
				status="failure..";
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
			sql =" SELECT id, name, qualification, gender, dateofjoining, pancard, dob, designation, address, mobilenumber, emailid, technicalskills FROM public.employeedetailsform";
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
				employee.setDesignation(rs.getString("designation"));
				employee.setAddress(rs.getString("address"));
				employee.setMobilenumber(rs.getString("mobilenumber"));
				employee.setEmailid(rs.getString("emailid"));
				employee.setTechnicalskills(rs.getString("technicalskills"));
				
				employeeList.add(employee);
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
		return employeeList;
	}

}
