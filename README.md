# ATM Simulation System

## Overview
The ATM Simulation System is a **Java-based project** that simulates real-world ATM functionality. Users can perform essential banking operations such as deposits, withdrawals, transfers, and balance inquiries. The system also supports account creation, PIN management, transaction history, and file-based data persistence.

## Features
- Secure Login with Customer Number and PIN
- Create a New Account  
- Forgot / Change PIN option  
- Deposit and Withdraw money (with limits)  
- Transfer between Checking and Savings account  
- Transaction History with timestamps  
- Data Persistence using `accounts.txt` file  
- Receipt printing for transactions  
- Basic safety tips on login  

## Project Structure
- **ATM.java** → Main class (program entry point)
- **Account.java** → Account details and operations (deposit, withdraw, transfer)
- **OptionMenu.java** → Menu handling and user interaction
- **accounts.txt** → Stores user account details

## Technologies Used
- **Java Programming** – Core OOP implementation
- **File Storage** – Managing account persistence
- **Collections Framework** – using HashMap and LinkedList to manage accounts and transaction records
- **Input Handling** – Scanner for user interactions
- **Money Formatter** – DecimalFormat for currency representation

## Requirements
- **Java Development Kit (JDK) 8+ :** works with terminal or IDEs (IntelliJ IDEA, Eclipse, VS Code)
- **accounts.txt file :** created automatically on first run
- **Optional :** IDE or text editor for viewing/modifying code

## How to Run
1. **Clone the repository :** https://github.com/Sudharsan-336/ATM-Project.git
2. **Compile the Java files :** javac ATM.java Account.java OptionMenu.java
3. **Run the program :** java ATM

## Usage Instructions
### Creating a New Account
1. Select **Create New Account**
2. Enter a unique **Customer Number** and a **PIN** (4–6 digits)
3. Your account is saved in `accounts.txt` and is ready to use

### Logging In
1. Select **Login**
2. Enter your **Customer Number** and **PIN**
3. After successful authentication, access the **Account Menu**

### Account Menu Options
Once logged in, choose an account:

#### Checking Account :
- View Balance
- Withdraw (min $1, max $1000)
- Deposit (min $1, max $10,000)
- Transfer to Savings

#### Savings Account :
- View Balance
- Withdraw (min $1, max $1000)
- Deposit (min $1, max $10,000)
- Transfer to Checking

#### Other Options :
- Transaction History → View last 5 transactions
- Change PIN → Update your PIN (4–6 digits)
- Exit → Logout safely

#### Forgot PIN :
- Select **Forgot PIN** from the main menu
- Enter your Customer Number and a new PIN (4–6 digits)
- The system updates your account and prompts you to login with the new PIN

#### Logging Out :
- Select Exit from the account menu
- All data is automatically saved in `accounts.txt` and the session ends safely





