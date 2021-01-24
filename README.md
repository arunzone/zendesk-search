# zendesk-search

## Design

In high level it has 3 modules

* Cli
* Service
* Repository

`CLI` --> `Service` --> `Repository`

`CLI` reads input from user, which is interpreted as commands and executed.  
`CommandFactory` interprets user input and instantiate a command  
Every command has separate responsibility to set its context and invoking relevant service get its job done.  
Console output are organised in `ConsoleDisplay` class to enhance re-usability  
<br/>
Once the input are read, it is validated and delegated to `SearchService`  
`SearchService` has relevant `Repository` dependency where the entities are read from relevant file  
Each input file is associated to an Entity eg. `User` entity is read from `users.json`  
<br/>
Exception while reading file and data portability are handled using runtime user defined exception  
Retrieved matching results are formatted and displayed in the console using `ConsoleReportGenerator`

App is the main class

---

## Prerequisite

maven 3.6.2 Jdk 14

---

## How to use?

### Run program

if maven and java14 are available in machine
<blockquote>
mvn compile exec:java -Dexec.mainClass="com.zendesk.App"
</blockquote>
otherwise (using docker)  
<blockquote>
docker run -v "$PWD:/home" -w /home -it maven:3.6.3-adoptopenjdk-14 bash  

mvn compile exec:java -Dexec.mainClass="com.zendesk.App"
</blockquote>

#### Customize input files

By default it uses input files from test resources, it can be overridden by program arguments like this  
User input file can be configured using `-u` option like
<blockquote>
mvn compile exec:java -Dexec.mainClass="com.zendesk.App" -Dexec.args="-u /tmp/users.json"
</blockquote>
Organization input file can be configured using `-o` option like  
<blockquote>
mvn compile exec:java -Dexec.mainClass="com.zendesk.App" -Dexec.args="-o /tmp/organizations.json"
</blockquote>
Ticket input file can be configured using `-t` option like  
<blockquote>
mvn compile exec:java -Dexec.mainClass="com.zendesk.App" -Dexec.args="-t /tmp/tickets.json"
</blockquote>


---

### Chose entity to search on

After welcome message, user is presented with options  
`Select [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket]?`  
enter `1` for selecting User  
enter `2` for selecting Organization  
enter `3` for selecting Ticket  
Then the selected entity acknowledgment message displayed and next option is displayed to chose from

### Option for an entity

`Select [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket, s ‣ Search, h ‣ Help]?`
This will allow you chose either search or to view field names to search on along with option to change entity
selection  
enter `s` for search on currently chosen entity  
enter `h` for displaying field names

### Search

Prompt to select field name  
`Enter User search term:` where you can enter `User` field name  
`Enter User search value:` value for the above mentioned field name  
Immediately the results were displayed in console

---

## Quality

### Findbugs

`mvn findbugs:gui`

### checkstyle

`mvn checkstyle::check`

### pmd

`mvn pmd::pmd`
