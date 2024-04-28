CREATE TABLE Users (
    UserID INT(6) NOT NULL,
    Email VARCHAR(20),
    Password VARCHAR(12),
    IsActive VARCHAR(2),
    RegistrationDate DATE,
    LastLogin DATE,
    CONSTRAINT RegisteredUser_PK PRIMARY KEY (UserID)
);
