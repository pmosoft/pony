import { Component, OnInit, Input } from '@angular/core';
import { JdbcInfoService } from './jdbc-info.service';
import { JdbcInfo } from './jdbc-info';
import { Router } from '@angular/router';
import { TabInfo } from '../table/tab-info';

@Component({
  selector: 'app-jdbc-info',
  templateUrl: './jdbc-info.component.html',
  styleUrls: ['./jdbc-info.component.scss']
})
export class JdbcInfoComponent implements OnInit {

  jdbcInfoInVo: JdbcInfo = new JdbcInfo();
  jdbcInfoOutVoList: JdbcInfo[];
  tabInfoInVo : TabInfo = new TabInfo();

  selectedValue : string = "mariadb";


  //----------------
  // select
  //----------------
  comboDriver = [
     {value: "oracle" , name: "oracle" }
    ,{value: "mariadb", name: "mariadb"}
    ,{value: "sqlite" , name: "sqlite" }
//    ,{value: "db2"    , name: "db2"    }
//    ,{value: "sybase" , name: "sybase" }
//    ,{value: "IQ"     , name: "iq"     }
//    ,{value: "mssql"  , name: "mssql"  }
//    ,{value: "Exadata", name: "exadata"}
//    ,{value: "mysql"  , name: "mysql"  }
//    ,{value: "hsqldb" , name: "hsqldb" }
//    ,{value: "postgre", name: "postgre"}
  ];


  constructor(private jdbcInfoService: JdbcInfoService
             ,private router: Router) { }

  ngOnInit() {
    this.onSelect();
  }

  onComboChange(comboValue) {
    this.selectedValue = comboValue;
    this.jdbcInfoInVo.driver = comboValue;
  }

  onClick(jdbcInfoInVo) {
    this.jdbcInfoInVo = jdbcInfoInVo;
    this.selectedValue = jdbcInfoInVo.driver
  }


  onNew() {
    this.jdbcInfoInVo = new JdbcInfo();
    this.selectedValue = "oracle";

  }

  onSave() {
    console.log("onSave");
    this.jdbcInfoInVo.driver = this.selectedValue;
    this.jdbcInfoService.saveJdbcInfo(this.jdbcInfoInVo)
    .subscribe(result => {
       if(!result.isSuccess) alert(result.errUsrMsg)
      else {
        this.jdbcInfoOutVoList = result.jdbcOutVo;
        this.onSelect();
        //console.log(result.codeOutVoList);
      }
    });
  }

  onSelect() {
    this.jdbcInfoService.selectJdbcInfoList(this.jdbcInfoInVo)
    .subscribe(result => {
      if(!result.isSuccess) alert(result.errUsrMsg)
      else {
        this.jdbcInfoOutVoList = result.jdbcInfoOutVoList;
        console.log(result.jdbcInfoOutVoList);
        console.log(result.jdbcInfoOutVoJson);
      }
    });
  }

  onDelete() {
    this.jdbcInfoService.deleteJdbcInfo(this.jdbcInfoInVo)
    .subscribe(result => {
      if(!result.isSuccess) alert(result.errUsrMsg)
      else {
        this.onNew();
        this.onSelect();
      }
    });
  }

  onTest() {
    console.log("onTest");
    this.tabInfoInVo.jdbcNm = this.jdbcInfoInVo.jdbcNm;
    this.tabInfoInVo.owner = this.jdbcInfoInVo.usrId;
    this.jdbcInfoService.testJdbcInfo(this.tabInfoInVo)
    .subscribe(result => {
      if(!result.isSuccess)
        //alert(result.errUsrMsg)
        alert("Fail");
      else {
        alert("Success");
        //console.log(result.codeOutVoList);
      }
    });
  }


}
