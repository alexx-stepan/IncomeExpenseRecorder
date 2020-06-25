import { Injectable } from '@angular/core';
import { Account } from './Account';
import {Observable, of} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AccountServiceService {

  private accountsUrl = 'api/account/list';
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) { }

  getAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>(this.accountsUrl)
      .pipe(
        catchError(this.handleError<Account[]>('getAccounts', []))
    );
  }

  getAccount(id: number): Observable<Account> {
    return this.http.get<Account>(`api/account/${id}`)
      .pipe(
        catchError(this.handleError<Account>(`getAccount id=${id}`))
    );
  }

  createAccount(account: Account): Observable<Account> {
    return this.http.post('api/account', account, this.httpOptions)
      .pipe(
        catchError(this.handleError<any>('createAccount'))
      );
  }

  updateAccount(account: Account): Observable<Account> {
    return this.http.put('api/account', account, this.httpOptions)
      .pipe(
        catchError(this.handleError<any>('updateAccount'))
      );
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.log('Error:');
      console.log(error);

      return of(result as T);
    }
  }
}
