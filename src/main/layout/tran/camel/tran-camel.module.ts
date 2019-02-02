import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranCamelComponent } from './tran-camel.component';
import { TranCamelRoutingModule } from './tran-camel-routing.module';

@NgModule({
  imports: [
    CommonModule,TranCamelRoutingModule
  ],
  declarations: [TranCamelComponent]
})
export class TranCamelModule { }
