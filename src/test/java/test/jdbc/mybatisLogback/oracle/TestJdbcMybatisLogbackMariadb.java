package test.jdbc.mybatisLogback.oracle;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

class TestJdbcMybatisLogbackMariadb implements EmpDao {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}	
	
    public ArrayList<EmpDto> selectEmp(){
    	EmpDao dao = sqlSession.getMapper(EmpDao.class);
    	ArrayList<EmpDto> empList = dao.selectMariadb();

        for(int i=0;i<empList.size();i++){
            System.out.println("empno="+empList.get(i).empno+"   "+"ename="+empList.get(i).ename );
        }    	
    	
    	return empList;
    }

	public void updateEmp(String ename, int empno) {
		// TODO Auto-generated method stub
	}

	public void deleteEmp(int empno) {
		// TODO Auto-generated method stub
	}

    @Override
    public ArrayList<EmpDto> selectSqlite() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<EmpDto> selectMariadb() {
        // TODO Auto-generated method stub
        return null;
    }    
    
}
