import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccountsComponent } from './accounts/accounts.component';
import { TransactionsComponent } from './transactions/transactions.component';


const routes: Routes = [
  {path: '', redirectTo: '/accounts', pathMatch: 'full'},
  {path: 'accounts', component: AccountsComponent},
  {path: 'transactions', component: TransactionsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
