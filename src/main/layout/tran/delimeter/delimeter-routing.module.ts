import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DelimeterComponent } from './delimeter.component';

const routes: Routes = [
  {
      path: '',
      component: DelimeterComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
 
export class DelimeterRoutingModule { }
  
