import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FeatureToggleService {

  constructor(private http: HttpClient) { }


  public getAllTenents() {
    return this.http.get(environment.baseUrl + '/tenants');
  }

  public getFeaturesForTenant(tenant: string, phase: string) {
    const requestParams: HttpParams = new HttpParams()
    .set('tenant', tenant)
    .set('phase', phase);
    return this.http.get(`${environment.baseUrl}/features`, {
      params: requestParams
    });
  }

  updateFeatureStatus(checked: any, name: any, tenant: string) {
    return this.http.post(`${environment.baseUrl}/features/${name}/${checked}?tenant=${tenant}`, null);
  }

}
