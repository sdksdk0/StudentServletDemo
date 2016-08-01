package cn.tf.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.tf.entity.Classes;
import cn.tf.utils.DBHelper;



public class ClassesDao {
	
	private DBHelper db=new DBHelper();
	
	public int add(String cname){
		String sql="insert into classes values(0,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(cname);
		return db.update(sql,params);
	}
	
	public List<Map<String, Object>>  find(){	
		return db.find("select cid,cname from classes", null);
	}
	public List<Classes> finds(){
		return  db.find("select cid,cname from classes", null, Classes.class);
	}

}
