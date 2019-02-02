import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TabInfoExtComponent } from './tab-info-ext.component';

const routes: Routes = [
  {
      path: '',
      component: TabInfoExtComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
 
export class TabInfoExtRoutingModule { }
  
