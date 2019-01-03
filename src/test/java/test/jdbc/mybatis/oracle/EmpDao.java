package test.jdbc.mybatis.oracle;

import java.util.ArrayList;

public interface EmpDao {

    public ArrayList<EmpDto> selectEmp();    
	
    public void updateEmp(final String ename,final int empno);

    public void deleteEmp(final int empno);

}
