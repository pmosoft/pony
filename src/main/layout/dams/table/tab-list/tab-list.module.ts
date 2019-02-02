import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { TabListComponent } from './tab-list.component';
import { TabListRoutingModule } from './tab-list-routing.module';

@NgModule({
  imports: [
    CommonModule,TabListRoutingModule,FormsModule
  ],
  declarations: [TabListComponent]
})
export class TabListModule { }
