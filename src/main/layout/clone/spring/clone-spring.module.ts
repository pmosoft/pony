import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CloneSpringComponent } from './clone-spring.component';
import { CloneSpringRoutingModule } from './clone-spring-routing.module';

@NgModule({
  imports: [
    CommonModule,CloneSpringRoutingModule,FormsModule
  ],
  declarations: [CloneSpringComponent]
})
export class CloneSpringModule { }
