CREATE TABLE usuarios (
    id UUID ,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL PRIMARY KEY,
    password VARCHAR(255),
    create_date TIMESTAMP,
    modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login DATE,
    token VARCHAR(255),
    is_active INT,
    number INT,
    citycode INT,
    contrycode INT
);