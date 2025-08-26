# JDBC Sales Management System

This project is a simple sales management system developed to practice JDBC concepts after learning SQL with MySQL and MS SQL Server. It provides basic functionalities to manage clients, products, staff, and sales records through a console-based interface.

## Features

- **Client Management:** Add and list clients.
- **Product Management:** Add and list products.
- **Staff Management:** Add and list staff members.
- **Sales Management:** Register and list sales.
- **Input Validation:**
  - **Email:** Validates email format using regex.
  - **Phone Number:** Validates Angolan phone numbers (must start with 9 and have 9 digits).

## Database Setup

Here are the `CREATE TABLE` statements for the four tables used in this project. You can use these to set up your database.

### Client Table
```sql
CREATE TABLE client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(9) UNIQUE NOT NULL
);
```

### Product Table
```sql
CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT NOT NULL
);
```

### Staff Table
```sql
CREATE TABLE staff (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(9) UNIQUE NOT NULL,
    salary DECIMAL(10, 2) NOT NULL
);
```

### Sales Table
```sql
CREATE TABLE sales (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    client_id INT,
    quantity INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method VARCHAR(50),
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (client_id) REFERENCES client(id)
);
```

## Future Plans

I am considering adding a graphical user interface (GUI) to this project using either **JavaFX** or **Swing** after I have learned these frameworks. This will make the application more user-friendly and visually appealing.

## How to Run

This is a Maven project. To run it, you can use your favorite IDE (like Eclipse, IntelliJ, or VS Code) to import the project and run the `App.java` class.

Alternatively, you can run it from the command line:

1.  **Compile the project:**
    ```bash
    mvn compile
    ```
2.  **Run the application:**
    ```bash
    mvn exec:java -Dexec.mainClass="ao.jose.App"
    ```

## Database Configuration

The database connection is managed by the `Connecter.java` class for modularity and ease of maintenance.

Before running the application, you need to update the database credentials in `src/main/java/ao/jose/connection/Connecter.java`:

```java
public class Connecter {
    private static final String URL = "jdbc:mysql://localhost:3306/console_store?useSSL=false&serverTimezone=UTC";
    private static final String USER = "your_username"; // <-- Change this
    private static final String PASSWORD = "your_password"; // <-- Change this
    // ...
}
```

## Sample Data

To get you started, here are some sample `INSERT` statements you can run to populate your database:

### Clients
```sql
INSERT INTO client (name, email, phone_number) VALUES
('Jose AFonso', 'john.doe@example.com', '912345678'),
('Paulo Andre', 'jane.smith@example.com', '987654321');
```

### Products
```sql
INSERT INTO product (name, description, price, stock_quantity) VALUES
('Laptop', 'A powerful laptop for all your needs', 1200.00, 50),
('Mouse', 'An ergonomic mouse', 25.00, 200),
('Keyboard', 'A mechanical keyboard', 75.00, 150);
```

### Staff
```sql
INSERT INTO staff (name, email, phone_number, salary) VALUES
('Admin User', 'admin@store.com', '900000000', 50000.00);
```
