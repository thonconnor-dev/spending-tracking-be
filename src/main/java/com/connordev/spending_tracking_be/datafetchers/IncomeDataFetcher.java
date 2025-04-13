package com.connordev.spending_tracking_be.datafetchers;

import java.util.List;
import com.connordev.spending_tracking_be.codegen.types.Transaction;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import graphql.schema.DataFetchingEnvironment;

@DgsComponent
public class IncomeDataFetcher {
    @DgsData(
      parentType = "Query",
      field = "incomes"
  )
  public List<Transaction> getIncomes(DataFetchingEnvironment dataFetchingEnvironment) {
    return List.of(Transaction.newBuilder()
        .id("1")
        .amount(100)
        .build());
  }

  @DgsData(
      parentType = "Query",
      field = "income"
  )
  public Transaction getIncome(DataFetchingEnvironment dataFetchingEnvironment) {
    return Transaction.newBuilder().build();
  }
}
