CREATE TABLE RegisteredUser (
    UserID INT NOT NULL,
    Email VARCHAR(20),
    Password VARCHAR(12),
    IsActive VARCHAR(2),
    RegistrationDate DATE,
    LastLogin DATE,
    CONSTRAINT RegisteredUser_PK PRIMARY KEY (UserID)
);
