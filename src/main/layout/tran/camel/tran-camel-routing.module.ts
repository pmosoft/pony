import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TranCamelComponent } from './tran-camel.component';

const routes: Routes = [
  {
      path: '',
      component: TranCamelComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
 
export class TranCamelRoutingModule { }
  