

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

CREATE TABLE expense (
    id varchar(255) NOT NULL,
    transaction_id varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE income (
    id varchar(255) NOT NULL,
    transaction_id varchar(255) NOT NULL,
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

INSERT INTO category (id, name) VALUES ('1', 'Income');
INSERT INTO category (id, name) VALUES ('2', 'Entertainment');
INSERT INTO category (id, name) VALUES ('3', 'Utilities');
INSERT INTO category (id, name) VALUES ('4', 'Groceries');
INSERT INTO category (id, name) VALUES ('5', 'Clothing');
INSERT INTO category (id, name) VALUES ('6', 'Health');
INSERT INTO category (id, name) VALUES ('7', 'Travel');
INSERT INTO category (id, name) VALUES ('8', 'Other');

INSERT INTO cashflow (id, cashflow_month, cashflow_year, expense_amount, income_amount) VALUES ('1', 1, 2024, 0, 0);
INSERT INTO cashflow (id, cashflow_month, cashflow_year, expense_amount, income_amount) VALUES ('2', 2, 2024, 0, 0);
INSERT INTO cashflow (id, cashflow_month, cashflow_year, expense_amount, income_amount) VALUES ('3', 3, 2024, 0, 0);
INSERT INTO cashflow (id, cashflow_month, cashflow_year, expense_amount, income_amount) VALUES ('4', 4, 2024, 0, 0);
INSERT INTO cashflow (id, cashflow_month, cashflow_year, expense_amount, income_amount) VALUES ('5', 5, 2024, 0, 0);
INSERT INTO cashflow (id, cashflow_month, cashflow_year, expense_amount, income_amount) VALUES ('6', 6, 2024, 0, 0);
INSERT INTO cashflow (id, cashflow_month, cashflow_year, expense_amount, income_amount) VALUES ('7', 7, 2024, 0, 0);
INSERT INTO cashflow (id, cashflow_month, cashflow_year, expense_amount, income_amount) VALUES ('8', 8, 2024, 0, 0);
INSERT INTO cashflow (id, cashflow_month, cashflow_year, expense_amount, income_amount) VALUES ('9', 9, 2024, 0, 0);
INSERT INTO cashflow (id, cashflow_month, cashflow_year, expense_amount, income_amount) VALUES ('10', 10, 2024, 0, 0);
INSERT INTO cashflow (id, cashflow_month, cashflow_year, expense_amount, income_amount) VALUES ('11', 11, 2024, 0, 0);
INSERT INTO cashflow (id, cashflow_month, cashflow_year, expense_amount, income_amount) VALUES ('12', 12, 2024, 0, 0);

