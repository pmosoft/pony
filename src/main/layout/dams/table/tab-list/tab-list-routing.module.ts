import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TabListComponent } from './tab-list.component';

const routes: Routes = [
  {
      path: '',
      component: TabListComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
 
export class TabListRoutingModule { }
  
