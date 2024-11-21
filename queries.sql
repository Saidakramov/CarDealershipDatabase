
SELECT * FROM Inventory;
SELECT * FROM Vehicles;
SELECT * FROM Sales_Contracts;
SELECT * FROM Lease_Contracts;

-- get all dealerships
SELECT * FROM Dealership;

-- find all vehicles for a specific dealership
SELECT 
	v.*,
    d.name
FROM Vehicles v
JOIN Inventory i ON v.VIN = i.VIN
JOIN Dealership d ON i.dealership_id = d.dealership_id;

-- find a car by VIN
SELECT *
FROM Vehicles
WHERE VIN = 'V009';

-- find a dealership where a certain car is located by VIN
SELECT 
	d.*
FROM Dealership d
JOIN Inventory i ON d.dealership_id = i.dealership_id
JOIN Vehicles v ON i.VIN = v.VIN
WHERE v.VIN = 'V009';

-- find all Deakership that have a certain car type
SELECT 
	d.*
FROM Dealership d
JOIN Inventory i ON d.dealership_id = i.dealership_id
JOIN Vehicles v ON i.VIN = v.VIN
WHERE color = 'Black';

-- get all sales info for specific dealer for specific date range
SELECT
	s.*
FROM Sales_Contracts s
JOIN Inventory i ON s.VIN = i.VIN
JOIN Dealership d ON d.dealership_id = i.dealership_id
WHERE `name` LIKE 'Prestige%'
	AND sales_date BETWEEN '2024-01-01' AND '2024-05-30';