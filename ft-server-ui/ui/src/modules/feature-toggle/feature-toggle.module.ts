import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatTableModule} from '@angular/material/table';
import {MatFormFieldModule, MatInputModule, MatSelectModule, MatSlideToggleModule} from '@angular/material';

import { FeatureToggleRoutingModule } from './feature-toggle-routing.module';
import { FeatureToggleComponent } from './components/feature-toggle/feature-toggle.component';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [FeatureToggleComponent],
  imports: [
    CommonModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatSlideToggleModule,
    FeatureToggleRoutingModule
  ]
})
export class FeatureToggleModule { }
