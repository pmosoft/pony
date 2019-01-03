package test.jdbc.mybatisLogback.oracle;

import java.util.ArrayList;

public interface EmpDao {

    public ArrayList<EmpDto> selectEmp();    
    public ArrayList<EmpDto> selectSqlite();    
    public ArrayList<EmpDto> selectMariadb();    
	
    public void updateEmp(final String ename,final int empno);

    public void deleteEmp(final int empno);

}
