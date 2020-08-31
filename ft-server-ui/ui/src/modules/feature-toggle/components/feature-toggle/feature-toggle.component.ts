import { FeatureToggleService } from './../../service/feature-toggle.service';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-feature-toggle',
  templateUrl: './feature-toggle.component.html',
  styleUrls: ['./feature-toggle.component.scss']
})
export class FeatureToggleComponent implements OnInit {


  tenantIdentifiers: any = [];
  features: any = [];
  selectedTenant = '';

  displayedColumns: string[] = ['id', 'name', 'description', 'groupName', 'phase', 'appName', 'enabled'];
  dataSource = new MatTableDataSource(this.features);

  constructor(private featureToggleService: FeatureToggleService) { }

  ngOnInit() {
    this.featureToggleService.getAllTenents().subscribe(res => {
      this.tenantIdentifiers = res;
    });
  }

  tenantSelected(tenant) {
    console.log(tenant.value);
    this.featureToggleService.getFeaturesForTenant(tenant.value).subscribe((res: any) => {
      this.features = res.features;
      this.selectedTenant = tenant.value;
      this.dataSource = new MatTableDataSource(this.features);
    });
  }

  updateFeatureStatus(event, feature) {
    console.log(event.checked);
    this.featureToggleService.updateFeatureStatus(event.checked, feature.id, this.selectedTenant).subscribe(res => {
      feature.enabled = event.checked;
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
