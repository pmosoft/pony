import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { JdbcInfoComponent } from './jdbc-info.component';
import { JdbcInfoRoutingModule } from './jdbc-info-routing.module';

@NgModule({
  imports: [
    CommonModule,JdbcInfoRoutingModule,FormsModule
  ],
  declarations: [JdbcInfoComponent]
})
export class JdbcInfoModule { }
