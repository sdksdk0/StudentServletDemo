<%@page import="cn.tf.entity.Student"%>
<%@page import="cn.tf.dao.StudentDao"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>

<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    <%
    	request.setCharacterEncoding("UTF-8");
    	String pageNo=request.getParameter("pageNo");
    	String pageSize=request.getParameter("pageSize");
    	
    	String op=request.getParameter("op");
    	StudentDao studentDao=new StudentDao();
    	List<Student> list=studentDao.find(Integer.parseInt(pageNo),Integer.parseInt(pageSize));
    	
    	JSONArray json=JSONArray.fromObject(list);  //把list集合中的数据转换成一个json对象数组
    	JSONObject jb=new JSONObject();
    	
    	if("1".equals(op)){
    		jb.put("total",studentDao.getTotal(null));
    	}
    	
    	
    	jb.put("studentInfo",json);
    	
    	
    	out.print(jb.toString());
    
   
    %>