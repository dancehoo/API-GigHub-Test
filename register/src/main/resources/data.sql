insert into mt_member_classified (id,class_description,min_salary) 
values(1,'Silver',15000);

insert into mt_member_classified (id,class_description,min_salary) 
values(2,'Gold',30001);

insert into mt_member_classified (id,class_description,min_salary) 
values(3,'Platinum',50001);

--api@test
insert into tran_member (email, password_hash, salary, classified_id,created_by,updated_by ) 
values('note_45@yahoo.com','d141e3b305c0fb410c3fe7ae8f7fefdc',123456,3,'ADMIN','ADMIN');
