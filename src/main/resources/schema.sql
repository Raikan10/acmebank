CREATE TABLE IF NOT EXISTS ACCOUNT(
    id INTEGER NOT NULL,
    currency VARCHAR(255) NOT NULL,
    amount decimal(15,2),
    PRIMARY KEY(id)
);
