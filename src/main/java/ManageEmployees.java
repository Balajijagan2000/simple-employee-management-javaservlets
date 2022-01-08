

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class ManageEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static Db_connection db;

    public ManageEmployees() {
        super();
        // TODO Auto-generated constructor stub
    }


	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Servlet Initialized..");
		db = new Db_connection("employeedb");
		try {
			db.Create_db();
		} catch(Exception e) {
			System.out.println("Error in creating the db: "+e);
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		if(request.getParameter("addemployee") != null) {
			System.out.println("Add Employee Form submitted");
			
			String name = request.getParameter("ename");
			String id = request.getParameter("eid");
			String age = request.getParameter("age");
			String dept = request.getParameter("dept");
			String email = request.getParameter("email");
			String phno = request.getParameter("phno");
			
			ArrayList<String> empDetail = new ArrayList<>();
			
			empDetail.add(id);
			empDetail.add(name);
			empDetail.add(age);
			empDetail.add(phno);
			empDetail.add(dept);
			empDetail.add(email);
			
			System.out.println(empDetail);
			
			db.addEmployee(empDetail);
//			
//						out.println("<script>"
//					+ "alert('Successfully Saved!')"
//			
//					+ "</script>");
			request.setAttribute("data", empDetail);
			RequestDispatcher rd = request.getRequestDispatcher("addemployee.jsp");
			rd.forward(request, response);
			
			
		}
		else if(request.getParameter("editemp") != null) {
			
			String eid = request.getParameter("eid");
			ArrayList<String> data = db.getEmployee(eid);
			System.out.println(data);
			request.setAttribute("empeditdata", data);
			RequestDispatcher rd = request.getRequestDispatcher("editempdetails.jsp");
			rd.forward(request, response);
		} 
		else if(request.getParameter("updateemployee") != null) {
			
			ArrayList<String> updatedata = new ArrayList<>();
			updatedata.add(request.getParameter("eid"));
			updatedata.add(request.getParameter("ename"));
			updatedata.add(request.getParameter("age"));
			updatedata.add(request.getParameter("phno"));
			updatedata.add(request.getParameter("email"));
			updatedata.add(request.getParameter("dept"));
			int flg = db.updateEmployee(updatedata);
			if(flg == 1) {
				request.setAttribute("Messageup","Updation Completed Successfully");
			}
			else {
				request.setAttribute("Messageup","Updation unsuccessful");
			}
			RequestDispatcher rs = request.getRequestDispatcher("editempdetails.jsp");
			rs.forward(request, response);
		
			
		} else if(request.getParameter("deleteemp") != null) {
			
			int flg = db.removeEmployee(request.getParameter("eid"));
			
			if(flg == 1) {
				String msg = "Employee Removed Successfully";
				request.setAttribute("Message",msg);
			}
			else {
				String msg = "Please give the proper ID or check with DB";
				request.setAttribute("Message",msg);
			}
			RequestDispatcher rs = request.getRequestDispatcher("editempdetails.jsp");
			rs.forward(request, response);
			
		} else if(request.getParameter("displayemp") != null) {
			
			ArrayList<ArrayList<String>> employees = db.displayAllemployees();
			System.out.println(employees);
			request.setAttribute("Employees", employees);
			RequestDispatcher rd = request.getRequestDispatcher("addemployee.jsp");
			rd.forward(request, response);
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
