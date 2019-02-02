import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { JdbcInfoComponent } from './jdbc-info.component';

const routes: Routes = [
  {
      path: '',
      component: JdbcInfoComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
 
export class JdbcInfoRoutingModule { }
  
