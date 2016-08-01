package cn.tf.entity;

import java.io.Serializable;

public class Student  implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sid;
	private int cid;
	private String sname;
	private int age;
	private String tel;
	private String photo;
	private String cname;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getSname() {
		return sname;
	}
	
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	public String getSnameStr() {
		//return "天方_"+sname;
		return  sname;
	}
	
	
	
	
	
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getPhoto() {
		
		if(photo!=null && photo.contains(",")){
			String[] str=photo.split(",");
			StringBuffer sb=new StringBuffer();
			for(String s:str){
				sb.append("<img src='../"+s+"' />&nbsp;");
			}
			return sb.toString();
			
		}else if(photo !=null && !"".equals(photo)){
			return "<img src='../"+photo+"' />";
		}else{
			return "";
		}

	}
	
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Student(int sid, int cid, String sname, int age, String tel,
			String photo, String cname) {
		super();
		this.sid = sid;
		this.cid = cid;
		this.sname = sname;
		this.age = age;
		this.tel = tel;
		this.photo = photo;
		this.cname = cname;
	}
	public Student() {
		super();
	}
	@Override
	public String toString() {
		return "{\"sid\":\"" + sid + "\",\"cid\":\"" + cid + "\",\"sname\":\"" + sname
				+ "\", \"age\":\"" + age + "\",\"tel\":\"" + tel + "\",\"photo\":\"" + photo
				+ "\",\"cname\":\"" + cname + "\"}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + cid;
		result = prime * result + ((cname == null) ? 0 : cname.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + sid;
		result = prime * result + ((sname == null) ? 0 : sname.hashCode());
		result = prime * result + ((tel == null) ? 0 : tel.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (age != other.age)
			return false;
		if (cid != other.cid)
			return false;
		if (cname == null) {
			if (other.cname != null)
				return false;
		} else if (!cname.equals(other.cname))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (sid != other.sid)
			return false;
		if (sname == null) {
			if (other.sname != null)
				return false;
		} else if (!sname.equals(other.sname))
			return false;
		if (tel == null) {
			if (other.tel != null)
				return false;
		} else if (!tel.equals(other.tel))
			return false;
		return true;
	}
	
	
	
	

}
