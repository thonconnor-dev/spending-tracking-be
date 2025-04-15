

CREATE TABLE transaction (
    id varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    category_id varchar(255) NOT NULL,
    amount decimal(19,2) NOT NULL,
    type varchar(255) NOT NULL,
    created_date datetime(6) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE category (
    id varchar(255) NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE cashflow (
    id varchar(255) NOT NULL,
    cashflow_month INTEGER NOT NULL,
    cashflow_year INTEGER NOT NULL,
    expense_amount decimal(19,2) NOT NULL,
    income_amount decimal(19,2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE cashflow_transaction (
    cashflow_id varchar(255) NOT NULL,
    transaction_id varchar(255) NOT NULL,
    PRIMARY KEY (cashflow_id, transaction_id)
);

CREATE TABLE balance_history (
    id varchar(255) NOT NULL,
    balance decimal(19,2) NOT NULL,
    created_date datetime(6) NOT NULL,
    transaction_id varchar(255),
    PRIMARY KEY (id)
);

