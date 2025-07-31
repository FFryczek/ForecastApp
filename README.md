ForecastApp
===========

Prosta aplikacja pogodowa zbudowana w Java 24, Spring Boot i czystym HTML/CSS/JS.
Pokazuje aktualną pogodę i prognozę dla wybranych szczytów Tatr.
Frontend w dużej części wygenerowany przez ChatGPT (ale poprawiany i rozwijany ręcznie).

Tech stack:
- Java 24, Spring Boot
- HTML, CSS, JavaScript
- Dane pogodowe z Open-Meteo API

Features:
- Wybór spośród szczytów: Rysy, Kozi Wierch, Świnica, Kościelec, Szpiglasowy Wierch, Granaty, Mięguszowiecki Szczyt Wielki i inne
- Aktualna temperatura, opady deszczu, opady śniegu, wilgotność
- Prognoza dzienna: temperatura max/min, suma opadów, suma opadów konwekcyjnych
- Prognoza godzinowa na wybrany dzień (co 3h): temperatura, odczuwalna temperatura, ciśnienie, pokrywa śnieżna, zachmurzenie, UV index i inne
- Nowoczesny, responsywny interfejs użytkownika

How to run:
1) ./mvnw spring-boot:run
2) Otwórz w przeglądarce: http://localhost:8080/index.html

Project structure (simplified):

forecastapp
  . src
    . main
      . java
        . com
          . ffryczek
            . forecast
      . resources
        . static
          . index.html
          . details.html
          . script.js
          . details.js
          . styles.css
  . pom.xml
  . README.txt

Author:
Created by FFryczek
Frontend UI generated with help of ChatGPT

Demo video:
https://youtu.be/VzGl-L1Wtrs

License:
Free to use and improve!
