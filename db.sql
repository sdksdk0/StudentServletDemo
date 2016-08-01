
--班级信息
create table classes(
       cid int primary key,
       cname varchar2(100) unique

);
select *from classes;
select *from student;
--学生信息表
create table student(

	sid number(10) primary key,
	cid number(10) 
			constraint FK_student_cid references classes(cid),
	sname varchar2(100),
	age number(3),
	tel varchar2(15),
	photo varchar2(500)


);

create sequence seq_cid  start with 1001;
create sequence seq_sid start with 1001;


create or replace trigger  tri_classes before insert on classes  for each row  
begin 
	select seq_cid.nextval  into :new.cid from dual;
end ;


create or replace trigger  tri_student before insert on student  for each row  
begin 
	select seq_sid.nextval  into :new.sid from dual;
end ;


drop table classes;
drop table student;


commit;







