-- Sample data for Role table
INSERT INTO Role (roleID, roleName) VALUES 
(1, 'Admin'),
(2, 'Customer'),
(3, 'Supplier'),
(4, 'Manager');

-- Sample data for Users table
INSERT INTO Users (userID, fName, lName, phoneNo, email, password, address, city, state, postCode, activation, registrationDate, roleID) VALUES
(1, 'John', 'Doe', 123456789, 'john@example.com', 'password123', '123 Main St', 'New York', 'NY', '1001', TRUE, '2024-04-01', 2),
(2, 'Jane', 'Smith', 987654321, 'jane@example.com', 'password456', '456 Elm St', 'Los Angeles', 'CA', '9001', TRUE, '2024-04-02', 2),
(3, 'Alice', 'Johnson', 555123456, 'alice@example.com', 'password789', '789 Oak St', 'Chicago', 'IL', '6001', TRUE, '2024-04-03', 2),
(4, 'Bob', 'Williams', 999888777, 'bob@example.com', 'passwordabc', '987 Pine St', 'Houston', 'TX', '7701', TRUE, '2024-04-04', 2),
(5, 'Admin', 'Admin', 111222333, 'admin@example.com', 'adminpassword', '456 Admin St', 'Admin City', 'AC', '1234', TRUE, '2024-04-05', 1),
(6, 'Supplier1', 'Supplier', 444555666, 'supplier1@example.com', 'supplierpassword1', '789 Supplier St', 'Supplier City', 'SC', '5678', TRUE, '2024-04-06', 3),
(7, 'Supplier2', 'Supplier', 777888999, 'supplier2@example.com', 'supplierpassword2', '321 Supplier St', 'Supplier Town', 'ST', '9012', TRUE, '2024-04-07', 3),
(8, 'Manager1', 'Manager', 222333444, 'manager1@example.com', 'managerpassword1', '654 Manager St', 'Manager City', 'MC', '3456', TRUE, '2024-04-08', 4),
(9, 'Manager2', 'Manager', 666777888, 'manager2@example.com', 'managerpassword2', '987 Manager St', 'Manager Town', 'MT', '7890', TRUE, '2024-04-09', 4),
(10, 'Customer1', 'Customer', 333444555, 'customer1@example.com', 'customerpassword1', '123 Customer St', 'Customer City', 'CC', '6789', TRUE, '2024-04-10', 2),
(11, 'Customer2', 'Customer', 888999000, 'customer2@example.com', 'customerpassword2', '456 Customer St', 'Customer Town', 'CT', '0123', TRUE, '2024-04-11', 2),
(12, 'Customer3', 'Customer', 555666777, 'customer3@example.com', 'customerpassword3', '789 Customer St', 'Customer Village', 'CV', '4567', TRUE, '2024-04-12', 2),
(13, 'Customer4', 'Customer', 000111222, 'customer4@example.com', 'customerpassword4', '321 Customer St', 'Customer Hamlet', 'CH', '8901', TRUE, '2024-04-13', 2),
(14, 'Customer5', 'Customer', 222333444, 'customer5@example.com', 'customerpassword5', '654 Customer St', 'Customer County', 'CC', '2345', TRUE, '2024-04-14', 2),
(15, 'Customer6', 'Customer', 999000111, 'customer6@example.com', 'customerpassword6', '987 Customer St', 'Customer State', 'CS', '6789', TRUE, '2024-04-15', 2);

-- Sample data for Orders table
INSERT INTO Orders (orderID, orderDate, status, totalNoItems, totalPrice, userID) VALUES
(1, '2024-04-01', 'Completed', 5, 150.00, 1),
(2, '2024-04-02', 'Pending', 3, 75.00, 2),
(3, '2024-04-03', 'Processing', 2, 50.00, 3),
(4, '2024-04-04', 'Completed', 4, 100.00, 4),
(5, 2024-04-05', 'Cancelled', 1, 25.00, 5),
(6, '2024-04-06', 'Pending', 2, 60.00, 6),
(7, '2024-04-07', 'Processing', 3, 90.00, 7),
(8, '2024-04-08', 'Completed', 4, 120.00, 8),
(9, '2024-04-09', 'Cancelled', 2, 55.00, 9),
(10, '2024-04-10', 'Completed', 3, 80.00, 10),
(11, '2024-04-11', 'Pending', 4, 100.00, 11),
(12, '2024-04-12', 'Processing', 2, 45.00, 12),
(13, '2024-04-13', 'Completed', 5, 110.00, 13),
(14, '2024-04-14', 'Pending', 2, 70.00, 14),
(15, '2024-04-15', 'Processing', 3, 95.00, 15);

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
INSERT INTO Payment (paymentID, paymentDate, paymentAmt, paymentMethod, cardNo, CVV, expiryDate, cardName) VALUES
(1, '2024-04-02', 50.00, 'Credit Card', 12345678, 123, '2026-04-01', 'John Doe'),
(2, '2024-04-03', 30.00, 'PayPal', NULL, NULL, NULL, 'Jane Smith'),
(3, '2024-04-04', 25.00, 'Credit Card', 98765432, 456, '2025-04-02', 'Alice Johnson'),
(4, '2024-04-05', 35.00, 'PayPal', NULL, NULL, NULL, 'Bob Williams'),
(5, '2024-04-06', 40.00, 'Credit Card', 11112222, 789, '2027-04-03', 'Admin Admin'),
(6, '2024-04-07', 20.00, 'Credit Card', 55556666, 321, '2026-04-04', 'Supplier1 Supplier'),
(7, '2024-04-08', 55.00, 'Credit Card', 99998888, 654, '2025-04-05', 'Manager1 Manager'),
(8, '2024-04-09', 45.00, 'Credit Card', 44443333, 987, '2027-04-06', 'Customer1 Customer'),
(9, '2024-04-10', 60.00, 'Credit Card', 88887777, 654, '2026-04-07', 'Customer2 Customer'),
(10, '2024-04-11', 75.00, 'Credit Card', 77776666, 321, '2025-04-08', 'Customer3 Customer'),
(11, '2024-04-12', 80.00, 'Credit Card', 66665555, 987, '2027-04-09', 'Customer4 Customer'),
(12, '2024-04-13', 65.00, 'Credit Card', 33332222, 123, '2026-04-10', 'Customer5 Customer'),
(13, '2024-04-14', 70.00, 'Credit Card', 22221111, 456, '2025-04-11', 'Customer6 Customer'),
(14, '2024-04-15', 85.00, 'Credit Card', 99990000, 789, '2027-04-12', 'Supplier2 Supplier'),
(15, '2024-04-16', 90.00, 'Credit Card', 88887777, 654, '2026-04-13', 'Manager2 Manager');

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
INSERT INTO Products (ProductID, ProductName, ProductType, UnitPrice, StockLvl) VALUES
(1, 'Product1', 'Type1', 10.00, 100),
(2, 'Product2', 'Type2', 20.00, 200),
(3, 'Product3', 'Type1', 15.00, 150),
(4, 'Product4', 'Type3', 25.00, 250),
(5, 'Product5', 'Type2', 30.00, 300),
(6, 'Product6', 'Type3', 35.00, 350),
(7, 'Product7', 'Type1', 40.00, 400),
(8, 'Product8', 'Type2', 45.00, 450),
(9, 'Product9', 'Type3', 50.00, 500),
(10, 'Product10', 'Type1', 55.00, 550),
(11, 'Product11', 'Type2', 60.00, 600),
(12, 'Product12', 'Type3', 65.00, 650),
(13, 'Product13', 'Type1', 70.00, 700),
(14, 'Product14', 'Type2', 75.00, 750),
(15, 'Product15', 'Type3', 80.00, 800);

-- Sample data
