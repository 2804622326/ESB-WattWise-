# ESB WattWise

This project is a full-stack mobile app prototype built with **Spring Boot (backend)** and **React Native + Expo (frontend)**. It enables users to monitor energy usage, complete eco-friendly tasks, earn points, and redeem rewards.

---

## 📦 Prerequisites

Please ensure the following tools are installed on your system:

- **Java 17**
- **Maven 3.9+**
- **MySQL 8.x**
- **Node.js 18+**
- **Expo CLI**  
  Install with:
  ```bash
  npm install -g expo-cli
  ```

⸻

🗃️ Step 1: Create the Database

Start your MySQL server, then create the required database schema:

CREATE DATABASE esb-data;

If needed, update database credentials in:
ESB-APP/src/main/resources/application.properties

Example:

spring.datasource.url=jdbc:mysql://localhost:3306/esb-database
spring.datasource.username=root
spring.datasource.password=your_password

⸻

🚀 Step 2: Run the Backend

You can start the backend by running the Spring Boot application in one of two ways:

Option 1: From Terminal

cd ESB-APP
./mvnw spring-boot:run

Option 2: From IDE

Open the project in IntelliJ or Eclipse and run:

com.esb.esbapp.EsbAppApplication

The API will be available at:

http://localhost:9192

⸻

📱 Step 3: Run the Frontend 1. Open frontend/config.js and update the backend URL:

export const BASE_URL = 'http://localhost:9192';

    2.	Install dependencies and start the app:

cd frontend
npm install
npm start

    3.	Scan the QR code with Expo Go app on your mobile device, or run it in an emulator.

⸻

🧪 Testing
• Backend:

cd ESB-APP
./mvnw test

    •	Frontend: (No test script defined at the moment)
