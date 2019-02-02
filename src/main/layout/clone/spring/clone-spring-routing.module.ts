import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CloneSpringComponent } from './clone-spring.component';

const routes: Routes = [
  {
      path: '',
      component: CloneSpringComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
 
export class CloneSpringRoutingModule { }
  
