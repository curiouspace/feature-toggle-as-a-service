import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FeatureToggleService {

  constructor(private http: HttpClient) { }


  public getAllTenents() {
    return this.http.get(environment.baseUrl + '/features/tenants');
  }

  public getFeaturesForTenant(tenant) {
    return this.http.get(`${environment.baseUrl}/features?tenant=${tenant}`);
  }

  updateFeatureStatus(checked: any, name: any, tenant: string) {
    return this.http.post(`${environment.baseUrl}/features/${name}/${checked}?tenant=${tenant}`, null);
  }

}
