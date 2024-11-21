DROP DATABASE IF EXISTS CarDealershipDatabase;

CREATE DATABASE IF NOT EXISTS CarDealershipDatabase;

USE CarDealershipDatabase;

-- Create tables

-- dealership
CREATE TABLE `Dealership` (
	dealership_id INTEGER NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50),
    address VARCHAR(50),
    phone VARCHAR(12),
    
    PRIMARY KEY (dealership_id)
);

-- vehicles
CREATE TABLE `Vehicles` (
	VIN VARCHAR(5) NOT NULL,
    make VARCHAR(10),
    model VARCHAR(10),
    color VARCHAR(8),
    sold BOOLEAN,
    
    PRIMARY KEY (VIN)
);

-- inventory
CREATE TABLE `Inventory` (
	dealership_id INTEGER,
    VIN VARCHAR(5),
    
    FOREIGN KEY (dealership_id) REFERENCES Dealership (dealership_id),
    FOREIGN KEY(VIN) REFERENCES Vehicles (VIN)
);

-- sales_contracts
-- DROP TABLE `Sales_Contracts`;
CREATE TABLE `Sales_Contracts` (
	contract_id INTEGER NOT NULL AUTO_INCREMENT,
    VIN VARCHAR(5),
    sales_date DATE,
    sales_price DECIMAL(9,2),
    contract_duration INTEGER,
    monthly_payment DECIMAL(6,2),
    
    PRIMARY KEY (contract_id),
    FOREIGN KEY(VIN) REFERENCES Vehicles (VIN)
);

-- lease contracts
-- DROP TABLE `Lease_Contracts`;
CREATE TABLE `Lease_Contracts` (
	lease_id INTEGER NOT NULL AUTO_INCREMENT,
    VIN VARCHAR(5),
    sales_date DATE,
    lease_duration INTEGER,
    monthly_payment DECIMAL(6,2),
    
	PRIMARY KEY (lease_id),
    FOREIGN KEY(VIN) REFERENCES Vehicles (VIN)
);

-- populationg sample data
-- Populate Dealership
INSERT INTO `Dealership` (`name`, `address`, `phone`) VALUES
('Sunrise Motors', '123 Main St', '555-123-4567'),
('Prestige Auto', '456 Elm St', '555-987-6543'),
('Highway Auto', '789 Oak St', '555-654-3210');

-- Populate Vehicles
INSERT INTO `Vehicles` (VIN, make, model, color, sold) VALUES
('V001', 'Toyota', 'Camry', 'Red', TRUE),
('V002', 'Honda', 'Civic', 'Blue', TRUE),
('V003', 'Ford', 'Fusion', 'Black', FALSE),
('V004', 'Chevy', 'Impala', 'White', TRUE),
('V005', 'Nissan', 'Altima', 'Silver', FALSE),
('V006', 'Hyundai', 'Elantra', 'Gray', FALSE),
('V007', 'BMW', 'X3', 'Blue', TRUE),
('V008', 'Mercedes', 'C300', 'Black', TRUE),
('V009', 'Audi', 'A4', 'Red', FALSE),
('V010', 'Kia', 'Sorento', 'White', TRUE),
('V011', 'Mazda', 'CX-5', 'Blue', TRUE),
('V012', 'Jeep', 'Cherokee', 'Green', FALSE),
('V013', 'Subaru', 'Forester', 'Silver', TRUE),
('V014', 'Tesla', 'Model 3', 'Black', TRUE),
('V015', 'Volkswagen', 'Jetta', 'Red', FALSE);

-- Populate Inventory
INSERT INTO `Inventory` (dealership_id, VIN) VALUES
(1, 'V001'),
(1, 'V002'),
(2, 'V003'),
(2, 'V004'),
(3, 'V005'),
(1, 'V006'),
(1, 'V007'),
(2, 'V008'),
(2, 'V009'),
(3, 'V010'),
(3, 'V011'),
(1, 'V012'),
(2, 'V013'),
(3, 'V014'),
(3, 'V015');

-- Populate Sales_Contracts
INSERT INTO `Sales_Contracts` (VIN, sales_date, sales_price, contract_duration, monthly_payment) VALUES
('V001', '2024-01-15', 25000, 60, 416.67),
('V002', '2024-02-10', 22000, 48, 458.33),
('V004', '2024-03-05', 27000, 72, 375.00),
('V007', '2024-03-25', 45000, 60, 750.00),
('V008', '2024-04-01', 50000, 48, 1041.67),
('V010', '2024-04-15', 30000, 60, 500.00),
('V011', '2024-05-05', 29000, 48, 604.17),
('V013', '2024-05-20', 32000, 60, 533.33),
('V014', '2024-06-01', 40000, 72, 555.56),
('V006', '2024-06-15', 20000, 36, 555.56),
('V009', '2024-06-30', 34000, 60, 566.67),
('V005', '2024-07-10', 25000, 48, 520.83),
('V003', '2024-07-20', 24000, 72, 333.33),
('V012', '2024-08-05', 21000, 36, 583.33),
('V015', '2024-08-15', 22000, 60, 366.67);

-- Populate Lease_Contracts
INSERT INTO `Lease_Contracts` (VIN, sales_date, lease_duration, monthly_payment) VALUES
('V003', '2024-01-20', 36, 299.99),
('V005', '2024-02-10', 24, 350.00),
('V006', '2024-03-01', 36, 310.00),
('V008', '2024-03-15', 48, 400.00),
('V009', '2024-04-01', 24, 450.00),
('V010', '2024-04-10', 36, 375.00),
('V011', '2024-04-20', 48, 325.00),
('V013', '2024-05-01', 36, 290.00),
('V014', '2024-05-15', 60, 500.00),
('V015', '2024-06-01', 24, 280.00),
('V012', '2024-06-15', 36, 275.00),
('V004', '2024-07-01', 48, 390.00),
('V002', '2024-07-10', 24, 350.00),
('V001', '2024-07-20', 36, 410.00),
('V007', '2024-08-01', 60, 450.00);
