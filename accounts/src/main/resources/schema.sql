CREATE TABLE IF NOT EXISTS customers (
    customer_id BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    mobile_number VARCHAR(20) UNIQUE NOT NULL,
    created_at DATE NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATE DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS accounts (
    account_number BIGINT PRIMARY KEY NOT NULL,
    customer_id INT8 NOT NULL,
    account_type VARCHAR(100) NOT NULL,
    branch_address VARCHAR(200) NOT NULL,
    created_at DATE NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATE DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);