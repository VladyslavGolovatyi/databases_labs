USE labor_sql;

SELECT model, speed, hd, cd, price FROM pc WHERE (cd='12x' OR cd='24x') AND price<600 ORDER BY speed DESC;

SELECT * FROM trip WHERE HOUR(time_in) BETWEEN 17 AND 23;

SELECT DISTINCT trip.plane, company.name FROM trip INNER JOIN company ON trip.ID_comp=company.ID_comp WHERE plane='Boeing';

SELECT COUNT(*) FROM product WHERE type='PC' AND maker='A';

SELECT DISTINCT product.maker FROM product INNER JOIN (SELECT maker, type FROM product WHERE type='Printer') AS printer_companies 
ON product.maker=printer_companies.maker WHERE product.type='Laptop';

#ALTER TABLE pass_in_trip 
#ADD COLUMN place_row VARCHAR(5) COMMENT 'Row(1,2,...)',
#ADD COLUMN place_pos VARCHAR(5) COMMENT 'Place in the row starting from left(a,b,...)';
#UPDATE pass_in_trip SET place_row=SUBSTR(place,1,1);
#UPDATE pass_in_trip SET place_pos=SUBSTR(place,2,1);
#ALTER TABLE pass_in_trip DROP COLUMN place;
SELECT * FROM pass_in_trip;

SELECT DATE(time_in) AS date, COUNT(*) AS flights FROM trip WHERE town_to='Moscow' GROUP BY DATE(time_in) ORDER BY date DESC;

SELECT product.maker, MIN(pc.price) FROM product INNER JOIN pc ON product.model=pc.model WHERE product.type='pc' GROUP BY maker;

SELECT trip.trip_no, company.name as company_name, trip.plane, trip.town_from, trip.town_to, 
CASE 
WHEN TIMEDIFF(trip.time_in,trip.time_out)>=0 THEN TIMEDIFF(trip.time_in,trip.time_out) 
WHEN TIMEDIFF(trip.time_in,trip.time_out)<0 THEN TIMEDIFF(trip.time_out,trip.time_in) 
END 
AS flight_time FROM trip
INNER JOIN company ON trip.ID_Comp=company.ID_Comp;

SELECT product.type, devices.model, MIN(devices.price) as lowest_price FROM product INNER JOIN (
SELECT model, price FROM pc
UNION
SELECT model, price FROM laptop 
UNION
SELECT model, price FROM printer
ORDER BY model) AS devices ON product.model=devices.model GROUP BY devices.model