import { Component, OnInit, Input } from '@angular/core';
import { CodeService } from '../code.service';
import { Code } from '../code';
import { Router } from '@angular/router';

@Component({
  selector: 'app-code-list',
  templateUrl: './code-list.component.html', 
  styleUrls: ['./code-list.component.scss']
})
export class CodeListComponent implements OnInit {

  comboCode = [
     {id : 'ALL'       , name : '전체'     }
    ,{id : 'CD_GRP'    , name : '코드그룹'  }
    ,{id : 'CD_GRP_NM' , name : '코드그룹명' }
    ,{id : 'CD_ID'     , name : '코드ID'   }
    ,{id : 'CD_ID_NM'  , name : '코드ID명'  }
    ,{id : 'CD'        , name : '코드'      }
    ,{id : 'CD_NM'     , name : '코드명'    }
    ,{id : 'CD_DESC'   , name : '코드설명'   }
  ];  
  
  codeInVo: Code = new Code();
  codeOutVoList: Code[]
 
  constructor(private codeService: CodeService
             ,private router: Router) { }

  ngOnInit() {
  }


  onComboCodeChange(id) {
    console.log(id);
    this.codeInVo.comboCodeId = id;
  }
  
  onSelectCodeList() {
    
    this.codeService.selectCodeList(this.codeInVo)
    .subscribe(result => {
       if(!result.isSuccess) alert(result.errUsrMsg)
      else {
        this.codeOutVoList = result.codeOutVoList;  
        //console.log(result.codeOutVoList);  
      } 
    });
  }

  onDownloadExcel() {

    let data = JSON.stringify(this.codeOutVoList);
    //console.log(data);
    
    const fd = new FormData();
    fd.append('fileNm', "code-list.xls");
    fd.append('data', data);
    
    this.codeService.downloadExcel(fd)
    .subscribe(result => {
       if(!result.isSuccess) alert(result.errUsrMsg)
      else {
      } 
    });
  }

}
