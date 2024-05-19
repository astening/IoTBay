CREATE TABLE Users (
    userID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    fName VARCHAR(50),
    lName VARCHAR(50),
    phoneNo INT,
    email VARCHAR(50),
    password VARCHAR(50),
    address VARCHAR(100),
    city VARCHAR(30),
    state VARCHAR(3),
    postCode VARCHAR(4),
    activation BOOLEAN,
    registrationDate DATE,
    position VARCHAR(20) NOT NULL
);

CREATE TABLE Orders (
    orderID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    orderDate VARCHAR(20),
    status VARCHAR(10),
    totalNoItems INT,
    totalPrice DOUBLE,
    userID INT,
    CONSTRAINT Order_FK FOREIGN KEY (userID) REFERENCES Users(userID)
);

CREATE TABLE Shipment (
    shipmentID INT,
    shipmentDate Date,
    carrier VARCHAR(20),
    trackingNo INT,
    CONSTRAINT Shipment_PK PRIMARY KEY (shipmentID)
);

CREATE TABLE AccessLog (
  logID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  userID INT NOT NULL,
  logDate DATE,
  logDetails VARCHAR(30),
  loginTimeStamp TIMESTAMP,
  logoutTimeStamp TIMESTAMP,
  CONSTRAINT AccessLog_FK FOREIGN KEY (userID) REFERENCES Users(userID)
);

CREATE TABLE Products (
  ProductID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  ProductName VARCHAR(30) NOT NULL,
  ProductType VARCHAR(20),
  UnitPrice FLOAT,
  StockLvl INT
);

CREATE TABLE OrderLineItem (
    itemQuantity INT,
    orderID INT,
    productID INT,
    CONSTRAINT OrderLineItem_FK1 FOREIGN KEY (orderID) REFERENCES Orders(orderID),
    CONSTRAINT OrderLineItem_FK2 FOREIGN KEY (productID) REFERENCES Products(productID)
);

CREATE TABLE PaymentMethod (
    paymentMethodID INT GENERATED ALWAYS AS IDENTITY NOT NULL,
    userID INT NOT NULL,
    cardName VARCHAR(40),
    cardNo VARCHAR(20),
    expiryDate DATE,
    CVV INT,
    CONSTRAINT PaymentMethod_PK PRIMARY KEY (paymentMethodID),
    CONSTRAINT PaymentMethod_FK FOREIGN KEY (userID) REFERENCES Users(userID)
);

-- orderID would reference order table
CREATE TABLE Payment (
    paymentID INT GENERATED ALWAYS AS IDENTITY NOT NULL,
    userID INT NOT NULL,
    orderID INT NOT NULL,
    paymentDate DATE,
    paymentAmount DOUBLE,
    CONSTRAINT Payment_PK PRIMARY KEY (paymentID),
    CONSTRAINT Payment_FK1 FOREIGN KEY (userID) REFERENCES Users(userID),
    CONSTRAINT Payment_FK2 FOREIGN KEY (orderID) REFERENCES Orders(orderID)
);

-- Users Table
INSERT INTO Users (fName, lName, phoneNo, email, password, address, city, state, postCode, activation, registrationDate, position) VALUES
('John', 'Doe', 1234567890, 'john.doe@example.com', 'password123', '123 Main St', 'City1', 'ST', '1234', TRUE, '2023-01-01', 'Individual'),
('Jane', 'Smith', 1234567891, 'jane.smith@example.com', 'password123', '124 Main St', 'City2', 'ST', '1234', TRUE, '2023-01-02', 'Company'),
('Michael', 'Johnson', 1234567892, 'michael.johnson@example.com', 'password123', '125 Main St', 'City3', 'ST', '1234', TRUE, '2023-01-03', 'Systems Admin'),
('Emily', 'Davis', 1234567893, 'emily.davis@example.com', 'password123', '126 Main St', 'City4', 'ST', '1234', TRUE, '2023-01-04', 'Salesperson'),
('Daniel', 'Miller', 1234567894, 'daniel.miller@example.com', 'password123', '127 Main St', 'City5', 'ST', '1234', TRUE, '2023-01-05', 'Clerk'),
('Sophia', 'Garcia', 1234567895, 'sophia.garcia@example.com', 'password123', '128 Main St', 'City6', 'ST', '1234', TRUE, '2023-01-06', 'Manager'),
('James', 'Martinez', 1234567896, 'james.martinez@example.com', 'password123', '129 Main St', 'City7', 'ST', '1234', TRUE, '2023-01-07', 'Individual'),
('Isabella', 'Brown', 1234567897, 'isabella.brown@example.com', 'password123', '130 Main St', 'City8', 'ST', '1234', TRUE, '2023-01-08', 'Company'),
('William', 'Jones', 1234567898, 'william.jones@example.com', 'password123', '131 Main St', 'City9', 'ST', '1234', TRUE, '2023-01-09', 'Systems Admin'),
('Olivia', 'Wilson', 1234567899, 'olivia.wilson@example.com', 'password123', '132 Main St', 'City10', 'ST', '1234', TRUE, '2023-01-10', 'Salesperson'),
('Lucas', 'Moore', 1234567800, 'lucas.moore@example.com', 'password123', '133 Main St', 'City11', 'ST', '1234', TRUE, '2023-01-11', 'Clerk'),
('Mia', 'Taylor', 1234567801, 'mia.taylor@example.com', 'password123', '134 Main St', 'City12', 'ST', '1234', TRUE, '2023-01-12', 'Manager'),
('Ethan', 'Anderson', 1234567802, 'ethan.anderson@example.com', 'password123', '135 Main St', 'City13', 'ST', '1234', TRUE, '2023-01-13', 'Individual'),
('Ava', 'Thomas', 1234567803, 'ava.thomas@example.com', 'password123', '136 Main St', 'City14', 'ST', '1234', TRUE, '2023-01-14', 'Company'),
('Mason', 'Jackson', 1234567804, 'mason.jackson@example.com', 'password123', '137 Main St', 'City15', 'ST', '1234', TRUE, '2023-01-15', 'Systems Admin'),
('Charlotte', 'White', 1234567805, 'charlotte.white@example.com', 'password123', '138 Main St', 'City16', 'ST', '1234', TRUE, '2023-01-16', 'Salesperson'),
('Elijah', 'Harris', 1234567806, 'elijah.harris@example.com', 'password123', '139 Main St', 'City17', 'ST', '1234', TRUE, '2023-01-17', 'Clerk'),
('Amelia', 'Martin', 1234567807, 'amelia.martin@example.com', 'password123', '140 Main St', 'City18', 'ST', '1234', TRUE, '2023-01-18', 'Manager'),
('Logan', 'Lee', 1234567808, 'logan.lee@example.com', 'password123', '141 Main St', 'City19', 'ST', '1234', TRUE, '2023-01-19', 'Individual'),
('Evelyn', 'Perez', 1234567809, 'evelyn.perez@example.com', 'password123', '142 Main St', 'City20', 'ST', '1234', TRUE, '2023-01-20', 'Company'),
('Alexander', 'Thompson', 1234567810, 'alexander.thompson@example.com', 'password123', '143 Main St', 'City21', 'ST', '1234', TRUE, '2023-01-21', 'Systems Admin'),
('Harper', 'King', 1234567811, 'harper.king@example.com', 'password123', '144 Main St', 'City22', 'ST', '1234', TRUE, '2023-01-22', 'Salesperson'),
('Henry', 'Wright', 1234567812, 'henry.wright@example.com', 'password123', '145 Main St', 'City23', 'ST', '1234', TRUE, '2023-01-23', 'Clerk'),
('Avery', 'Scott', 1234567813, 'avery.scott@example.com', 'password123', '146 Main St', 'City24', 'ST', '1234', TRUE, '2023-01-24', 'Manager'),
('Liam', 'Green', 1234567814, 'liam.green@example.com', 'password123', '147 Main St', 'City25', 'ST', '1234', TRUE, '2023-01-25', 'Individual');

-- Products Table
INSERT INTO Products (ProductName, ProductType, UnitPrice, StockLvl) VALUES
('Smart Thermostat', 'Thermostat', 59.99, 100),
('Smart Bulb', 'Lighting', 19.99, 150),
('Security Camera', 'Security', 129.99, 75),
('Smart Lock', 'Security', 89.99, 80),
('Smart Speaker', 'Audio', 99.99, 120),
('Smart TV', 'Entertainment', 499.99, 50),
('Smartwatch', 'Wearable', 199.99, 100),
('Fitness Tracker', 'Wearable', 79.99, 200),
('Smart Refrigerator', 'Appliance', 1299.99, 25),
('Smart Oven', 'Appliance', 699.99, 40),
('Robot Vacuum', 'Appliance', 399.99, 60),
('Smart Doorbell', 'Security', 149.99, 90),
('Smart Plugs', 'Utility', 29.99, 200),
('Smart Light Switch', 'Lighting', 39.99, 175),
('Smart Thermostat Pro', 'Thermostat', 159.99, 50),
('4K Smart TV', 'Entertainment', 899.99, 30),
('Smart Display', 'Utility', 129.99, 85),
('Wi-Fi Router', 'Networking', 149.99, 110),
('Smart Garage Door Opener', 'Utility', 99.99, 70),
('Smart Irrigation Controller', 'Utility', 199.99, 40),
('Smart Bed', 'Furniture', 2999.99, 10),
('Smart Pillow', 'Furniture', 199.99, 50),
('Smart Mirror', 'Furniture', 499.99, 25),
('Smart Scale', 'Health', 79.99, 100),
('Smart Air Purifier', 'Health', 299.99, 45);

-- Orders Table
INSERT INTO Orders (orderDate, status, totalNoItems, totalPrice, userID) VALUES
('2024-01-01', 'Shipped', 3, 179.97, 1),
('2024-01-01', 'Pending', 2, 299.98, 1),
('2024-01-03', 'Delivered', 1, 129.99, 1),
('2024-01-04', 'Shipped', 1, 99.99, 2),
('2024-01-05', 'Pending', 2, 399.98, 2),
('2024-01-06', 'Delivered', 3, 599.97, 2),
('2024-01-07', 'Shipped', 1, 49.99, 7),
('2024-01-08', 'Pending', 2, 159.98, 7),
('2024-01-09', 'Delivered', 1, 499.99, 7),
('2024-01-10', 'Shipped', 3, 599.97, 8),
('2024-01-11', 'Pending', 2, 179.98, 8),
('2024-01-12', 'Delivered', 1, 299.99, 8),
('2024-01-13', 'Shipped', 1, 199.99, 13),
('2024-01-14', 'Pending', 2, 399.98, 13),
('2024-01-15', 'Delivered', 3, 699.97, 13),
('2024-01-16', 'Shipped', 1, 99.99, 14),
('2024-01-17', 'Pending', 2, 259.98, 14),
('2024-01-18', 'Delivered', 1, 399.99, 14),
('2024-01-19', 'Shipped', 3, 699.97, 20),
('2024-01-20', 'Pending', 2, 159.98, 20),
('2024-01-21', 'Delivered', 1, 299.99, 20),
('2024-01-22', 'Shipped', 1, 199.99, 21),
('2024-01-23', 'Pending', 2, 399.98, 21),
('2024-01-24', 'Delivered', 3, 599.97, 21),
('2024-01-25', 'Shipped', 1, 99.99, 21);

-- Shipment Table
INSERT INTO Shipment (shipmentID, shipmentDate, carrier, trackingNo) VALUES
(1, '2023-01-01', 'UPS', 123456),
(2, '2023-01-02', 'FedEx', 234567),
(3, '2023-01-03', 'DHL', 345678),
(4, '2023-01-04', 'USPS', 456789),
(5, '2023-01-05', 'UPS', 567890),
(6, '2023-01-06', 'FedEx', 678901),
(7, '2023-01-07', 'DHL', 789012),
(8, '2023-01-08', 'USPS', 890123),
(9, '2023-01-09', 'UPS', 901234),
(10, '2023-01-10', 'FedEx', 1234567),
(11, '2023-01-11', 'DHL', 2345678),
(12, '2023-01-12', 'USPS', 3456789),
(13, '2023-01-13', 'UPS', 4567890),
(14, '2023-01-14', 'FedEx', 5678901),
(15, '2023-01-15', 'DHL', 6789012),
(16, '2023-01-16', 'USPS', 7890123),
(17, '2023-01-17', 'UPS', 8901234),
(18, '2023-01-18', 'FedEx', 9012345),
(19, '2023-01-19', 'DHL', 12345678),
(20, '2023-01-20', 'USPS', 23456789),
(21, '2023-01-21', 'UPS', 34567890),
(22, '2023-01-22', 'FedEx', 45678901),
(23, '2023-01-23', 'DHL', 56789012),
(24, '2023-01-24', 'USPS', 67890123),
(25, '2023-01-25', 'UPS', 78901234);

-- AccessLog Table
INSERT INTO AccessLog (userID, logDate, logDetails, loginTimeStamp, logoutTimeStamp) VALUES
(1, '2023-01-01', 'Login', '2023-01-01 08:00:00', '2023-01-01 12:00:00'),
(2, '2023-01-02', 'Login', '2023-01-02 09:00:00', '2023-01-02 13:00:00'),
(3, '2023-01-03', 'Login', '2023-01-03 10:00:00', '2023-01-03 14:00:00'),
(4, '2023-01-04', 'Login', '2023-01-04 11:00:00', '2023-01-04 15:00:00'),
(5, '2023-01-05', 'Login', '2023-01-05 12:00:00', '2023-01-05 16:00:00'),
(6, '2023-01-06', 'Login', '2023-01-06 08:00:00', '2023-01-06 12:00:00'),
(7, '2023-01-07', 'Login', '2023-01-07 09:00:00', '2023-01-07 13:00:00'),
(8, '2023-01-08', 'Login', '2023-01-08 10:00:00', '2023-01-08 14:00:00'),
(9, '2023-01-09', 'Login', '2023-01-09 11:00:00', '2023-01-09 15:00:00'),
(10, '2023-01-10', 'Login', '2023-01-10 12:00:00', '2023-01-10 16:00:00'),
(11, '2023-01-11', 'Login', '2023-01-11 08:00:00', '2023-01-11 12:00:00'),
(12, '2023-01-12', 'Login', '2023-01-12 09:00:00', '2023-01-12 13:00:00'),
(13, '2023-01-13', 'Login', '2023-01-13 10:00:00', '2023-01-13 14:00:00'),
(14, '2023-01-14', 'Login', '2023-01-14 11:00:00', '2023-01-14 15:00:00'),
(15, '2023-01-15', 'Login', '2023-01-15 12:00:00', '2023-01-15 16:00:00'),
(16, '2023-01-16', 'Login', '2023-01-16 08:00:00', '2023-01-16 12:00:00'),
(17, '2023-01-17', 'Login', '2023-01-17 09:00:00', '2023-01-17 13:00:00'),
(18, '2023-01-18', 'Login', '2023-01-18 10:00:00', '2023-01-18 14:00:00'),
(19, '2023-01-19', 'Login', '2023-01-19 11:00:00', '2023-01-19 15:00:00'),
(20, '2023-01-20', 'Login', '2023-01-20 12:00:00', '2023-01-20 16:00:00'),
(21, '2023-01-21', 'Login', '2023-01-21 08:00:00', '2023-01-21 12:00:00'),
(22, '2023-01-22', 'Login', '2023-01-22 09:00:00', '2023-01-22 13:00:00'),
(23, '2023-01-23', 'Login', '2023-01-23 10:00:00', '2023-01-23 14:00:00'),
(24, '2023-01-24', 'Login', '2023-01-24 11:00:00', '2023-01-24 15:00:00'),
(25, '2023-01-25', 'Login', '2023-01-25 12:00:00', '2023-01-25 16:00:00');

-- PaymentMethod Table
INSERT INTO PaymentMethod (userID, cardName, cardNo, expiryDate, CVV) VALUES
(1, 'John Doe', '1234 5678 1234 5678', '2025-01-01', 123),
(2, 'Jane Smith', '2345678923456789', '2025-02-01', 234),
(7, 'James Martinez', '7890123478901234', '2025-07-01', 789),
(8, 'Isabella Brown', '8901234589012345', '2025-08-01', 890),
(13, 'Ethan Anderson', '3456789034567890', '2025-01-01', 456),
(14, 'Ava Thomas', '4567890145678901', '2025-02-01', 567),
(20, 'Evelyn Perez', '0123456701234567', '2025-08-01', 234),
(21, 'Alexander Thompson', '1234567812345678', '2025-09-01', 345);

INSERT INTO Payment (userID, orderID, paymentDate, paymentAmount) VALUES
(1, 1, '2024-01-01', 299.99),
(1, 1, '2024-01-03', 199.99),
(3, 1, '2024-01-05', 99.99),
(4, 2, '2024-01-07', 399.99),
(5, 2, '2024-01-09', 149.99),
(6, 2, '2024-01-11', 249.99),
(7, 7, '2024-01-13', 499.99),
(8, 7, '2024-01-15', 59.99),
(9, 7, '2024-01-17', 119.99),
(10, 8, '2024-01-19', 349.99),
(11, 8, '2024-01-21', 299.99),
(12, 8, '2024-01-23', 49.99),
(13, 13, '2024-01-25', 179.99),
(14, 13, '2024-01-27', 599.99),
(15, 13, '2024-01-29', 199.99),
(16, 14, '2024-02-01', 149.99),
(17, 14, '2024-02-03', 399.99),
(18, 14, '2024-02-05', 299.99),
(19, 20, '2024-02-07', 99.99),
(20, 20, '2024-02-09', 199.99),
(21, 20, '2024-02-11', 349.99),
(22, 21, '2024-02-13', 499.99),
(23, 21, '2024-02-15', 59.99),
(24, 21, '2024-02-17', 119.99),
(25, 21, '2024-02-19', 349.99);

INSERT INTO OrderLineItem (itemQuantity, orderID, productID) VALUES
(1, 1, 1),
(2, 2, 2),
(1, 3, 3),
(3, 4, 4),
(2, 5, 5),
(1, 6, 6),
(3, 7, 7),
(1, 8, 8),
(2, 9, 9),
(4, 10, 10),
(2, 11, 11),
(1, 12, 12),
(3, 13, 13),
(1, 14, 14),
(2, 15, 15),
(4, 16, 16),
(2, 17, 17),
(3, 18, 18),
(1, 19, 19),
(2, 20, 20),
(3, 21, 21),
(1, 22, 22),
(2, 23, 23),
(4, 24, 24),
(3, 25, 25);
