import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CodeListComponent } from './code-list.component';
import { CodeListRoutingModule } from './code-list-routing.module';

@NgModule({
  imports: [
    CommonModule,CodeListRoutingModule,FormsModule
  ],
  declarations: [CodeListComponent]
})
export class CodeListModule { }
