---------------------------------
-- 계정
---------------------------------
mysql -uroot -plife200727

---------------------------------
-- database 생성
---------------------------------
create database pony;

---------------------------------
-- 유저 생성
---------------------------------

-- 모든 ip 허용
create user 'pony'@'%' identified by 'p1234';
grant all privileges on pony.* to 'pony'@'%';

create user 'pony'@'localhost' identified by 'p1234';
create user 'pony'@'pmosoft.net' identified by 'p1234';
create user 'pony'@'182.228.242.133' identified by 'p1234'; -- cafe24 보안서버

grant all privileges on pony.* to pony@localhost;
grant all privileges on pony.* to pony@pmosoft.net;
grant all privileges on pony.* to pony@182.228.242.133;

