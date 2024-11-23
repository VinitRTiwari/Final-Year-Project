# Final-Year-Project
Teamwork of Vinit Tiwari and Priyanshu Agrawal ...

# Flow of the project :----------------------

Start → Librarian Login
Login Successful → Main Dashboard
Options: Manage Books, Manage Users, Manage Transactions, Manage Fines, Generate Reports
Manage Books → [Options: Add, Edit, Delete, Update Book Status]
Manage Users → [Options: Add/Edit/Delete User, Monitor Borrowed Books/Fines]
Manage Transactions → [Track Issues/Returns/Overdue Fines]
Manage Fines → Track Fine Payments
Generate Reports → View Transaction History/Overdue Books/Fines

End

***************************************************
# Start :--------------------------

Librarian Login

Input: Username and password
Output: Login successful (if credentials are valid)
Main Dashboard

Options Available:
Manage Books
Manage Users (students/faculty)
Manage Transactions
Manage Fines
Generate Reports
Manage Books

# Add Book :-------------------------------

Input: Book details (title, author, ISBN, etc.)
Output: Book added to the system
Edit Book:
Input: Book ID, updated details
Output: Book details updated
Delete Book:
Input: Book ID
Output: Book removed from inventory
Update Book Status (Borrowed, Available, etc.)
Manage Users

# Add/Edit/Delete Users :-------------------------

Input: User details (student or faculty)
Output: User data added/updated/deleted
Monitor Borrowed Books/Fines:
Output: List of books borrowed, fines, overdue status.
Manage Transactions

# Track Book Issues :------------------------------

Input: Issue Book to User, record Issue Date, Due Date.
Output: Transaction record created.
Track Book Returns:
Input: Book returned by User.
Output: Update book status to Available, log return transaction.
Calculate Fines (if book is overdue):
Output: Fine calculated and added to User's account.

# Manage Fines :---------------------------

Track Fine Payments:
Input: Payment details.
Output: Mark fine as paid in the system.


# Generate Reports :---------------------

Generate reports based on:
Books borrowed
Overdue books
Fines owed
Transaction history
End


# Managing books (adding/editing/deleting/updating status) :------------------------------------
Managing users (adding/editing/removing users and monitoring borrowed books/fines).
Managing transactions (tracking issues/returns, calculating overdue fines).
Managing fines (tracking and marking fines as paid).
Generating reports based on the library activities....



## DATABSE ##

# Books Table :------------------------------------------
This table stores information about the books available in the library.

Column Name	Data Type	Description
BookID	INT	Primary Key, Unique identifier for each book
Title	VARCHAR(255)	Title of the book
Author	VARCHAR(255)	Author of the book
ISBN	VARCHAR(20)	International Standard Book Number (unique)
Category	VARCHAR(50)	Category/genre of the book
PublicationYear	INT	Year the book was published
CopiesAvailable	INT	Number of copies available in the library
Status	ENUM('Available', 'Borrowed', 'Reserved', 'Damaged')	Current status of the book

 
# Users Table :----------------------------------------------------
This table stores information about the users (students and faculty members) who borrow books from the library.

Column Name	Data Type	Description
UserID	INT	Primary Key, Unique identifier for each user
Name	VARCHAR(255)	Full name of the user
Role	ENUM('Student', 'Faculty')	Role of the user (Student or Faculty)
Email	VARCHAR(255)	Email address of the user (optional)
Status	ENUM('Active', 'Blocked')	Status of the user (Active/Blocked)
BorrowedBooksCount	INT	Number of books the user has currently borrowed
FineAmount	DECIMAL(10,2)	Total fines accumulated by the user


# Transactions Table :----------------------------------------------
This table stores information about book transactions, including when a book is borrowed or returned.

Column Name	Data Type	Description
TransactionID	INT	Primary Key, Unique identifier for each transaction
BookID	INT	Foreign Key from the Books table
UserID	INT	Foreign Key from the Users table
IssueDate	DATE	The date when the book was issued
ReturnDate	DATE	The date when the book was returned
DueDate	DATE	The date by which the book should be returned
Status	ENUM('Issued', 'Returned', 'Overdue')	The status of the transaction (Issued, Returned, Overdue)


# Fines Table :------------------------------------------------------
This table tracks fines related to overdue books or other reasons (e.g., damage).

Column Name	Data Type	Description
FineID	INT	Primary Key, Unique identifier for each fine
UserID	INT	Foreign Key from the Users table
Amount	DECIMAL(10,2)	Fine amount charged to the user
Reason	VARCHAR(255)	Reason for the fine (e.g., Late Return, Damaged Book)
PaidStatus	ENUM('Paid', 'Unpaid')	Indicates if the fine has been paid
PaidDate	DATE	The date when the fine was paid (if applicable)


# Add a New Book :-------------------------------------------------

INSERT INTO Books (Title, Author, ISBN, Category, PublicationYear, CopiesAvailable, Status)
VALUES ('The Great Gatsby', 'F. Scott Fitzgerald', '9780743273565', 'Fiction', 1925, 10, 'Available');

# Issue a Book to a User :------------------------------------

SELECT CopiesAvailable FROM Books WHERE BookID = 1 AND Status = 'Available';

INSERT INTO Transactions (BookID, UserID, IssueDate, DueDate, Status)
VALUES (1, 123, '2024-11-22', '2024-12-06', 'Issued');

# Track Book Return and Update Status :--------------------------

UPDATE Transactions 
SET ReturnDate = '2024-11-25', Status = 'Returned' 
WHERE TransactionID = 1001;


# UPDATE Books :------------------------------------------------

SET CopiesAvailable = CopiesAvailable + 1, Status = 'Available' 
WHERE BookID = 1;

# Add a Fine for Late Return :------------------------------------------

INSERT INTO Fines (UserID, Amount, Reason, PaidStatus)
VALUES (123, 5.00, 'Late Return', 'Unpaid');




# Relationships Between Tables :---------------------------------------
Books and Transactions: One-to-many relationship.

A single book can have many transactions (one per issue and return).
Users and Transactions: One-to-many relationship.

A user can have many transactions (borrowing multiple books over time).
Users and Fines: One-to-many relationship.

A user can accumulate multiple fines (for overdue books, damaged books, etc.).
Books and Users: Indirect relationship through the Transactions Table.

Books are associated with users via transactions (book issue and return).


