# 🛒 Supermarket Inventory Management System

A full-stack **Spring Boot backend** project designed to manage inventory, billing, stock-in, and reporting in a supermarket. This system supports **role-based access**, **secure authentication**, and is ready for deployment with a MySQL database.

---

## 🚀 Features

* 📦 **Stock-In Management**: Add new inventory items (name, brand, quantity, price, expiry, etc.)
* 💰 **Billing Module**: Handle customer purchases and update stock in real-time
* 🧾 **Sales Tracking**: Automatically track all billed items as daily sales
* 🧯 **Damaged & Expiry Management**: Mark and process expired or damaged stock
* ⚠️ **Low Stock Alerts**: Aggregates quantities of same-name/brand and alerts if below threshold
* 📉 **Profit & Loss Reports**: Track daily and monthly financials with expiry/damage/billing logic
* 🥇 **Top-Selling Products & Brands**: Rank based on quantity sold
* 🎯 **Discounts on Near-Expiry**: Offer automatic discounts when items approach expiry date
* 🛡️ **User Role Authorization**: Role-based access using Spring Security

---

## 🔐 Role-Based Access

| Role       | Access                           |
| ---------- | -------------------------------- |
| `OWNER`    | All reports, profit, top-sellers |
| `MANAGER`  | Sales reports                    |
| `INVENTOR` | Stock-in, low stock, expiry      |
| `CASHIER`  | Billing                          |

Implemented using `@PreAuthorize` annotations and manual Spring Security context setup.
## 🛠️ Tech Stack

### ⚙️ Backend:

* **Java 17**
* **Spring Boot** (Core, Web, Security, JPA)
* **MySQL** for persistent storage
* **Lombok** to reduce boilerplate

### 🔒 Security:
* Spring Security with **BCrypt password encryption**
* Manual login + authentication context setting
* `@PreAuthorize` for role-based endpoint protection

### 🔗 Integration:
* JPA Repository layer
* DTO-based communication
* Proper exception handling and validation

## 🧠 Core Logical Highlights

* ✅ All product actions consider brand + name grouping (e.g., all "Parle-G")
* ✅ Threshold logic on grouped quantity
* ✅ Profit = Billing - Cost - Expiry - Damage
* ✅ Role security without using full JWT (yet)
* ✅ Daily and Monthly aggregation logic

## 🧪 Future Scope

* 🔐 JWT-based login with token-based access
* 📊 Frontend dashboard using React.js
* 🧾 PDF generation for receipts and reports
* ☁️ Deploy on Render/EC2 with MySQL cloud

## 📝 Getting Started
1. Clone the repo:

```bash
git clone https://github.com/your-username/supermarket-inventory
```

2. Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/supermarket_db
spring.datasource.username=root
spring.datasource.password=your_password
```

3. Run the app using your IDE or `mvn spring-boot:run`

## 🙋‍♀️ Author

**Nandini Sharma**
1st Year B.Tech, Computer Science 🎓
Backend Developer | DSA Enthusiast 

> 🧠 *"Learning to build logic is more valuable than memorizing theory."*
