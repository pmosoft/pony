import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DelimeterComponent } from './delimeter.component';
import { DelimeterRoutingModule } from './delimeter-routing.module';

@NgModule({
  imports: [
    CommonModule,DelimeterRoutingModule,FormsModule
  ],
  declarations: [DelimeterComponent]
})
export class DelimeterModule { }
