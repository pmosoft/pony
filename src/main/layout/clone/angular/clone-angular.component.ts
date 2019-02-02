import { Component, OnInit, Input } from '@angular/core';
import { GensService } from '../gens.service';
import { Gens } from '../gens';
import { Router } from '@angular/router';

@Component({
  selector: 'app-clone-angular',
  templateUrl: './clone-angular.component.html', 
  styleUrls: ['./clone-angular.component.scss']
})
export class CloneAngularComponent implements OnInit {

  gensInVo: Gens = new Gens();
  gensOutVo: Gens = new Gens();
 
  constructor(private gensService: GensService
             ,private router: Router) { }

  ngOnInit() {
    this.gensInVo.srcPathNm = localStorage.getItem('srcPathNm')
    this.gensInVo.srcBarNm = localStorage.getItem('srcBarNm')
    this.gensInVo.tarPathNm = localStorage.getItem('tarPathNm')
    this.gensInVo.tarBarNm = localStorage.getItem('tarBarNm')
    console.log("this.gensInVo.srcPathNm="+this.gensInVo.srcPathNm);
  }
 
  onSave() {
    console.log("onSaveSrcPath");
    localStorage.setItem('srcPathNm', this.gensInVo.srcPathNm);
    localStorage.setItem('srcBarNm', this.gensInVo.srcBarNm);
    localStorage.setItem('tarPathNm', this.gensInVo.tarPathNm);
    localStorage.setItem('tarBarNm', this.gensInVo.tarBarNm);
  }

  onExecute() {
    this.onSave();
    this.gensService.cloneAngular(this.gensInVo)
    .subscribe(result => {
       if(!result.isSuccess) alert(result.errUsrMsg)
      else {
        this.gensOutVo = result.gensOutVo;  
        //console.log(result.codeOutVoList);  
      }  
    });
  }

}
