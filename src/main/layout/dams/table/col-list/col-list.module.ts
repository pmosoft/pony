import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ColListComponent } from './col-list.component';
import { ColListRoutingModule } from './col-list-routing.module';

@NgModule({
  imports: [
    CommonModule,ColListRoutingModule,FormsModule
  ],
  declarations: [ColListComponent]
})
export class ColListModule { }
