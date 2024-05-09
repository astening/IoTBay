CREATE TABLE Role (
    roleID INT NOT NULL,
    roleName VARCHAR(10),
    CONSTRAINT Role_PK PRIMARY KEY (roleID)
);

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
    CONSTRAINT Users_PK PRIMARY KEY (UserID),
    CONSTRAINT Users_FK FOREIGN KEY (roleID) REFERENCES Role(roleID)
);

CREATE TABLE Orders (
    orderID INT NOT NULL,
    orderDate DATE,
    status VARCHAR(10),
    totalNoItems INT,
    totalPrice DOUBLE,
    userID INT,
    CONSTRAINT Order_PK PRIMARY KEY (orderID),
    CONSTRAINT Order_FK FOREIGN KEY (userID) REFERENCES Users(userID)
);

CREATE TABLE Shipment (
    shipmentID INT,
    shipmentDate Date,
    carrier VARCHAR(20),
    trackingNo INT,
    CONSTRAINT Shipment_PK PRIMARY KEY (shipmentID)
);

CREATE TABLE Invoice (
  invoiceID INT NOT NULL,
  userID INT NOT NULL,
  invoiceDate DATE,
  invoiceDueDate DATE,
  CONSTRAINT Invoice_PK PRIMARY KEY (invoiceID),
  CONSTRAINT Invoice_FK FOREIGN KEY (userID) REFERENCES Users(userID)
);

CREATE TABLE PaymentMethod (
  paymentMethodID INT NOT NULL,
  userID INT NOT NULL,
  cardName VARCHAR(40),
  cardNo INT,
  expiryDate DATE,
  CVV INT,
  CONSTRAINT PaymentMethod_PK PRIMARY KEY (paymentMethodID),
  CONSTRAINT PaymentMethod_FK FOREIGN KEY (userID) REFERENCES Users(userID)
)

CREATE TABLE Payment (
  paymentID INT NOT NULL,
  paymentMethodID INT,
  paymentDate DATE,
  paymentAmt FLOAT,
  CONSTRAINT Payment_PK PRIMARY KEY (paymentID),
  CONSTRAINT Payment_FK FOREIGN KEY (paymentMethodID) REFERENCES PaymentMethod(paymentMethodID)
);

CREATE TABLE AccessLog (
  logID INT NOT NULL,
  userID INT NOT NULL,
  logDate DATE,
  logDetails VARCHAR(30),
  CONSTRAINT AccessLog_PK PRIMARY KEY (logID),
  CONSTRAINT AccessLog_FK FOREIGN KEY (userID) REFERENCES Users(userID)
);

CREATE TABLE Product (
  ProductID INT NOT NULL,
  ProductName VARCHAR(30) NOT NULL,
  ProductType VARCHAR(20),
  UnitPrice FLOAT,
  StockLvl INT,
  CONSTRAINT Product_PK PRIMARY KEY (ProductID)
);

CREATE TABLE OrderLineItem (
    itemQuantity INT,
    orderID INT,
    productID INT,
    CONSTRAINT OrderLineItem_FK1 FOREIGN KEY (orderID) REFERENCES Orders(orderID),
    CONSTRAINT OrderLineItem_FK2 FOREIGN KEY (productID) REFERENCES Product(productID)
);

