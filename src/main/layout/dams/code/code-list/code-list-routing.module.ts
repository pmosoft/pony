import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CodeListComponent } from './code-list.component';

const routes: Routes = [
  {
      path: '',
      component: CodeListComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
 
export class CodeListRoutingModule { }
  