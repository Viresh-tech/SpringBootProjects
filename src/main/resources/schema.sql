
DROP TABLE IF EXISTS student_Entity;

CREATE TABLE student_Entity (
    id int PRIMARY KEY,
    student_Name varchar(255),
    student_Address varchar(255)
);

create or replace procedure cnt_student(cou OUT NUMBER) is
begin 
    select count(*) into cou  from student_Entity;
end;