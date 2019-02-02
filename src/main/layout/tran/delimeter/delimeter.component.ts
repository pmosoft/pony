import { Component, OnInit, Input } from '@angular/core';
import { TranService } from '../tran.service';
import { Tran } from '../tran';
import { Router } from '@angular/router';

@Component({
  selector: 'app-delimeter',
  templateUrl: './delimeter.component.html',
  styleUrls: ['./delimeter.component.scss']
})
export class DelimeterComponent implements OnInit {

  tranInVo: Tran = new Tran();
  tranOutVo: Tran = new Tran();

  tokenCnt = 0;
  delimeterCnt = 0;
  constructor(private tranService: TranService
             ,private router: Router) { }

  ngOnInit() {
  }

  onDelimeterToRows() {
    console.log(this.tranInVo.strToken);
    console.log(this.tranInVo.delimeter);
    this.tranService.delimeterToRows(this.tranInVo)
    .subscribe(result => {
       if(!result.isSuccess) alert(result.errUsrMsg)
      else {
        this.tranOutVo = result.tranOutVo;
        this.tokenCnt = this.tranOutVo.tokenCnt;
        this.delimeterCnt = this.tranOutVo.delimeterCnt;

        console.log(result.tranOutVo);
      }
    });
  }
}
