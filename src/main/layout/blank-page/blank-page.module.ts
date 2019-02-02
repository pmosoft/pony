import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BlankPageRoutingModule } from './blank-page-routing.module';
import { BlankPageComponent } from './blank-page.component';

import { AgGridModule } from 'ag-grid-angular';


@NgModule({
    imports: [CommonModule, BlankPageRoutingModule,AgGridModule.withComponents([])],
    declarations: [BlankPageComponent]
})
export class BlankPageModule {}
