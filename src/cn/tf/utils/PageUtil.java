package cn.tf.utils;

//分页
public class PageUtil {
	
	private int pageNo=1;
	private int pageSize=10;
	private int totalSize;
	private int totalPage;
	
	public PageUtil(int totalSize,int pageSize){
		this.totalSize=totalSize;
		this.pageSize=pageSize;
		setTotalPage();  //自动计算页数
	}
	
	//上一页
	public void getPrePageNo(){
		if(pageNo>1){
			pageNo--;
		}else{
			pageNo=1;
		}
	}

	//下一页
	public void getNextPageNo(){
		if(pageNo<totalPage){
			pageNo++;
		}else{
			pageNo=totalPage;
		}
	}
	
	//计算总页数
	private void setTotalPage(){
		totalPage=this.totalSize%this.pageSize==0?this.totalSize/this.pageSize:this.totalSize/this.pageSize+1;
	}
	
	
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		
		if(pageSize<=0){
			pageSize=10;
		}
		
		this.pageSize = pageSize;
		setTotalPage();   //当每页显示的记录数发生改变的时候，重新计算总页数
	}

	public int getTotalSize() {
		
		return totalSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalSize(int totalSize) {
		
		if(totalSize<0){
			totalSize=0;
		}
		this.totalSize = totalSize;
		setTotalPage();  
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	

}
