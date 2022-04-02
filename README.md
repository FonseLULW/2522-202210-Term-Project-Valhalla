# Valhalla
**A BCIT COMP-2522 term project**

## About Valhalla
When aliens land on planet Earth, only _**YOU**_ are able to stop them.  
As the last viking in Earth, you are tasked with defending your home  
from inter-planetary threats. You must build towers to defend your  
base, alternatively you can fight them yourself!

Good luck brave bearskin, and soon may we meet at the doors of ```Valhalla```!

**Genres:** Tower Defense, Adventure, PvE
## Authors
>**Fonse Clarito**  
```ID: A01262284```  
```Gmail: jalfonsclarito@gmail.com```  
[```Github: FonseLULW```](https://github.com/FonseLULW)  
[```LinkedIn: Jose Alfonso Clarito```](https://www.linkedin.com/in/jaclarito)

>**Kai Oh**  
```ID: A01274246```  
```Gmail: kaka8651@gmail.com```  
[```Github: kaioh08```](https://github.com/kaioh08)  
[```LinkedIn: Kai Oh```](https://www.linkedin.com/in/kaioh08)  
[```Website: Kai-Oh-Resume-Website```](https://kai-oh-resume-website.netlify.app/)

## Project made using
* Software: Java
* Graphics: JavaFX
* Database: SQL using MySQL (and MySQL Workbench)
* Build: Maven
* Game Engine: FXGL by Almas Baimagambetov ```(AlmasB) almaslvl@gmail.com```

## Using the Valhalla database
1. Make your local version of the Valhalla database from `valhalla.sql` in the _**src**_ package using MySQL 
(or MySQL Workbench).
2. Inside _**src.main.java.ca.bcit.comp2522.termproject.valhalla.game**_, alter a few values in `DatabaseManager.class`
   1. **Line 11**: Change the value of `DRIVER` to the name of your _JDBC_ driver.
   2. **Line 12**: Change the value of `PORT` to 3306 (the MySQL port) if not already.
   3. **Line 14**: Change the value of `DATABASE_NAME` to comp2522 if not already.
   4. **Line 16**: Change the value of `CONNECTION_USERNAME` to your MySQL connection username.
   5. **Line 17**: Change the value of `CONNECTION_PASSWORD` to your MySQL connection password.
3. Check if the database works by running `DatabaseManager.main()` in line 57.

