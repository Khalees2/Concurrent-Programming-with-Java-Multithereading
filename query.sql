DROP TABLE ACCOUNTS;
USE assignmentOne;
CREATE TABLE DEPARTMENT (
deptId int , deptName text
);

SELECT  * from department;

CREATE TABLE ACCOUNTS (
AccountNumber int , Balance int 
);

desc ACCOUNTS;
select COUNT(*) from accounts;

select * from accounts;

Update ACCOUNTS SET BALANCE = 0 WHERE AccountNumber=1;

SELECT * FROM ACCOUNTS where AccountNumber=1;
commit;

show tables;




