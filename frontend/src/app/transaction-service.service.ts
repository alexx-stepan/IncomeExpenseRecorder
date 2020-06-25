import { Injectable } from '@angular/core';
import { Transaction } from './Transaction';
import { TransactionListResponse } from './transaction-list-response';
import {Observable, of} from "rxjs";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class TransactionServiceService {

  private transactionsUrl = 'api/transaction/list';
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) { }

  getTransactions(pageNumber: number, pageSize: number): Observable<TransactionListResponse> {
    let params = new HttpParams().set('pageNumber', pageNumber.toString()).set("pageSize", pageSize.toString());
    return this.http.get<TransactionListResponse>(this.transactionsUrl, {params: params})
      .pipe(
        catchError(this.handleError<TransactionListResponse>('getTransactions', new TransactionListResponse()))
    );
  }

  getTransaction(id: number): Observable<Transaction> {
    return this.http.get<Transaction>(`api/transaction/${id}`)
      .pipe(
        catchError(this.handleError<Transaction>(`getTransaction id=${id}`))
    );
  }

  createTransaction(transaction: Transaction): Observable<Transaction> {
    transaction.bookingDate = new Date();
    return this.http.post('api/transaction', transaction, this.httpOptions)
      .pipe(
        catchError(this.handleError<any>('createTransaction'))
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
