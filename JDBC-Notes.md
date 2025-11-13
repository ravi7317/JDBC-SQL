Here’s a **complete JDBC Notes (Java Database Connectivity)** — concise, structured, and interview-ready 👇

---

# **JDBC (Java Database Connectivity) Notes**

---

## **1. Introduction to JDBC**

**Definition:**
JDBC is a Java API used to connect and execute queries with databases.
It allows Java applications to interact with relational databases like MySQL, Oracle, PostgreSQL, etc.

**Full Form:** Java Database Connectivity

**Purpose:**

* To connect Java programs with databases.
* To execute SQL queries and retrieve results.

---

## **2. JDBC Architecture**

### **Main Components:**

1. **JDBC API** → Provides classes and interfaces for database operations.
2. **JDBC Driver Manager** → Manages different database drivers.
3. **JDBC Driver** → Connects Java application to a specific database.
4. **Database** → Stores actual data.

---

## **3. JDBC Driver Types**

| Type   | Name                    | Description                           | Example                      |
| ------ | ----------------------- | ------------------------------------- | ---------------------------- |
| Type 1 | JDBC-ODBC Bridge        | Uses ODBC driver (slower, deprecated) | sun.jdbc.odbc.JdbcOdbcDriver |
| Type 2 | Native-API Driver       | Uses native client libraries          | Oracle OCI driver            |
| Type 3 | Network Protocol Driver | Communicates via middleware server    | IDS Server driver            |
| Type 4 | Thin Driver (Pure Java) | Directly converts JDBC to DB protocol | MySQL Connector/J            |

✅ **Type 4 is most commonly used today.**

---

## **4. Steps to Connect Java with Database (5 Steps)**

```java
import java.sql.*;

class Example {
    public static void main(String[] args) throws Exception {
        // 1. Load the Driver class
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. Create Connection
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/mydatabase", "root", "password");

        // 3. Create Statement
        Statement stmt = con.createStatement();

        // 4. Execute Query
        ResultSet rs = stmt.executeQuery("SELECT * FROM employee");

        // 5. Process Result
        while(rs.next()) {
            System.out.println(rs.getInt(1)+" "+rs.getString(2));
        }

        // Close connection
        con.close();
    }
}
```

---

## **5. Important JDBC Interfaces**

| Interface             | Description                                                               |
| --------------------- | ------------------------------------------------------------------------- |
| **Driver**            | Handles communication with database.                                      |
| **Connection**        | Represents a connection session between Java and DB.                      |
| **Statement**         | Used to execute static SQL queries.                                       |
| **PreparedStatement** | Used to execute precompiled SQL queries with parameters (safer & faster). |
| **CallableStatement** | Used to call stored procedures.                                           |
| **ResultSet**         | Holds data retrieved from the database.                                   |

---

## **6. Types of Statements**

### **a) Statement**

* Used for simple SQL queries.

```java
Statement stmt = con.createStatement();
stmt.executeUpdate("INSERT INTO emp VALUES(1, 'Ravi')");
```

### **b) PreparedStatement**

* Precompiled, faster, prevents SQL Injection.

```java
PreparedStatement ps = con.prepareStatement("INSERT INTO emp VALUES(?, ?)");
ps.setInt(1, 1);
ps.setString(2, "Ravi");
ps.executeUpdate();
```

### **c) CallableStatement**

* Used to execute stored procedures.

```java
CallableStatement cs = con.prepareCall("{call getEmployee(?)}");
cs.setInt(1, 101);
ResultSet rs = cs.executeQuery();
```

---

## **7. ResultSet Navigation**

| Method                    | Description             |
| ------------------------- | ----------------------- |
| `next()`                  | Moves cursor forward.   |
| `previous()`              | Moves cursor backward.  |
| `first()`                 | Moves to first row.     |
| `last()`                  | Moves to last row.      |
| `absolute(int n)`         | Moves to the nth row.   |
| `getInt()`, `getString()` | Retrieve column values. |

---

## **8. Execute Methods**

| Method            | Use                        | Returns                                          |
| ----------------- | -------------------------- | ------------------------------------------------ |
| `executeQuery()`  | For SELECT                 | ResultSet                                        |
| `executeUpdate()` | For INSERT, UPDATE, DELETE | int (rows affected)                              |
| `execute()`       | For any SQL                | boolean (true → ResultSet, false → update count) |

---

## **9. Transaction Management**

Used to ensure **data integrity**.

```java
con.setAutoCommit(false); // Start transaction

stmt.executeUpdate("UPDATE account SET balance=balance-500 WHERE id=1");
stmt.executeUpdate("UPDATE account SET balance=balance+500 WHERE id=2");

con.commit(); // Save changes
// con.rollback(); // Undo changes
```

---

## **10. Batch Processing**

Used for executing multiple queries at once.

```java
Statement stmt = con.createStatement();
stmt.addBatch("INSERT INTO emp VALUES(1, 'Ravi')");
stmt.addBatch("INSERT INTO emp VALUES(2, 'Sita')");
stmt.executeBatch();
```

---

## **11. Data Access Object (DAO) Pattern**

**Purpose:**
To separate database logic from business logic.

**Example:**

```java
public class EmployeeDAO {
    Connection con;

    public EmployeeDAO(Connection con) {
        this.con = con;
    }

    public void addEmployee(Employee e) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO emp VALUES(?, ?)");
        ps.setInt(1, e.getId());
        ps.setString(2, e.getName());
        ps.executeUpdate();
    }
}
```

---

## **12. Connection Utility Class (Best Practice)**

```java
public class DBUtil {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = System.getenv("DB_USER");
        String pass = System.getenv("DB_PASS");
        return DriverManager.getConnection(url, user, pass);
    }
}
```

✅ Using `System.getenv()` hides credentials using environment variables.

---

## **13. SQL Injection & Prevention**

**SQL Injection:**
Malicious users inject harmful SQL via user input.

**Unsafe:**

```java
Statement st = con.createStatement();
st.executeQuery("SELECT * FROM users WHERE name='" + userInput + "'");
```

**Safe (PreparedStatement):**

```java
PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE name=?");
ps.setString(1, userInput);
```

---

## **14. Common Exceptions**

| Exception                 | Cause                   |
| ------------------------- | ----------------------- |
| `SQLException`            | General database errors |
| `ClassNotFoundException`  | Driver class not found  |
| `SQLSyntaxErrorException` | Wrong SQL query syntax  |

---

## **15. Best Practices**

* Always close `Connection`, `Statement`, `ResultSet` in `finally` block or use **try-with-resources**.
* Use **PreparedStatement** instead of Statement.
* Use **Connection Pooling** (via DataSource) for large-scale apps.
* Use **DAO pattern** for clean architecture.
* Handle exceptions properly and log errors.

---

