import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tran-camel',
  templateUrl: './tran-camel.component.html', 
  styleUrls: ['./tran-camel.component.scss']
})
export class TranCamelComponent implements OnInit {

  srcTxt = ""
  tarTxt = ""

  constructor(private router: Router) { }

  ngOnInit() {
  }

  onChangeSrcTxt(ev) {this.srcTxt = ev.target.value;}
  onChangeTarTxt(ev) {this.tarTxt = ev.target.value;}

  tranCamelToBar() {
    console.log("aaaa");

    var strTmp = this.srcTxt;
    console.log("strTmp="+strTmp);
    var strRtn = "";
    var flag = false;
    
    for(var i=0; i <strTmp.length; i++){
    	if(strTmp[i].match(/[A-Z]/)){
    		strRtn += "_";
    	}
    	strRtn += strTmp[i];
    }
    strRtn = strRtn.toUpperCase();
    
    this.tarTxt = strRtn; 
  }

  tranBarToCamel() {
    console.log("aaaabbb");
    var strTmp = this.srcTxt.toLowerCase();
    var strRtn = "";
    var flag = false;
    
    for(var i=0; i <strTmp.length; i++){
    	if(strTmp[i] == "_"){
    		flag = true;    		
    	} else {
    		if(flag){
    			strRtn += strTmp[i].toUpperCase();
    			flag = false;
    		} else {
    		  strRtn += strTmp[i];
    		}
    	}
    }		

    this.tarTxt = strRtn;
  }


}
