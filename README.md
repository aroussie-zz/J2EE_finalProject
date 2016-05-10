# J2EE_finalProject
This final project was about developing a Multi-tiered enterprise application, consisting of a persistence layer, a service layer, a presentation layer and also providing some services or transactions. My web application was connected to a MySQL database, and I use SQL queries and JDBC to manage transactions with it. I used JPA for my domain model and EJB components as the service layer. Finally, I used XHTML language and JSF Facelets to build the user Interface. I provided some security with a Login/Password to access to the application, also some security to add a new user like email validation using AJAX.

The idea of the project:
I'm a french student and in my french school we have several partnerships with foreign schools like IIT. Unfortunately, my school doesn't really help us to be prepared to go abroad, or answer to our questions about the administrative points or about how to find a appartment that kind of stuff...

So I have the idea to do a web site with different sections corresponding to each city where we have school partnerships. For example a section "IIT", another "Plymouth University" etc...

On this web site there will be two kinds of user :

      - The students like me who are/were in this foreign schools
      - The "visitors" who want to know more about the school, the city etc

On this web site, the students will be able to :

      - Create and show their profile with their name, their email adress, if now they work in the country, etc
      - Create topics to talk about a subject like "What to do for getting a VISA" or "places to see"

The visitors will be able to :

       - See all of the topics of each section
       - See every students' profiles
       - Post their questions in the section they are interested in

To connect : 
To access to the application, a login and password combinaison is asked. Here is the list by default of login/password with the roles associated to them:

ROLE      LOGIN       PASSWORD
Admin	    admin	      adminpassword
Student	  student1	  studentun
Student	  student2	  studentdeux
Visitor	  visitor1	  visitorun
Visitor	  visitor2	  visitordeux

You can see some snapshots of the application in the "Snapshots" folder
