# <img src="readme_images/Bluemoon_Logo.png" alt="Logo" width="100" align="right"/>
# BLUE MOON ADMIN WEB

---

## BUILDING THE APPLICATION

---

### MAVEN

The project can be built using **Maven**. Use the following command to build the application:

```bash
mvn clean install
```
It will create this folder structure, with the **compiled application** within.
<p align="center">
    <img src="readme_images/maven_install.png" alt="Maven Install"/>
</p>

### DOCKER

The application can be containerized using Docker. Use the provided Dockerfile to build the **Docker image** with the following command:
```bash
docker build -t bluemoonadminweb .
```
After building the docker image you can **create a container** with the following command:
```bash
docker run -p 8081:8081 bluemoonadminweb
```
You can also specify **enviroment variables** to set the **API URL** or the administrator account used internally by the app.
```bash
docker run -p 8081:8081 -e API_URL=url APP_USER_USERNAME=usr APP_USER_PASSWORD=secret bluemoonadminweb
```
By default, the connection data are the follwing: \
**Username**: bluemoon_admin \
**Password**: ur%]SEmRPcvMqfB;2xs>!

## USER MANUAL

---

### LOGGING IN

The application provides a secure login system for authorized administrators to access the management functionalities.

<p align="center">
    <img src="readme_images/login.png" alt="Login"/>
</p>

After successfully loggin in you will be redirected to the dashboard, where you will be able to access all sections of the application.

<p align="center">
    <img src="readme_images/dashboard.png" alt="dashboard"/>
</p>

### SILVER TYPES

Manage and view information related to different types of silver.

<p align="center">
    <img src="readme_images/silvertypes.png" alt="Silver Types"/>
</p>

Edit form:

<p align="center">
    <img src="readme_images/edit_silvertype.png" alt="Edit Silver Type"/>
</p>

### TRADES

Track and manage trades within the system.

<p align="center">
    <img src="readme_images/trades.png" alt="Trades"/>
</p>

Inspect form:

<p align="center">
    <img src="readme_images/inspect_trade.png" alt="Inspect Trade"/>
</p>

When you inspect a form you can either **approve** or **deny** it. Afterwards you will be able to **dispatch** it, which will hide it from the application.

### PRODUCTS

View and manage various products stored in the database.

<p align="center">
    <img src="readme_images/products.png" alt="Products"/>
</p>

Edit form:

<p align="center">
    <img src="readme_images/edit_product.png" alt="Edit Product"/>
</p>