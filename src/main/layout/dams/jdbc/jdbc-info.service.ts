import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { JdbcInfo } from './jdbc-info';
import { TabInfo } from '../table/tab-info';
import { DOCUMENT } from '@angular/platform-browser';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class JdbcInfoService {

  jdbcInfo : JdbcInfo;
  private heroesUrl = '/jdbcInfo/test3';  // URL to web api

  constructor(private http: HttpClient
             ,@Inject(DOCUMENT) private document: any) { }

  saveJdbcInfo (jdbcInfo: JdbcInfo): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/dams/jdbc/saveJdbcInfo', jdbcInfo, httpOptions);
  }

  selectJdbcInfoList(jdbcInfo: JdbcInfo): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/dams/jdbc/selectJdbcInfoList', jdbcInfo, httpOptions);
  }

  selectComboJdbcList(jdbcInfo: JdbcInfo): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/dams/jdbc/selectComboJdbcList', jdbcInfo, httpOptions);
  }

  deleteJdbcInfo(jdbcInfo: JdbcInfo): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/dams/jdbc/deleteJdbcInfo', jdbcInfo, httpOptions);
  }

  testJdbcInfo(tabInfo: TabInfo): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/dams/table/testJdbcInfo', tabInfo, httpOptions);
  }

}
