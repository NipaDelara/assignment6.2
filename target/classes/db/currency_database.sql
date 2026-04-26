DROP DATABASE IF EXISTS currency_converter;
CREATE DATABASE currency_converter;
USE currency_converter;

CREATE TABLE CURRENCY (
            id INT NOT NULL AUTO_INCREMENT,
            abbreviation VARCHAR(10) NOT NULL UNIQUE,
            name VARCHAR(50) NOT NULL,
            rate_to_usd DECIMAL(10, 4) NOT NULL,
            PRIMARY KEY (id)
);

INSERT INTO CURRENCY (abbreviation, name, rate_to_usd) VALUES
                        ('USD', 'United States Dollar', 1.0000),
                        ('EUR', 'Euro', 1.1712),
                        ('GBP', 'British Pound', 1.3490),
                        ('SEK', 'Swedish Krona', 0.1060),
                        ('BDT', 'Bangladeshi Take', 0.0082),
                        ('INR', 'Indian Rupee', 0.0120),
                        ('JPY', 'Japanese Yen', 0.0069),
                        ('CAD', 'Canadian Dollar', 0.7290);

DROP USER IF EXISTS 'apps'@'localhost';
CREATE USER 'apps'@'localhost' IDENTIFIED BY 'password';

GRANT SELECT ON currency_converter.* TO 'apps'@'localhost';

FLUSH PRIVILEGES;