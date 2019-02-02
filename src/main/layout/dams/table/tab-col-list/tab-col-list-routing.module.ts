import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TabColListComponent } from './tab-col-list.component';

const routes: Routes = [
  {
      path: '',
      component: TabColListComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
 
export class TabColListRoutingModule { }
  
