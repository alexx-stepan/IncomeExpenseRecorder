CREATE SCHEMA recorder;

CREATE TABLE recorder.ACCOUNTS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_number INT AUTO_INCREMENT,
    owner_name VARCHAR(20) NOT NULL,
    currency_code VARCHAR(3) NOT NULL,
    balance INT NOT NULL
);

CREATE TABLE recorder.TRANSACTIONS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT NOT NULL REFERENCES recorder.ACCOUNTS(id),
    category_name VARCHAR(20) NOT NULL,
    booking_date TIMESTAMP NOT NULL,
    amount INT NOT NULL
);
