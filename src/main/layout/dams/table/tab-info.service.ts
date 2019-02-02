import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { TabInfo } from './tab-info';
import { DOCUMENT } from '@angular/platform-browser';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class TabInfoService {

  tabInfo : TabInfo;
  private heroesUrl = '/code/test3';  // URL to web api

  constructor(private http: HttpClient
             ,@Inject(DOCUMENT) private document: any) { }

  selectMetaTabInfoList(tabInfo: TabInfo): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/dams/table/selectMetaTabInfoList', tabInfo, httpOptions);
  }

  selectCmpTabInfoList(tabInfo: TabInfo): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/dams/table/selectCmpTabInfoList', tabInfo, httpOptions);
  }

  saveCmpTabInfoList(tabInfo: TabInfo): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/dams/table/saveCmpTabInfoList', tabInfo, httpOptions);
  }

  deleteTabInfo(tabInfo: TabInfo): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/dams/table/deleteTabInfo', tabInfo, httpOptions);
  }

  selectTabInfoList(tabInfo: TabInfo): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/dams/table/selectTabInfoList', tabInfo, httpOptions);
  }

  selectTabList(tabInfo: TabInfo): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/dams/table/selectTabList', tabInfo, httpOptions);
  }

  selectColList(tabInfo: TabInfo): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/dams/table/selectColList', tabInfo, httpOptions);
  }

}
