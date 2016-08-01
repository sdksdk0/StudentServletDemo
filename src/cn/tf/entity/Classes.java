package cn.tf.entity;

import java.io.Serializable;

public class Classes implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	private int cid;
	private String cname;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Classes [cid=" + cid + ", cname=" + cname + "]";
		
		//return "{\"cid\":\""+cid+"\",\"cname\":\""+cname+"\"}";
	}
	public Classes(int cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	public Classes() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cid;
		result = prime * result + ((cname == null) ? 0 : cname.hashCode());
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
		Classes other = (Classes) obj;
		if (cid != other.cid)
			return false;
		if (cname == null) {
			if (other.cname != null)
				return false;
		} else if (!cname.equals(other.cname))
			return false;
		return true;
	}
	
	
}
