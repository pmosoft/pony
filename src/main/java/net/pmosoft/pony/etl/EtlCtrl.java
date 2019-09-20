package net.pmosoft.pony.etl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.pmosoft.pony.dams.jdbc.JdbcInfo;
import net.pmosoft.pony.dams.table.TabInfo;

@RestController
@CrossOrigin(origins="http://localhost:4202")
public class EtlCtrl {

    @Autowired
    private EtlSrv etlSrv;
    
     /**
      * 
      */
    @RequestMapping(value = "/etl/executeDbToDb")
    public Map<String, Object> executeDbToDb(@RequestBody List<TabInfo> inVo) {
        return etlSrv.executeDbToDb(inVo);
    }

}
