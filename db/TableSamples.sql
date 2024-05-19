/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  William Sinclair
 * Created: 10 May 2024
 */

-- Sample data for Users table
INSERT INTO Users (fName, lName, phoneNo, email, password, address, city, state, postCode, activation, registrationDate, position) VALUES
( 'John', 'Doe', 123456789, 'john@example.com', 'password123', '123 Main St', 'New York', 'NY', '1001', TRUE, '2024-04-01', 'Salesperson'),
( 'Jane', 'Smith', 987654321, 'jane@example.com', 'password456', '456 Elm St', 'Los Angeles', 'CA', '9001', TRUE, '2024-04-02', 'Salesperson'),
( 'Alice', 'Johnson', 555123456, 'alice@example.com', 'password789', '789 Oak St', 'Chicago', 'IL', '6001', TRUE, '2024-04-03', 'Salesperson'),
( 'Bob', 'Williams', 999888777, 'bob@example.com', 'passwordabc', '987 Pine St', 'Houston', 'TX', '7701', TRUE, '2024-04-04', 'Salesperson'),
( 'Admin', 'Admin', 111222333, 'admin@example.com', 'adminpassword', '456 Admin St', 'Admin City', 'AC', '1234', TRUE, '2024-04-05', 'Systems Admin'),
( 'Supplier1', 'Supplier', 444555666, 'supplier1@example.com', 'supplierpassword1', '789 Supplier St', 'Supplier City', 'SC', '5678', TRUE, '2024-04-06', 'Systems Admin'),
( 'Supplier2', 'Supplier', 777888999, 'supplier2@example.com', 'supplierpassword2', '321 Supplier St', 'Supplier Town', 'ST', '9012', TRUE, '2024-04-07', 'Manager'),
( 'Manager1', 'Manager', 222333444, 'manager1@example.com', 'managerpassword1', '654 Manager St', 'Manager City', 'MC', '3456', TRUE, '2024-04-08', 'Customer'),
( 'Manager2', 'Manager', 666777888, 'manager2@example.com', 'managerpassword2', '987 Manager St', 'Manager Town', 'MT', '7890', TRUE, '2024-04-09', 'Customer'),
( 'Customer1', 'Customer', 333444555, 'customer1@example.com', 'customerpassword1', '123 Customer St', 'Customer City', 'CC', '6789', TRUE, '2024-04-10', 'Customer'),
( 'Customer2', 'Customer', 888999000, 'customer2@example.com', 'customerpassword2', '456 Customer St', 'Customer Town', 'CT', '0123', TRUE, '2024-04-11', 'Salesperson'),
( 'Customer3', 'Customer', 555666777, 'customer3@example.com', 'customerpassword3', '789 Customer St', 'Customer Village', 'CV', '4567', TRUE, '2024-04-12', 'Salesperson'),
( 'Customer4', 'Customer', 000111222, 'customer4@example.com', 'customerpassword4', '321 Customer St', 'Customer Hamlet', 'CH', '8901', TRUE, '2024-04-13', 'Salesperson'),
( 'Customer5', 'Customer', 222333444, 'customer5@example.com', 'customerpassword5', '654 Customer St', 'Customer County', 'CC', '2345', TRUE, '2024-04-14', 'Salesperson'),
( 'Customer6', 'Customer', 999000111, 'customer6@example.com', 'customerpassword6', '987 Customer St', 'Customer State', 'CS', '6789', TRUE, '2024-04-15', 'Salesperson');

-- Sample data for Customer table
INSERT INTO Users (fName, lName, phoneNo, email, password, address, city, state, postCode, activation, registrationDate, position) VALUES
( 'Lily', 'Smith', 874205619, 'LilySmith@email.com', 'password1', '34 Duke St', 'Fern', 'CO', '2725', TRUE, '2024-03-02', 'Company'),
( 'John', 'Kelly', 328716495, 'JohnKelly@email.com', 'password2', '235 Brook St', 'Redding', 'NY', '8628', TRUE, '2024-05-01', 'Individual'),
( 'Olivia', 'Jones', 506197324, 'OliviaJones@email.com', 'password3', '2 Green St', 'Millville', 'CA', '7153', TRUE, '2024-03-04', 'Company'),
( 'Tom', 'Anderson', 932460175, 'TomAnderson@email.com', 'password4', '554 Park St', 'Miranda', 'FL', '6172', TRUE, '2024-02-02', 'Individual'),
( 'Miller', 'Johnson', 615893207, 'MillerJohnson@email.com', 'password5', '43 George St', 'Lakeport', 'CT', '1625', TRUE, '2024-01-03', 'Company'),
( 'Mark', 'Moore', 749312508, 'MarkMore@email.com', 'password6', '989 Kendal St', 'Middletown', 'HI', '2618', TRUE, '2024-03-05', 'Individual'),
( 'Daniel', 'Clark', 201587643, 'DanielClark@email.com', 'password7', '34 Brown St', 'Windsor', 'IA', '8362', TRUE, '2024-04-05', 'Individual'),
( 'Stacy', 'Lane', 485932670, 'StacyLane@email.com', 'password8', '59 Archer St', 'Hopland', 'NY', '7352', TRUE, '2024-02-01', 'Company'),
( 'Jess', 'Jackson', 573821049, 'JessJackson@email.com', 'password9', '5 Dawson St', 'Oakland', 'DE', '8161', TRUE, '2024-01-04', 'Individual'),
( 'Sarah', 'Hall', 946720358, 'SarahHall@email.com', 'password10', '90 Finstock St', 'Watsonville', 'LA', '3527', TRUE, '2024-04-03', 'Company'),
( 'Royce', 'Allen', 362810947, 'RoyceAllen@email.com', 'password11', '283 Sunbeam St', 'Newman', 'AK', '8726', TRUE, '2024-02-03', 'Individual'),
( 'James', 'Young', 518734602, 'JamesYoung@email.com', 'password12', '621 Dunmore St', 'Livingston', 'IL', '9817', TRUE, '2024-04-02', 'Individual'),
( 'Michael', 'Walker', 894167235, 'MichaelWalker@email.com', 'password13', '563 Hopefield St', 'Weldon', 'CO', '6152', TRUE, '2024-01-02', 'Company'),
( 'Jennifer', 'Lewis', 725134986, 'JenniferLewis@email.com', 'password14', '421 Maygrove St', 'Inglewood', 'AZ', '2345', TRUE, '2024-01-01', 'Individual'),
( 'Scott', 'Adams', 631857492, 'ScottAdams@email.com', 'password15', '980 Dartmouth St', 'Ontario', 'GA', '9725', TRUE, '2024-03-02', 'Individual');
( 'Steve', 'Charles', 470298156, 'SteveCharles@email.com', 'password16', '123 Lowfield St', 'Anaheim', 'FL', '7514', TRUE, '2024-02-03', 'Company'),
( 'Bruce', 'Myers', 862415703, 'BruceMyers@email.com', 'password17', '45 Mora St', 'Irvine', 'CV', '3529', TRUE, '2024-04-04', 'Individual'),
( 'Kim', 'Fisher', 395728146, 'KimFisher@email.com', 'password18', '928 Ivy St', 'Vista', 'CT', '8651', TRUE, '2024-01-02', 'Company'),
( 'Wilson', 'Keely', 143876592, 'WilsonKeely@email.com', 'password19', '48 Clifford St', 'Hayfield', 'FL', '2653', TRUE, '2024-03-04', 'Individual'),
( 'Bailey', 'Baker', 642753975, 'BaileyBaker@email.com', 'password20', '7 Mulgrave St', 'Topock', 'LA', '7652', TRUE, '2024-04-01', 'Company');

-- Sample data for Orders table
INSERT INTO Orders (orderDate, status, totalNoItems, totalPrice, userID) VALUES
('2024-04-01', 'Completed', 5, 150.00, 8),
('2024-04-02', 'Pending', 3, 75.00, 9),
('2024-04-03', 'Processing', 2, 50.00, 8),
('2024-04-04', 'Completed', 4, 100.00, 9),
('2024-04-05', 'Cancelled', 1, 25.00, 8),
('2024-04-06', 'Pending', 2, 60.00, 10),
('2024-04-07', 'Processing', 3, 90.00, 9),
('2024-04-08', 'Completed', 4, 120.00, 8),
('2024-04-09', 'Cancelled', 2, 55.00, 9),
('2024-04-10', 'Completed', 3, 80.00, 10),
('2024-04-11', 'Pending', 4, 100.00, 10),
('2024-04-12', 'Processing', 2, 45.00, 10),
('2024-04-13', 'Completed', 5, 110.00, 10),
('2024-04-14', 'Pending', 2, 70.00, 8),
('2024-04-15', 'Processing', 3, 95.00, 9);

-- Sample data for Shipment table
INSERT INTO Shipment (shipmentID, shipmentDate, carrier, trackingNo) VALUES
(1, '2024-04-02', 'UPS', 123456),
(2, '2024-04-03', 'FedEx', 654321),
(3, '2024-04-04', 'DHL', 987654),
(4, '2024-04-05', 'USPS', 456789),
(5, '2024-04-06', 'UPS', 987123);

-- Sample data for Invoice table
INSERT INTO Invoice (invoiceID, userID, invoiceDate, invoiceDueDate) VALUES
(1, 1, '2024-04-01', '2024-05-01'),
(2, 2, '2024-04-02', '2024-05-02'),
(3, 3, '2024-04-03', '2024-05-03'),
(4, 4, '2024-04-04', '2024-05-04'),
(5, 5, '2024-04-05', '2024-05-05'),
(6, 6, '2024-04-06', '2024-05-06'),
(7, 7, '2024-04-07', '2024-05-07'),
(8, 8, '2024-04-08', '2024-05-08'),
(9, 9, '2024-04-09', '2024-05-09'),
(10, 10, '2024-04-10', '2024-05-10'),
(11, 11, '2024-04-11', '2024-05-11'),
(12, 12, '2024-04-12', '2024-05-12'),
(13, 13, '2024-04-13', '2024-05-13'),
(14, 14, '2024-04-14', '2024-05-14'),
(15, 15, '2024-04-15', '2024-05-15');

-- Sample data for Payment table
INSERT INTO Payment (userID, orderID, paymentDate, paymentAmount) VALUES
(1, 1, '2024-05-13', 33.33);

INSERT INTO PaymentMethod (userID, cardName, cardNo, expiryDate, CVV) VALUES
(1, 'John Doe', '1234 1234 1234 1234', '2024-12-31', 123);

-- Sample data for AccessLog table
INSERT INTO AccessLog (logID, userID, logDate, logDetails) VALUES
(1, 1, '2024-04-01', 'Logged in'),
(2, 2, '2024-04-02', 'Logged in'),
(3, 3, '2024-04-03', 'Logged in'),
(4, 4, '2024-04-04', 'Logged in'),
(5, 5, '2024-04-05', 'Logged in'),
(6, 6, '2024-04-06', 'Logged in'),
(7, 7, '2024-04-07', 'Logged in'),
(8, 8, '2024-04-08', 'Logged in'),
(9, 9, '2024-04-09', 'Logged in'),
(10, 10, '2024-04-10', 'Logged in'),
(11, 11, '2024-04-11', 'Logged in'),
(12, 12, '2024-04-12', 'Logged in'),
(13, 13, '2024-04-13', 'Logged in'),
(14, 14, '2024-04-14', 'Logged in'),
(15, 15, '2024-04-15', 'Logged in');

-- Sample data for Products table
INSERT INTO Products (ProductName, ProductType, UnitPrice, StockLvl) VALUES
('Product1', 'Type1', 10.00, 100),
('Product2', 'Type2', 20.00, 200),
('Product3', 'Type1', 15.00, 150),
('Product4', 'Type3', 25.00, 250),
('Product5', 'Type2', 30.00, 300),
('Product6', 'Type3', 35.00, 350),
('Product7', 'Type1', 40.00, 400),
('Product8', 'Type2', 45.00, 450),
('Product9', 'Type3', 50.00, 500),
('Product10', 'Type1', 55.00, 550),
('Product11', 'Type2', 60.00, 600),
('Product12', 'Type3', 65.00, 650),
('Product13', 'Type1', 70.00, 700),
('Product14', 'Type2', 75.00, 750),
('Product15', 'Type3', 80.00, 800);
('Computer', 'Type2', 60.00, 600),
('Phone', 'Type3', 65.00, 650),
('Laptop', 'Type1', 70.00, 700),
('Alexa', 'Type2', 75.00, 750),
('Apple Watch', 'Type3', 80.00, 800);
('HP Laptop', 'Type2', 60.00, 600),
('iPhone 8', 'Type3', 65.00, 650),
('iPhone X', 'Type1', 70.00, 700),
('Macbook Air', 'Type2', 75.00, 750),
('FitBit', 'Type3', 80.00, 800);

-- Sample data for Role table
INSERT INTO Role (roleID, roleName) VALUES
(1, 'Admin'),
(2, 'Customer'),
(3, 'Supplier'),
(4, 'Manager');

-- Sample data for Payment table
-- INSERT INTO Payment (paymentID, paymentDate, paymentAmt, paymentMethod, cardNo, CVV, expiryDate, cardName) VALUES
-- need to update
