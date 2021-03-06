package net.pmosoft.pony.tran;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4202")
public class TranCtrl {

    @Autowired
    private TranSrv tranSrv;

    @RequestMapping(value = "/tran/delimiterToRows")
    public Map<String, Object> delimiterToRows(@RequestBody Tran tran) {
        return tranSrv.delimiterToRows(tran);
    }

    @RequestMapping(value = "/tran/thinq")
    public Map<String, Object> thinq(@RequestBody Tran tran) {
        return tranSrv.thinq(tran);
    }
    
    
}
