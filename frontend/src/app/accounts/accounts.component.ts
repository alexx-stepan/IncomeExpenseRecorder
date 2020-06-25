import { Component, OnInit } from '@angular/core';
import { Account } from '../Account';
import { AccountServiceService } from '../account-service.service';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.scss']
})
export class AccountsComponent implements OnInit {

  accounts: Account[];
  acc: Account;

  constructor(private accountService: AccountServiceService) { }

  ngOnInit(): void {
    this.acc = new Account();
    this.getAccounts();
  }

  getAccounts(): void {
    this.accountService.getAccounts().subscribe(accounts => this.accounts = accounts);
  }

  createAccount(): void {
    this.accountService.createAccount(this.acc).subscribe(() => this.getAccounts());
    this.acc = new Account();
  }

  saveAccount(): void {
    this.accountService.updateAccount(this.acc).subscribe(() => this.getAccounts());
    this.acc = new Account();
  }

  editAccount(account: Account): void {
    this.acc = account;
  }

  cancelEditing(): void {
    this.acc = new Account();
  }

}
