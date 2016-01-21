Instruction to run the Web Service:
(Your public key must be present in the remote machine)


Running with .jar:
   You need application.properties (external) file to set local configurations:
   Simulator remote Machine:
   	   gspnHost=greatspn.dei.polimi.it
   Webservice's local port:
	   server.port=8181
   SSH parmas (Username, Private Key's path, known-hosts' path)
	   gspnUser=user
	   gspnUserPrivateKeyFile=/Users/jacoporigoli/.ssh/id_rsa 
	   gspnSetKnownHosts=/Users/jacoporigoli/.ssh/known_hosts
   Number of threads used to launch concurrent simulations (always ≤ than the total number of cores in the remote machine )
       pool.size=2

   Command used to launch .jar:
       java -jar [path to .jar/]WS4GSPN.jar --spring.config.location=[path to .properties/]application.properties
       
   A directory called simulations, and a .log file will be created in the same location in which this command have been launched.

Running in IDE:
   Be sure that application.properties' values inside the project are right.
   A directory called simulations, and a .log file will be created in the root.
	
(REMINDER
	To deploy file .jar (in order to be able to use an external .properties file):
		mvn -DskipTests package
	
	In this way I'm sure that also gspn.* values will be overwritten by an external .properties file.
	Maybe also with mvn package gspn.* values will be overwritten, but in order to generate tests valid gspn.* are required.
	TODO: check this
	TODO: creates different .properties profiles for testing and developing
	TODO: possibility to set (from .properties) local and remote simulation paths, remote simulation path to WNSIM, file names.
)
	