import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CloneAngularComponent } from './clone-angular.component';
import { CloneAngularRoutingModule } from './clone-angular-routing.module';

@NgModule({
  imports: [
    CommonModule,CloneAngularRoutingModule,FormsModule
  ],
  declarations: [CloneAngularComponent]
})
export class CloneAngularModule { }
