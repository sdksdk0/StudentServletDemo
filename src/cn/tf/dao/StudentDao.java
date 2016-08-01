package cn.tf.dao;

import java.util.ArrayList;
import java.util.List;

import cn.tf.entity.Student;
import cn.tf.utils.DBHelper;



public class StudentDao {

	private DBHelper  db=new DBHelper();
	
	public int add(String cid,String sname,String age,String  tel,String photo){
		
		String sql="insert into student values(0,?,?,?,?,?)";
		List<Object>  params =new ArrayList<Object>();
		params.add(cid);
		params.add(sname);
		params.add(age);
		params.add(tel);
		params.add(photo);
		return db.update(sql, params);
		
	}
	
	public List<Student> find(){
		String sql="select s.*,cname from student s,classes c where s.cid=c.cid";
		return db.find(sql, null, Student.class);
		
	}
	
	public List<Student>  find(Integer pageNo,Integer pageSize){
		String sql="select *from (select A.*,rownum rn  from (select s.*,cname from student s,classes c where s.cid=c.cid  order by sid) A "
				+ " where rownum<=?)where rn>?";
		List<String> param=new ArrayList<String>();
		param.add(String.valueOf(pageNo*pageSize));
		param.add(String.valueOf((pageNo-1)*pageSize));
		
		return db.find(sql, param,Student.class);
	}
	
	public int getTotal(Integer cid){
		String sql=null;
		List<String> param=new ArrayList<String>();
		if(cid!=null){
			sql="select count(sid) as count from student s ,classes c where s.cid=c.cid and cid=?";
			param.add(String.valueOf(cid));
		}else{
			sql="select count(sid) as count from student s,classes c where s.cid=c.cid";
		}
		double count=db.doSelectFunction(sql, param);
		
		return (int) count;
	}
	
	//用户登录
	public Student login(String uname,String pwd){
		
		String sql="select *  from student  where sname=? and sid=? ";
		List<String> params=new ArrayList<String>();
		params.add(uname);
		params.add(pwd);
		
		List<Student> list=db.find(sql, params,Student.class);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
		
	
		
		
	}
	
	
	
}
