package test.jdbc.mybatis.sqlite;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

class TestJdbcMybatisOracle implements EmpDao {

	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}	
	
    public ArrayList<EmpDto> selectEmp(){
    	EmpDao dao = sqlSession.getMapper(EmpDao.class);
    	ArrayList<EmpDto> empList = dao.selectEmp();

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
    
}
