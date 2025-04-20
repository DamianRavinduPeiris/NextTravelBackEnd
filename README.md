# `✈️NextTravel-BackEnd.`  


Frontend repo can be found in <a href="https://github.com/DamianRavinduPeiris/NextTravel-FrontEnd">here.</a>

![image](https://github.com/DamianRavinduPeiris/NextTravel-FrontEnd/assets/115478137/296c5b4d-a140-4616-970f-0e4ba0345b77)

This backend powers the **NextTravel** web application, a travel planning platform designed to provide users with a seamless experience for booking hotels, vehicles, and travel packages. Developed using the **Spring Framework** and **Spring Security**, it ensures secure and efficient operations while adhering to modern software development best practices.


---

## Key Features

### User Functionality
- **Authentication and Authorization**: Secure login and signup with Spring Security.
- **Travel Planning**:
  - Browse and book travel packages.
  - Select hotels, vehicles, and packages tailored to individual needs.
- **Payment Integration**:
  - Integrated with a dummy **Stripe payment gateway** for transaction simulation.
  - Automatic email notifications sent to users upon successful booking.
- **User-Friendly APIs**: RESTful APIs designed for seamless frontend integration.

### Admin Panel
- **Resource Management**:
  - Manage hotels, vehicles, users, and travel packages.
  - Add, update, and delete resources efficiently.
- **Monitoring and Control**: Tools to oversee application resources and bookings.

### Architectural Highlights
- **Microservices Architecture**:
  - Ensures separation of concerns for better maintainability and scalability.
  - Modular design for improved system reliability.
- **Email Notifications**: Automated email system for booking confirmations.
- **Security**:
  - Spring Security for authentication and role-based access control.
  - Secure handling of sensitive information such as user data and transactions.


## Building and running locally.

To set up the project locally, follow these steps:

### Prerequisites
- **Java Development Kit (JDK)** 11 or higher
- **Maven** for dependency management
- A relational database (e.g., MySQL or PostgreSQL)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/DamianRavinduPeiris/NextTravelBackEnd.git
   ```
2. Navigate to the project directory:
   ```bash
   cd NextTravelBackEnd
   ```
3. Configure the application:
   - Update the `application.properties` file with your database connection details.
   - Add your Stripe API keys for the dummy payment gateway.
4. Build the project:
   ```bash
   mvn install
   ```
5. Run the application:
   ```bash
   mvn spring-boot:run
   ```
6. Access the APIs using tools like Postman or integrate them with the frontend.

---

## Contribution Guidelines

We welcome contributions to enhance this project! Please follow these steps to contribute:

1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Describe your changes"
   ```
4. Push to your feature branch:
   ```bash
   git push origin feature/your-feature-name
   ```
5. Open a pull request to the main repository.

---

## License

This project is licensed under the [MIT License](LICENSE).

For further inquiries or support, feel free to reach out:
