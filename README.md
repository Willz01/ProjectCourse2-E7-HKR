# Covid-19 Test Service

The Covid-19 Test Service is a a JavaFX application for tracking and analysing patient test results of diseases such as Covid-19.

## Getting Started
These instructions will get a copy of the project up and running on your local machine for
development and testing purposes. The project consists of a JavaFX application and connects to
a MySQL database.

### Prerequisites
You will need a MySQL database for data storage as well as an SMTP server for email sending.

We recommend to run the project using IntelliJ IDEA. You can donwload it from the [offical website](https://www.jetbrains.com/idea/).
After installing IntelliJ, open the root of the GitHub repository.
The dependency manager Maven will automatically download any missing dependencies.

### Running the Application
Before running the application there are two config files that have to be created. Both will be located in the `/src/main/resources` folder.

The first config file is called `hibernate.cfg.xml`. Copy the existing `hibernate.cfg.default.xml` and add your database connection properties to the file. Place your hostname, database name, username and password as indicated below.

```xml
[...]
<hibernate-configuration>
    <session-factory>
        [...]
        <property name="connection.url">jdbc:mysql://[MY_HOST]:3306/[MY_DATABASE]</property>
        [...]
        <property name="connection.username">[MY_USERNAME]</property>
        <property name="connection.password">[MY_PASSWORD]</property>
        <property name="hibernate.hbm2ddl.auto">update</property>
    </session-factory>
</hibernate-configuration>
```

The second config file is called `mail.properties`. Copy the existing `mail.default.properties` file and add your SMTP server connection details. Using [Mailtrap.io](https://mailtrap.io/), the config file should look like this:

```properties
mail.smtp.host=smtp.mailtrap.io
mail.smtp.port=465
mail.smtp.from.name=Acme Corp
mail.smtp.from=noreply@example.com
mail.smtp.user=[MY_USERNAME]
mail.smtp.password=[MY_PASSWORD]
mail.smtp.auth=true
```

To run the JavaFX application execute the following command from the Maven console on the right side of your IntelliJ IDEA project window.
```
mvn clean javafx:run
```
![Execute Maven Goal button](/documentation/execute_maven_goal.png)
![mvn clean javafx:run command](/documentation/mvn_clean_javafx_run.png)

## Built with
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE
* [Maven](https://maven.apache.org/) - Build tool
* [MySQL](https://www.mysql.com/) - Relational database
* [MailTrap](https://mailtrap.io) - Mail trap
* [Hibernate](https://hibernate.org) - Hibernate

## Authors
* **Hani Adel Alzir** - [Hani311](https://github.com/Hani311)
* **Wills Ekanem** - [Willz01](https://github.com/Willz01)
* **Philip Mavromatis Klempin** - [klempin](https://github.com/klempin)
* **Mohammed Nabil Mohammed Al-Qalisi** - [mnq11](https://github.com/mnq11)
