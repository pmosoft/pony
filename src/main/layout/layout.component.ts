import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-layout',
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {


    constructor(private router: Router) {}

    ngOnInit() {
        //this.router.navigate(['/pony-regist']);

        // this.ponyInVo.usrId = this.usrId;
        // this.ponyService.selectUsrSttlMstrList(this.ponyInVo)
        // .subscribe(result => {
        //    if(!result.isSuccess) alert(result.errUsrMsg)
        //   else {
        //     this.usrSttlVoList = result.usrSttlVoList;
        //     if(this.usrSttlVoList.length==0) 
        //          this.router.navigate(['/pony-regist']);
        //     else this.router.navigate(['/pony-view/:blank']);
        //   } 
        // });
    }
}
