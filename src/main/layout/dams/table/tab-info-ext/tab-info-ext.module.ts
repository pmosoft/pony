import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { TabInfoExtComponent } from './tab-info-ext.component';
import { TabInfoExtRoutingModule } from './tab-info-ext-routing.module';

@NgModule({
  imports: [
    CommonModule,TabInfoExtRoutingModule,FormsModule
  ],
  declarations: [TabInfoExtComponent]
})
export class TabInfoExtModule { }
