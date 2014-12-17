## MusicRecital

If you're reading this then you've probably cloned the repository at [GitHub][]. 
Hopefully, you have just copied the source for this application and set
up a [Git][] SCM repository in this directory structure. 

The application was derived-from or started with an AppFuse [archetype][]. The
model template was a single module [Tapestry] 5.x framework and it uses AppFuse 3.0.0.
You will soon notice that the modelVersion tag at the top of the project pom is 4.0.0.
This means that the [Apache Maven][] project model pom is using version 4.0.0 of the XSD.
The [AppFuse][] framework version is in the properties section near the bottom of the pom.xml.
You will find that versions of your dependencies are easily found, identified, configured, and managed
in the Maven project object model.
 
The project object model (pom) is defined in the file pom.xml just below the 
project root directory. This file is configured to run for you locally as long
as you have a running instance of the [MySQL][] database and [SMTP][] relay service.
The service ports should be open on your local network or localhost (127.0.0.1). 
MySQL can be easily installed on your development machine, however installations vary by operating
system and distribution. MySQL installation and administration are beyond the scope of this
README but the following prerequisites are needed at a minimum:

1. [Java][] 6.x
2. [Apache Maven][] 3.1.0
3. [MySQL][] 5.x

Note: MusicRecital will run in a web container as-is without SMTP but this means
that emails (a feature of this system) will not work.
  
Once, you are confident that Java, Maven, and MySQL are installed and available, open a
terminal window and change-directory to your project clone (the source including a git repository).

1. Run "mvn jetty:run" and view the application at http://localhost:8080.

2. Please post questions for this application after subscribing to the developers
   mail list here: http://musicrecital.org/mailman/listinfo/dev

4. More information can be found later at: www.musicrecital.org. Currently, the 
   development site is hosted here http://musicrecital.org/project/ . You can also
   visit the Google+ community "MusicRecital" for news and updates.

## Background

[MusicRecital][] was built from the foundation of [AppFuse][]. AppFuse is a full-stack framework for building web applications on the JVM. Rather than re-inventing the wheel, it integrates some of the most popular frameworks today, including [Spring][], [Hibernate][], [Maven][], [jQuery][] and [Twitter Bootstrap][]. AppFuse supports a philosophy of change. This project also believes in using the latest stable open source and making applications better. Our mission first is to develop something of value every week.  And, to evolve a prototype into a robust application that meets our requirements. While the software dependencies change around us, we will do our best to keep up with version changes as our dependencies release their own  JARs and Libraries of value.  

If you arrived here with interests to help develop the software, please visit our [Maven project][] and be sure to join the "dev" list at our [mailing lists][] page. Users with a technical background may run this locally to see what this application is all about. We are currently building a prototype to be hosted for demonstration and to campaign for funding. The "users" mail list will be used to discuss this application even before it's actually released. We plan to build a community of people that will use this application to better the culture of the music recital. Please join the [mailing lists][] if you are interested.

To learn more about this project, subscribe to our [mailing lists][] or read the [latest news][].

## Licensing
MusicRecital is released under version 2.0 of the [Apache License][].

[AppFuse]: http://appfuse.org
[MusicRecital]: http://musicrecital.org
[Apache License]: http://www.apache.org/licenses/LICENSE-2.0
[latest news]: http://musicrecital.org
[mailing lists]: http://musicrecital.org/mailman/listinfo/
[Maven project]: http://musicrecital.org/project/
[GitHub]: http://github.com
[Java]: http://java.com/en/
[Apache Maven]: http://maven.apache.org/
[Maven]: http://maven.apache.org/
[MySQL]: http://www.mysql.com/
[Git]: http://git-scm.com/
[archetype]: http://maven.apache.org/guides/introduction/introduction-to-archetypes.html
[Tapestry]: http://tapestry.apache.org/
[SMTP]: http://en.wikipedia.org/wiki/Simple_Mail_Transfer_Protocol
[Spring]: http://projects.spring.io/spring-framework/
[Hibernate]: http://hibernate.org/
[jQuery]: http://jquery.com/
[Twitter Bootstrap]: http://getbootstrap.com/2.3.2/