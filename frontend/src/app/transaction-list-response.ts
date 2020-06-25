import { Transaction } from './Transaction';

export class TransactionListResponse {
  transactionsOnPage: Transaction[];
  transactionTotalNumber: number;
}
