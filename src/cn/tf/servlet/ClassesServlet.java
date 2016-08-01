package cn.tf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import cn.tf.dao.ClassesDao;
import cn.tf.entity.Classes;


public class ClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PrintWriter out;
    private ClassesDao classesDao=new ClassesDao();
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		out=response.getWriter();
		
		String op=request.getParameter("op");
    	if("addClasses".equals(op)){
    		addClasses(request,response);
    	}else if("findClasses".equals(op)){
    		findClasses(request,response);
    		
    	}
	}



	//添加班级信息
	private void addClasses(HttpServletRequest request, HttpServletResponse response){
		String cname=request.getParameter("cname");
    	int result=classesDao.add(cname);
    	
    	out.println(result);
		out.flush();
		out.close();
	}
	
	//查询所有班级信息
	private void findClasses(HttpServletRequest request, HttpServletResponse response){
		
		List<Classes> list=classesDao.finds();
		JSONArray  json=JSONArray.fromObject(list);
		out.print(json);
		out.flush();
		out.close();
		
		
		
	}
	
	}	
	