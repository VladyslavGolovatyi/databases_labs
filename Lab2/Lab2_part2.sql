USE golovatyi;

#show all airports in specific country
SELECT * FROM airports WHERE country='USA';

#show all planes
SELECT planes.id, planes.max_passengers, planes.max_luggage_weight, airlines.name AS airline_name FROM planes 
INNER JOIN airlines ON planes.airline_id=airlines.id;

#show top 5 planes by max number of passengers
SELECT planes.id, planes.max_passengers, planes.max_luggage_weight, airlines.name AS airline_name FROM planes 
INNER JOIN airlines ON planes.airline_id=airlines.id ORDER BY planes.max_passengers DESC LIMIT 5;

#show base airports for airlines
SELECT airlines.name, airports.name FROM base_airports 
INNER JOIN airlines ON base_airports.airline_id=airlines.id
INNER JOIN airports ON base_airports.airport_id=airports.id;

#show all tickets bought in last month 
SELECT * FROM bought_tickets WHERE purchase_time>DATE_ADD(NOW(), INTERVAL -1 MONTH);

#show all users
SELECT * FROM users;

#show airlines sorted by foundation year
SELECT * FROM airlines ORDER BY foundation_year;

#show airlines and average number of passengers in its planes
SELECT airlines.name, AVG(planes.max_passengers) AS avg_max_passengers FROM planes 
INNER JOIN airlines ON planes.airline_id=airlines.id GROUP BY name; 

#show number of planes of each airline
SELECT airlines.name, COUNT(*) AS number_of_planes FROM planes 
INNER JOIN airlines ON planes.airline_id=airlines.id GROUP BY name; 

#show flights from specific airport in specific day
SELECT * FROM flights WHERE src_airport_id='1' AND CAST(departure_time AS DATE) = '2022-09-13';
