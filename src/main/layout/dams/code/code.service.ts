import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Code } from './code';
import { DOCUMENT } from '@angular/platform-browser';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class CodeService {

  code : Code;
  private heroesUrl = '/code/test3';  // URL to web api

  constructor(private http: HttpClient
             ,@Inject(DOCUMENT) private document: any) { }

  saveUsrCodes (fd: FormData): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/code/saveUsrCodes', fd);
  }

  selectCodeList(code: Code): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/dams/code/selectCodeList', code, httpOptions);
  }
 

  downloadExcel (fd: FormData): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/comm/excel/downloadExcel', fd);
  }

}
