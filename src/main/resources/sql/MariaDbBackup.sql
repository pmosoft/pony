mysqldump -uroot -p1 pony > pony_backup.sql

1.root 에 db계정명을 적습니다.

2.pass 부분에 mysql 비밀번호를 적습니다. 비밀번호를 적지 않고 넘어가면 mysqldump 명령어 수행시 비밀번호를 물어봅니다.

3.dbname 부분에 db명을 적습니다,

4.backup 부분에 백업할 파일명을 적습니다. 



mysql 전체 DB 백업

# mysqldump -uroot -p -A > backup.sql

혹은

# mysqldump -uroot -p --all-databases > backup.sql



mysql 특정 db의 특정 테이블만 백업

* dump 데이터베이스의 test 테이블만 백업합니다.

# mysqldump -uroot -p dump test > dumptest.sql



mysql schema 정보만 백업

# mysqldump -uroot -p --no-data schemainfo > schemainfo.sql



모든 db 복원

# mysql -uroot -p < backup.sql



출처: http://wordpressxe.tistory.com/10 [유츠]