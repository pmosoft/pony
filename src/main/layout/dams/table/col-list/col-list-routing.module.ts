import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ColListComponent } from './col-list.component';

const routes: Routes = [
  {
      path: '',
      component: ColListComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
 
export class ColListRoutingModule { }
  
