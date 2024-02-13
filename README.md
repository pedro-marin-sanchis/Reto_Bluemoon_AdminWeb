# <img src="readme_images/Bluemoon_Logo.png" alt="Logo" width="100" align="right"/>
# BLUE MOON ADMIN WEB

---

## BUILDING THE APPLICATION

---

### MAVEN

The project can be built using Maven. Use the following command to build the application:

```bash
mvn clean install
```

### DOCKER

The application can be containerized using Docker. Use the provided Dockerfile to build the Docker image:
```bash
docker build -t bluemoonadminweb .
```

## DEPLOYMENT

---

## USER MANUAL

---

### LOGGING IN

The application provides a secure login system for authorized administrators to access the management functionalities.
<p align="center">
    <img src="readme_images/login.png" alt="Login"/>
</p>

<p align="center">
    <img src="readme_images/dashboard.png" alt="dashboard"/>
</p>

### SILVER TYPES

Manage and view information related to different types of silver.
<p align="center">
    <img src="readme_images/silvertypes.png" alt="Silver Types"/>
</p>

<p align="center">
    <img src="readme_images/edit_silvertype.png" alt="Edit Silver Type"/>
</p>

### TRADES

Track and manage trades within the system.
<p align="center">
    <img src="readme_images/trades.png" alt="Trades"/>
</p>

<p align="center">
    <img src="readme_images/inspect_trade.png" alt="Inspect Trade"/>
</p>

### PRODUCTS

View and manage various products stored in the database.
<p align="center">
    <img src="readme_images/products.png" alt="Products"/>
</p>

<p align="center">
    <img src="readme_images/edit_product.png" alt="Edit Product"/>
</p>