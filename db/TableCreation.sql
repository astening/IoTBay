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
