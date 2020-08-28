import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FeatureToggleComponent } from './components/feature-toggle/feature-toggle.component';

const routes: Routes = [{ path: '', component: FeatureToggleComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FeatureToggleRoutingModule { }
