CREATE TABLE AccessLog (
  LogID INT(5) NOT NULL,
  UserID INT(6) NOT NULL,
  LogDate DATE,
  LogDetails VARCHAR(30),
  CONSTRAINT AccessLog_PK PRIMARY KEY (LogID),
  CONSTRAINT AccessLog_FK FOREIGN KEY (UserID) REFERENCES User(UserID)
);

CREATE TABLE Staff (
  StaffID INT(5) NOT NULL,
  StaFName VARCHAR(10),
  StaLName VARCHAR(10),
  StaPhoneNo INT(10),
  StaEmail VARCHAR(20),
  RoleCode INT(15),
  CONSTRAINT Staff_PK PRIMARY KEY (StaffID)
);

CREATE TABLE Customer (
  CustomerID INT(5) NOT NULL,
  CustFName VARCHAR(10),
  CustLName VARCHAR(10),
  CustPhoneNo INT(10),
  CustEmail VARCHAR(20),
  CustStreetAddress VARCHAR(20),
  CustCity VARCHAR(20),
  CustState VARCHAR(5),
  CustPostCode INT(4),
  CONSTRAINT Customer_PK PRIMARY KEY (CustomerID)
);

CREATE TABLE Invoice (
  InvoiceID INT(5) NOT NULL,
  CustomerID INT(5) NOT NULL,
  InvoiceDate DATE,
  InvoiceDueDate DATE,
  CONSTRAINT Invoice_PK PRIMARY KEY (InvoiceID),
  CONSTRAINT Invoice_FK FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

CREATE TABLE Payment (
  PaymentID INT(5) NOT NULL,
  PaymentDate DATE,
  PaymentAmt FLOAT(10),
  PaymentMethod VARCHAR(20),
  CardNo INT(10),
  CVV INT(3),
  ExpiryDate DATE,
  CardName VARCHAR(40),
  CONSTRAINT Payment_PK PRIMARY KEY (PaymentID)
);
