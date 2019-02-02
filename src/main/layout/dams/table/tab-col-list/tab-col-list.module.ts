import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { TabColListComponent } from './tab-col-list.component';
import { TabColListRoutingModule } from './tab-col-list-routing.module';

@NgModule({
  imports: [
    CommonModule,TabColListRoutingModule,FormsModule
  ],
  declarations: [TabColListComponent]
})
export class TabColListModule { }
