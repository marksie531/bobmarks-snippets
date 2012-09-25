CREATE TABLE PRODUCTS (PROD_ID BIGINT NOT NULL,
                       PROD_NAME VARCHAR(50),
                       PROD_DESCRIPTION VARCHAR(255),
                       PROD_PRICE DECIMAL (10,2),
                       PRIMARY KEY (PROD_ID));

INSERT INTO PRODUCTS (PROD_ID, PROD_NAME, PROD_DESCRIPTION, PROD_PRICE)
              VALUES (1, 'Computer', 'Dell Computer', 299.95);