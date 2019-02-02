import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Gens } from './gens';
import { DOCUMENT } from '@angular/platform-browser';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class GensService {

  constructor(private http: HttpClient
             ,@Inject(DOCUMENT) private document: any) { }

  cloneAngular(gens: Gens): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/gens/pgm/cloneAngular', gens, httpOptions);
  }

  cloneSpring(gens: Gens): Observable<any> {
    return this.http.post<any>('http://'+this.document.location.hostname+':9201/gens/pgm/cloneSpring', gens, httpOptions);
  }
  
}
