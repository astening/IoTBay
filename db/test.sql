/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Ella
 * Created: May 13, 2024
 */

CREATE TABLE Users (
    userID INT NOT NULL,
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
    roleID INT,
    CONSTRAINT Users_PK PRIMARY KEY (UserID)
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
    CONSTRAINT Payment_FK2 FOREIGN KEY (userID) REFERENCES Users(userID)
);

INSERT INTO Users (userID, fName, lName, phoneNo, email, password, address, city, state, postCode,activation,registrationDate) VALUES
(123, 'James', 'Potter', null, 'james@gmail.com', 'password', null, null, null, null, null, null);

INSERT INTO PaymentMethod (userID, cardName, cardNo, expiryDate, CVV)
VALUES (123, 'James Potter', '1234 1234 1234 1234', '2024-12-31', 123);

INSERT INTO Payment (userID, orderID, paymentDate, paymentAmount)
VALUES (123, 1, '2024-05-13', 33.33),
(123, 2, '2024-04-12', 44.44),
(123, 3, '2024-03-11', 55.55),
(123, 4, '2024-05-13', 66.66);


SELECT * FROM USERS;
SELECT * FROM PaymentMethod;
SELECT * FROM Payment;