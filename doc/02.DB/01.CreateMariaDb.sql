---------------------------------
-- 계정
---------------------------------
mysql -uroot -plife200727

---------------------------------
-- database 생성
---------------------------------
create database push;

---------------------------------
-- 유저 생성
---------------------------------

-- 모든 ip 허용
create user 'push'@'%' identified by 'p1234';
grant all privileges on push.* to 'push'@'%';

create user 'push'@'localhost' identified by 'p1234';
create user 'push'@'pmosoft.net' identified by 'p1234';
create user 'push'@'182.228.242.133' identified by 'p1234'; -- cafe24 보안서버

grant all privileges on push.* to push@localhost;
grant all privileges on push.* to push@pmosoft.net;
grant all privileges on push.* to push@182.228.242.133;

