import { DataSource } from '@angular/cdk/table';
import { Transaction } from './Transaction';
import { TransactionListResponse } from './transaction-list-response';
import { CollectionViewer } from '@angular/cdk/collections';
import { Observable, BehaviorSubject, of } from "rxjs";
import { TransactionServiceService } from './transaction-service.service';
import { catchError, finalize } from "rxjs/operators";

export class TransactionDataSource implements DataSource<Transaction>{

  private transactionSubject = new BehaviorSubject<Transaction[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);
  private countSubject = new BehaviorSubject<number>(0);
  public counter$ = this.countSubject.asObservable();

  constructor(private transactionService: TransactionServiceService) { }

  connect(collectionViewer: CollectionViewer): Observable<Transaction[]> {
    return this.transactionSubject.asObservable();
  }

  disconnect(collectionViewer: CollectionViewer): void {
    this.transactionSubject.complete();
    this.loadingSubject.complete();
    this.countSubject.complete();
  }

  loadTransactions(pageNumber = 0, pageSize = 5): void {
    this.loadingSubject.next(true);
    this.transactionService.getTransactions(pageNumber, pageSize)
      .pipe(catchError(() => of([])), finalize(() => this.loadingSubject.next(false)))
      .subscribe((result: TransactionListResponse) => {
        this.transactionSubject.next(result.transactionsOnPage);
        this.countSubject.next(result.transactionTotalNumber);
      });
  }
}
