# E-Commerce Application

A full-stack e-commerce application built with Spring Boot (backend) and React (frontend), featuring product management, image uploads, and a responsive user interface.

## 🚀 Features

### Core Functionality
- **Product Management**: Add, view, update, and delete products
- **Image Upload**: Upload and display product images with PostgreSQL LOB storage
- **Responsive Design**: Mobile-friendly interface using Bootstrap 5
- **Real-time Updates**: Dynamic product listing with immediate UI updates
- **Search Functionality**: Search products by keywords

### Technical Features
- **RESTful API**: Clean REST endpoints for all operations
- **File Handling**: Multipart file upload with image validation
- **Transaction Management**: Proper database transaction handling with @Transactional
- **Error Handling**: Comprehensive exception handling and user feedback
- **Date Management**: Custom date formatting and parsing
- **Form Validation**: Client-side and server-side validation

## 🛠️ Technology Stack

### Backend
- **Framework**: Spring Boot 3.x
- **Database**: PostgreSQL with LOB (Large Object) support
- **ORM**: Hibernate/JPA
- **File Storage**: PostgreSQL bytea (binary data)
- **Build Tool**: Maven
- **Java Version**: 17+

### Frontend
- **Framework**: React 18.x
- **Routing**: React Router DOM
- **HTTP Client**: Axios
- **UI Framework**: Bootstrap 5
- **Notifications**: React Toastify
- **Build Tool**: Vite

## 📋 Prerequisites

### Backend Requirements
- Java 17 or higher
- PostgreSQL 12 or higher
- Maven 3.6+

### Frontend Requirements
- Node.js 16+ and npm/yarn
- Modern web browser

## 🔧 Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd EcomApp
```

### 2. Backend Setup

#### Configure PostgreSQL
```bash
# Create database
createdb ecommerce_db

# Update application.properties with your credentials
```

#### Update Backend Configuration
Edit `Backend/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

#### Build and Run Backend
```bash
cd Backend
mvn clean install
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### 3. Frontend Setup

#### Install Dependencies
```bash
cd Frontend
npm install
```

#### Configure API Endpoint
Update the API base URL in frontend components if needed (default: `http://localhost:8080/api`)

#### Run Development Server
```bash
npm run dev
```

The frontend will start on `http://localhost:5173`

## 📡 API Endpoints

### Product Endpoints
- `GET /api/products` - Get all products
- `GET /api/product/{id}` - Get product by ID
- `POST /api/product` - Add new product (multipart/form-data)
- `PUT /api/product/{id}` - Update product (multipart/form-data)
- `DELETE /api/product/{id}` - Delete product
- `GET /api/product/{id}/image` - Get product image
- `GET /api/products/search?keyword={keyword}` - Search products

## 🎯 Key Features Implementation

### Image Upload & Storage
- Images are stored as binary data (bytea) in PostgreSQL
- EAGER loading strategy for immediate image access
- Transaction management for consistent data operations
- Support for PNG and JPEG formats

### Date Handling
- Backend: Uses `dd-MM-yyyy` format with `@JsonFormat` annotation
- Frontend: Custom date parsing function to handle the specific format
- Handles edge cases like years `0022` → `2022`

### Error Handling
- Global exception handler for consistent error responses
- Proper HTTP status codes for different error scenarios
- Client-side validation with user-friendly error messages

### Form Validation
- Required field validation
- Numeric field validation (price > 0, stock ≥ 0)
- Image file type validation
- Date format validation

## 🐛 Common Issues & Solutions

### 400 Bad Request on Product Update
**Cause**: Empty FormData or missing required parameters
**Solution**: Ensure both `product` and `imageFile` are properly formatted. The `imageFile` is now optional in the backend.

### "Unable to access lob stream" Error
**Cause**: Hibernate LAZY loading issue with LOB data
**Solution**: Changed to EAGER loading and added `@Transactional` annotations

### "Invalid Date" Display
**Cause**: JavaScript's `new Date()` can't parse `dd-MM-yyyy` format
**Solution**: Custom `formatDate()` function handles the specific backend format

### 500 Internal Server Error (null to int)
**Cause**: Primitive `int` fields can't handle null values
**Solution**: Changed to `Integer` wrapper class and added null validation

## 🔍 Database Schema

### Product Table
```sql
CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    brand VARCHAR(255),
    price DECIMAL(10,2),
    category VARCHAR(255),
    release_date DATE,
    product_available BOOLEAN,
    stock_quantity INTEGER,
    image_name VARCHAR(255),
    image_type VARCHAR(255),
    image_data BYTEA
);
```

## 🚀 Deployment Considerations

### Backend
- Configure production database connection
- Set up proper CORS configuration for production
- Implement proper logging and monitoring
- Consider using cloud storage (AWS S3, etc.) for images in production

### Frontend
- Build for production: `npm run build`
- Configure proper API endpoint URLs
- Set up environment variables for different environments
- Implement proper error boundaries

## 🔧 Development Tips

### Running Tests
```bash
# Backend tests
cd Backend && mvn test

# Frontend tests  
cd Frontend && npm test
```

### Code Style
- Backend: Follow Spring Boot conventions
- Frontend: Use React hooks and functional components
- Consistent error handling patterns
- Proper TypeScript/JavaDoc documentation

### Debugging
- Use browser developer tools for frontend debugging
- Check Spring Boot logs for backend issues
- Use Postman for API testing
- Monitor browser console for JavaScript errors

## 📚 Learning Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [React Documentation](https://reactjs.org/docs/getting-started.html)
- [Bootstrap 5 Documentation](https://getbootstrap.com/docs/5.0/getting-started/introduction/)
- [PostgreSQL LOB Documentation](https://www.postgresql.org/docs/current/largeobjects.html)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For issues and questions:
1. Check the troubleshooting section above
2. Search existing issues in the repository
3. Create a new issue with detailed information about your problem

---

**Happy coding! 🎉**