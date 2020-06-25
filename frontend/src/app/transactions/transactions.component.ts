import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { Transaction } from '../Transaction';
import { TransactionServiceService } from '../transaction-service.service';
import { AccountServiceService } from '../account-service.service';
import { Account } from '../Account';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import { TransactionDataSource } from '../transaction.datasource';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.scss']
})
export class TransactionsComponent implements OnInit {

  @Input() accountId: number;

  accounts: Account[];
  transaction: Transaction;
//   transactions: Transaction[];

  displayedColumns: string[] = ['id', 'account', 'category', 'date', 'amount'];
  dataSource: TransactionDataSource;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private transactionService: TransactionServiceService,
              private accountService: AccountServiceService) { }

  ngOnInit(): void {
    this.transaction = new Transaction();
    this.getAccounts();
    this.dataSource = new TransactionDataSource(this.transactionService);
    this.dataSource.loadTransactions();
  }

  ngAfterViewInit() {
    this.dataSource.counter$
      .pipe(
        tap((count: number) => {
          this.paginator.length = count;
        })
      )
      .subscribe();

    this.paginator.page
      .pipe(
        tap(() => this.loadTransactions())
      )
      .subscribe();
  }

  loadTransactions() {
    this.dataSource.loadTransactions(this.paginator.pageIndex, this.paginator.pageSize);
  }

  getAccounts(): void {
    this.accountService.getAccounts().subscribe(accounts => this.accounts = accounts);
  }

  createTransaction(): void {
    this.transactionService.createTransaction(this.transaction).subscribe(() => this.dataSource.loadTransactions());
    this.transaction = new Transaction();
  }

  cancelEditing(): void {
    this.transaction = new Transaction();
  }
}
