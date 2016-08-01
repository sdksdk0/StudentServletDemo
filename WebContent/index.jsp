<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta  charset="UTF-8">
<title>添加</title>

<script type="text/javascript" src="js/jquery-1.11.3.js"  ></script>
<script type="text/javascript" src="js/ajaxfileupload.js" ></script>
<script type="text/javascript" src="js/showPic.js"></script>
</head>
<style>
	table tr td img{
		width:200px;
		height:150px;
	}
	
	 a{
		text-decoration: none;
		padding:2px 10px;
		color:#fff;
		font-weight:bold;
		background:#ccc;
		margin-left:10px;
	}
	 a:hover{
		background-color:#ccc;
	}
	
	.over{
		background-color:blue;
	}
	.out{
		background-color:gray;
	}
	

</style>


	<script>
	
		function addClasses(){
			var cname=$.trim($("#cname").val());
			
			if(cname==""){
				$("#cname").next().next().text(" 请输入班级名称").css("color","red");
			}else{
				$.post("${pageContext.request.contextPath}/servlet/ClassesServlet",{op:'addClasses',cname:cname},function(data){
					data=parseInt($.trim(data));
					if(data>0){
						$("#cname").next().next().text("班级信息添加成功").css("color","green");
						$("#cname").val("");
						$("#classesInfo").html("");
						classesFind();
					}else{
						$("#cname").next().next().text("班级信息添加失败").css("color","red");
					}
				});
			}
			
			
		}
		
		/* $(function(){
				$.post("doaddclasses.jsp",{op:"findClasses"},function(data){
					data=$.trim(data);
					if(data!=""){
						var arr=data.split(",");
						for(var i=0;i<arr.length;i++){
							var cls=arr[i].split("-");
							$("#classesInfo").append($("<option value='"+cls[0]+"'>"+cls[1]+"</option>"));
						}
					}
				});
		}); */
	
		$(function(){
			
			classesFind();
		});
		
		function classesFind(){
			$.post("${pageContext.request.contextPath}/servlet/ClassesServlet",{op:"findClasses"},function(data){			
				if(data!=""){
					$.each(data,function(index,item){
						$("#classesInfo").append( $("<option value='"+item.cid+"'>"+item.cname+"</option>"));
					});
				}
			},"json");	
			firstPageOP();
			
		};
		
		
		function addStudent(){
			var sname=$.trim( $("#sname").val());
			var age=$.trim( $("#age").val());
			var tel=$.trim( $("#tel").val());
			var cid=$.trim( $("#classesInfo").val());
			
			$.ajaxFileUpload({
				url:"${pageContext.request.contextPath}/servlet/StudentServlet?op=addStudent",
				secureuri:false,
				fileElementId:"photo",//要上传的文件的id，如果有多个file的表单元素，则用数组
				dataType:"json",
				data:{cid:cid,sname:sname,age:age,tel:tel},
				success:function(data,status){
					data=$.trim(data);
					if(parseInt(data)==1){
						$("#sname").val("");
						$("#age").val("");
						$("#tel").val("");
						
						$("#showPicDiv").css("display","none");
						$("#photo").val("");
						firstPageOP();
					}else{
						alert("添加学生信息失败...");
					}
				},
				error:function(data,status,e){
					alert("添加学生信息失败...");
				}
			});
		}
			
			/* $.post("dostudent.jsp",{op:"addStudent",sname:sname,age:age,tel:tel,cid:cid},function(data){
				
		
				if(data==null){
					alert("数据添加失败");
				}else{
					$("#sname").val("");
					$("#age").val("");
					$("#tel").val("");
					$("#show_student").html("");

					$.each(data,function(index,item){
						$("#show_student").append("<tr><td></td><td>"+item.sid+"</td><td>"+ item.sname +"</td><td>"
								+item.age+"</td><td>"+item.tel+"</td><td>"+ item.cname +"</td></tr>");
							
					});
				}
			},"json"); */
		//}
	


	//分页操作
	function firstPageOP(){
			$.post("doshow.jsp",{op:1,pageNo:1,pageSize:5},function(data){
				var str="";
				$.each(data.studentInfo,function(index,item){
					str+="<tr><td>"+item.photo+"</td><td>"+item.sid+"</td><td>"+
					item.sname+"</td><td>"+item.age+"</td><td>"+item.tel+"</td><td>"+item.cname+"</td><td>删除</td></tr>";
				});
				$("#show_student").html("").append($(str));
				
				var total=parseInt(data.total);
				var page="";
				for(var i=0;i<total/5;i++){
					if(i==0){
						page+="<a href='javascript:findStudentByPage("+(i+1)+",5)' class='over'>"+(i+1)+"</a>";
					}else{
						page+="<a href='javascript:findStudentByPage("+(i+1)+",5)' class='out'>"+(i+1)+"</a>";
					}
				}
				$("#pageInfo").html("").append( $(page));
			},"json");
		}
	
	function findStudentByPage(pageNo,pageSize){
		$.post("doshow.jsp",{op:2,pageNo:pageNo,pageSize:pageSize},function(data){
			var str="";
			$.each(data.studentInfo,function(index,item){
				str+="<tr><td>"+item.photo+"</td><td>"+item.sid+"</td><td>"+
				item.sname+"</td><td>"+item.age+"</td><td>"+item.tel+"</td><td>"+item.cname+"</td><td>删除</td></tr>";
			});
			$("#show_student").html("").append($(str));
		},"json");
		
		$("#pageInfo a").attr("class","out");
		$("#pageInfo a").eq(pageNo-1).attr("class","over");
	}
	

</script>


<body>

	<fieldset>
	<legend>添加班级</legend>
	<form method="post">
		班级名称:<input type="text" name="cname" id="cname"/>
		<input type="button" value="添加班级" onclick="addClasses()" /><span></span>
	</form>
</fieldset>

<center><h1><a href=" ${pageContext.request.contextPath}/servlet/StudentServlet?op=findStudent"  >查看学生信息</a></h1></center>
<fieldset>

	<legend>添加学生</legend>
	
	<form action="" method="post" id="add_student">
		班级：<select name="cid" id="classesInfo">
		
		</select>
		姓名：<input type="text" name="sname" id="sname" />
		年龄：<input type="number" name="age" id="age" />
		联系方式：<input type="text" name="tel" id="tel" />
		图像：<input type="file" name="photo" id="photo" multiple="multiple" onchange="setImagePreviews(this,'showPicDiv')" />
		<input type="button" value="添加" onclick="addStudent()" />
	</form>
	<div id="showPicDiv" style="width:840px; style="display:none"></div>
</fieldset>

<filedset>
	<legend>学生信息浏览</legend>
	<table border="1px" cellspacing="0px" cellpadding="0px" width="90%" align="center" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th>图像</th>
			<th>学号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>联系方式</th>
			<th>所在班级</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody id="show_student" align="center">	
		
	</tbody>
	</table><br /><br />
	<center id="pageInfo">
	
	</center>
</filedset>



</body>
</html>