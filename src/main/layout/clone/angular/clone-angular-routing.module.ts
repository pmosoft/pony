import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CloneAngularComponent } from './clone-angular.component';

const routes: Routes = [
  {
      path: '',
      component: CloneAngularComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
 
export class CloneAngularRoutingModule { }
  