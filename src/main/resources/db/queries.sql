USE currency_converter;

SELECT * FROM CURRENCY;

select *
from (SELECT *
      FROM CURRENCY
      WHERE abbreviation = 'EUR') `C*`;

SELECT COUNT(*) AS currency_count
FROM CURRENCY;

SELECT * FROM CURRENCY
ORDER BY rate_to_usd DESC
LIMIT 1;