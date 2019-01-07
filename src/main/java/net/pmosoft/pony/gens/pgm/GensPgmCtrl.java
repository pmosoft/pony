package net.pmosoft.pony.gens.pgm;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
@CrossOrigin(origins="http://localhost:4202")
public class GensPgmCtrl {

    @Autowired
    private GensPgmSrv gensPgmSrv;

    @RequestMapping(value = "/gens/pgm/cloneAngular")
    public Map<String, Object> cloneAngular(@RequestBody Gens gens) {
        return gensPgmSrv.cloneAngular(gens);
    }
    
    
    @RequestMapping(value = "/gens/pgm/genPgmByTmpl")
    public Map<String, Object> genPgmByTmpl(@RequestParam Map<String,String> params) {
        return gensPgmSrv.genPgmByTmpl(params);
    }

    @RequestMapping(value = "/gens/pgm/genPgmByCopy")
    public Map<String, Object> genPgmByCopy(@RequestParam Map<String,String> params) {
        return gensPgmSrv.genPgmByCopy(params);
    }
    
}
