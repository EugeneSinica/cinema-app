# 🔴 Cinema App

This project has a primitive functionality, but is written using all modern Java 
software development technologies.

The main goal of the app is to create a user, let the user make order (buy tickets)
to a certain movie session in a certain cinema hall.

By default, the app has two roles: USER and ADMIN.
User can register, login and make orders. Admin can add movies, movie sessions, cinema halls.

# 🔵 Technologies used:
* Spring (Web MVC, Security, ORM)
* Hibernate (Core, Validator)
* MySQL
* Tomcat

# 🚩 How to run the app
First, clone the repo on GitHub. Second, open Intellij IDEA and open the project. Then configure Tomcat (fix war-exploded).
Also, you have to configure your database properties in main/resources/db.properties.
The next step is to run the app. When you see the login form, you can authorize as ADMIN (admin@test.ua : 1234) or USER (user@test.ua : 1234).
To test the full functionality I advise you to use Postman and sent requests with bodies.

That's all for now. Thanks 💎
