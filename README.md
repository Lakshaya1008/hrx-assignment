# 🚀 Bajaj Finserv Health – Webhook Assignment (JAVA)

**👤 Author:** Lakshaya Jain  
**🎓 Reg No:** 22BCE11253  
**📧 Email:** lakshayajain93@gmail.com  

---

## 📌 Overview

This Spring Boot application automates the complete workflow required in the assignment:

1. Calls the `generateWebhook/JAVA` API using name, regNo, and email.  
2. Receives a webhook URL and access token from the server.  
3. Reads the SQL solution from `finalQuery.sql`.  
4. Submits the SQL query to the received webhook with the token in the Authorization header.  
5. Prints the submission response in the console.

The entire flow executes automatically when the application starts.

---

## 🧩 Tech Stack

| Component | Technology |
|----------|------------|
| Language | Java 21 |
| Framework | Spring Boot 3.x |
| HTTP Client | WebClient |
| Build Tool | Maven |
| Packaging | JAR |

---

## 📂 Project Structure

hrx-assignment/
│── src/
│ ├── main/
│ │ ├── java/com/assignment/hrx/
│ │ │ ├── HrxApplication.java
│ │ │ ├── WebClientConfig.java
│ │ │ └── GenerateResponse.java
│ │ └── resources/
│ │ └── application.properties
│── finalQuery.sql
│── pom.xml
│── target/hrx-0.0.1-SNAPSHOT.jar

---

## ▶️ How to Run

### 1️⃣ Build the project

mvn clean package

### 2️⃣ Run the JAR

java -jar target/hrx-0.0.1-SNAPSHOT.jar

### Expected Output

Calling generateWebhook...
Webhook = https://...
Access Token = <token>
Submitting finalQuery...
Submission Response: {"success":true,"message":"Webhook processed successfully"}


---

## 📤 Flow Summary


Your App
|
|-- POST --> generateWebhook/JAVA
| (gets webhook + token)
|
Reads finalQuery.sql
|
|-- POST --> webhook (Authorization header)
{ regNo, finalQuery }
|
Server validates → Response printed


---

## ✔️ Assignment Requirements Checklist

| Requirement | Status |
|------------|--------|
| API call to generateWebhook | ✅ Completed |
| Extract webhook + token | ✅ Completed |
| Read SQL from file | ✅ Completed |
| Submit SQL to webhook | ✅ Completed |
| Add Authorization header | ✅ Completed |
| Build JAR + GitHub repo | ✅ Completed |

---

## 📦 Release (JAR)

You can download the executable JAR from the **Releases** section of this repository.

---

## 📝 Notes

- No secrets or tokens are stored in the repository.  
- Uses Spring WebClient for clean, non-blocking HTTP calls.  
- The project runs end-to-end without manual input.


