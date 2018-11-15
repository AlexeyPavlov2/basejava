[![Codacy Badge](https://api.codacy.com/project/badge/Grade/57cbfa231f194615aa3d096ec6dec387)](https://www.codacy.com/app/AlexeyPavlov2/basejava?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=AlexeyPavlov2/basejava&amp;utm_campaign=Badge_Grade)
## Учебное приложение "База данных резюме"
Развернутое приложение  ["База данных резюме"](https://resumespavlov.herokuapp.com/ "Приложение") на  [heroku.com](http://heroku.com/ "")


Приложение написано в ходе обучения на курсе Basejava.
Информация о курсе BaseJava,  и используемых технологиях - ниже.


#### [Курс BaseJava на javaops.ru](http://javaops.ru)

#### Разработка Web приложения "База данных резюме"
  -  используется: Java 8, <a href="https://zeroturnaround.com/rebellabs/java-tools-and-technologies-landscape-2016-trends/#java-ides-adoption">IntelliJ IDEA</a>,
    <a href="https://zeroturnaround.com/rebellabs/java-tools-and-technologies-landscape-2016-trends/#java-vcs-adoption">GitHib/Git</a>, Сервлеты, JSP, JSTL, Tomcat, JUnit, PostgreSQL, GSON, JAXB
  - хранение резюме
     -  в памяти на основе массива, отсортированного массива, списка и ассоциированного массива (Map)
     -  в файловой системе (File API и <a href="http://www.quizful.net/post/java-nio-tutorial">Java 7 NIO File API</a>)
        - в стандартной и кастомной сериализации Java
        - в формате JSON (<a href="https://github.com/google/gson">Google Gson</a>)
        - в формате XML (<a href="https://ru.wikipedia.org/wiki/Java_Architecture_for_XML_Binding">JAXB</a>)
     -  в реляционной базе <a href="https://ru.wikipedia.org/wiki/PostgreSQL">PostgreSQL</a>
  -  деплой веб приложения
     - в контейнер сервлетов <a href="http://tomcat.apache.org/">Tomcat</a>
     - в облачный сервис <a href="https://www.heroku.com/">Heroku</a>

Приложение разрабатываться начиная с первого занятия, основываясь на базовых темах курса:
**объектная модель, коллекции, система ввода-вывода, работа с файлами, сериализация, работа с XML, JSON, SQL, персистентность в базу данных (PostgreSQL), сервлеты, HTML/JSP/JSTL, веб-контейнер Tomcat, модульные тесты JUnit, java.util.Logging, система контроля версий Git.**

