import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Tran } from './tran';
import { DOCUMENT } from '@angular/platform-browser';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class TranService {

  tran : Tran;
  private heroesUrl = '/tran/test3';  // URL to web api

  constructor(private http: HttpClient
             ,@Inject(DOCUMENT) private document: any) { }


  delimeterToRows(tran: Tran): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/tran/delimeterToRows', tran, httpOptions);
  }


}
