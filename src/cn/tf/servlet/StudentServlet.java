package cn.tf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import cn.tf.dao.StudentDao;
import cn.tf.entity.Student;
import cn.tf.utils.PageUtil;
import cn.tf.utils.UploadUtil;


public class StudentServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.doPost(request, response);
		
		
		String op=request.getParameter("op").trim();
		System.out.println(op);

		
		if("addStudent".equals(op)){
			addStudent(request,response);
		}else if("findStudent".equals(op)){
			findStudent(request,response);
		}else if("studentLogin".equals(op)){
			studentLogin(request,response);
		}
		
		
		
	}
	
	private void studentLogin(HttpServletRequest request,HttpServletResponse response) {
		
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		
		
		StudentDao studentDao=new StudentDao();
		int result=0;
		if(uname==null || "".equals(uname)){
			result=1;
		}else if(pwd==null || "".equals(pwd)){
			result=2;
		}else{
			Student stu=studentDao.login(uname, pwd);
			if(stu!=null){
				
				HttpSession session=request.getSession();
				session.setAttribute("currentLogin",stu);
				result=3;
			}else{
				result=0;
			}
		}
		this.out(response,result);
		
	}


	//查询学生信息
	private void findStudent(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		
		Object obj=session.getAttribute("pageUtil");
		StudentDao studentDao=new StudentDao();
		
		PageUtil pageUtil;
		if(obj==null){
			pageUtil=new PageUtil(studentDao.getTotal(null),5);
			session.setAttribute("pageUtil", pageUtil);
		}else{
			pageUtil=(PageUtil) obj;
		}
		session.setAttribute("studentInfo", studentDao.find(pageUtil.getPageNo(),pageUtil.getPageSize()));
		
		try {
			response.sendRedirect("../show.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	private void addStudent(HttpServletRequest request, HttpServletResponse response) {
		StudentDao studentDao=new StudentDao();
		UploadUtil upload=new UploadUtil();
		
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PageContext pageContext=JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 1024, true);

		Map<String,String> map=upload.upload(pageContext);

		
		if(studentDao.add(map.get("cid"),map.get("sname"),map.get("age"),map.get("tel"),map.get("photo"))>0){
			out.print(1);
		}else{
			out.print(0);
		}
	}
	
	
	
	
	

}
