import { Component, OnInit, Input } from '@angular/core';
import { TabInfoService } from '../tab-info.service';
import { TabInfo } from '../tab-info';
import { JdbcInfoService } from '../../jdbc/jdbc-info.service';
import { JdbcInfo } from '../../jdbc/jdbc-info';
import { Router } from '@angular/router';

@Component({
  selector: 'app-col-list',
  templateUrl: './col-list.component.html',
  styleUrls: ['./col-list.component.scss']
})
export class ColListComponent implements OnInit {

  tabInfoInVo: TabInfo = new TabInfo();
  tabInfoOutVoList: TabInfo[]
  jdbcInfoInVo: JdbcInfo = new JdbcInfo();

  //comboJdbc : JdbcCombo[];
  comboJdbc : JdbcInfo[];
  comboOrderBy = [
      {name : '선택(정렬)' , value : 'JDBC_NM' }
     ,{name : '컬럼명'   , value : 'COL_NM'  }
     ,{name : '컬럼한글명', value : 'COL_HNM'  }
     ,{name : '타입'     , value : 'DATA_TYPE_DESC'}
     ,{name : 'Rows'     , value : 'TAB_ROWS'}
     ,{name : '테이블명'   , value : 'TAB_NM'  }
  ];
  comboAscDesc = [
     {name : 'ASC' , value : 'ASC' }
    ,{name : 'DESC', value : 'DESC'}
  ];

  constructor(private tabInfoService: TabInfoService
             ,private jdbcInfoService: JdbcInfoService
             ,private router: Router) { }

  ngOnInit() {
    this.onSetComboJdbc();
  }

  onSetComboJdbc() {
    this.jdbcInfoService.selectComboJdbcList(this.jdbcInfoInVo)
    //this.jdbcInfoService.selectJdbcInfoList(this.jdbcInfoInVo)
    .subscribe(result => {
      if(!result.isSuccess) alert(result.errUsrMsg)
      else {
        //console.log(result.jdbcInfoOutVoList);
        this.comboJdbc = result.jdbcInfoOutVoList;
      }
    });
  }

  onChangeComboJdbc(i) {
    //console.log("event.value=="+event.value);
    //console.log("event.value=="+event.selectedIndex);
    //console.log("event.value=="+event.options[event.selectedIndex]);
    //console.log("event.value=="+event.options[event.selectedIndex].owner);
    //var aa = event.options[event.selectedIndex];
    //console.log("event.value=="+aa.owner);
    //console.log("event.value=="+this.comboJdbc[0].usrId);
    //console.log(usrId);
    //console.log("aa".toUpperCase());
    if(i==0) this.tabInfoInVo = new TabInfo();
    else {
      this.tabInfoInVo.jdbcNm = this.comboJdbc[i].jdbcNm;
      this.tabInfoInVo.owner = this.comboJdbc[i].usrId;
    }
    this.onSelectColList();
  }
  onChangeComboOrderBy(i) {
    this.tabInfoInVo.orderBy = this.comboOrderBy[i].value;
    this.onSelectColList();
  }
  onChangeComboAscDesc(i) {
    this.tabInfoInVo.ascDesc = this.comboAscDesc[i].value;
    this.onSelectColList();
  }

  onSelectColList(){
    this.tabInfoService.selectColList(this.tabInfoInVo)
    .subscribe(result => {
       if(!result.isSuccess) alert(result.errUsrMsg)
      else {
        this.tabInfoOutVoList = result.tabInfoOutVoList;
        //console.log(result.tabInfoOutVoList);
        //alert("onSelectMetaTabInfoList");
      }
    });
  }



  onDownloadExcel() {

    // let data = JSON.stringify(this.codeOutVoList);
    // //console.log(data);

    // const fd = new FormData();
    // fd.append('fileNm', "col-list.xls");
    // fd.append('data', data);

    // this.codeService.downloadExcel(fd)
    // .subscribe(result => {
    //    if(!result.isSuccess) alert(result.errUsrMsg)
    //   else {
    //   }
    // });
  }


}
