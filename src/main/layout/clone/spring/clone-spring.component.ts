import { Component, OnInit, Input } from '@angular/core';
import { GensService } from '../gens.service';
import { Gens } from '../gens';
import { Router } from '@angular/router';

@Component({
  selector: 'app-clone-spring',
  templateUrl: './clone-spring.component.html',
  styleUrls: ['./clone-spring.component.scss']
})
export class CloneSpringComponent implements OnInit {

  gensInVo: Gens = new Gens();
  gensOutVo: Gens = new Gens();

  constructor(private gensService: GensService
             ,private router: Router) { }

  ngOnInit() {
    this.gensInVo.pkgComNm = localStorage.getItem('pkgComNm')
    this.gensInVo.srcPathNm = localStorage.getItem('srcSpringPathNm')
    this.gensInVo.srcClassNm  = localStorage.getItem('srcSpringClassNm')
    this.gensInVo.tarPathNm = localStorage.getItem('tarSpringPathNm')
    this.gensInVo.tarClassNm  = localStorage.getItem('tarSpringClassNm')
    console.log("this.gensInVo.srcPathNm="+this.gensInVo.srcPathNm);
  }

  onSave() {
    console.log("onSave");
    localStorage.setItem('pkgComNm'         , this.gensInVo.pkgComNm  );
    localStorage.setItem('srcSpringPathNm'  , this.gensInVo.srcPathNm );
    localStorage.setItem('srcSpringClassNm' , this.gensInVo.srcClassNm);
    localStorage.setItem('tarSpringPathNm'  , this.gensInVo.tarPathNm );
    localStorage.setItem('tarSpringClassNm' , this.gensInVo.tarClassNm);
  }

  onExecute() {
    this.onSave();
    this.gensService.cloneSpring(this.gensInVo)
    .subscribe(result => {
       if(!result.isSuccess) alert(result.errUsrMsg)
      else {
        this.gensOutVo = result.gensOutVo;
        //console.log(result.codeOutVoList);
      }
    });
  }

}
