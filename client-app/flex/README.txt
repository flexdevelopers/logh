10/19/2010

 #REQUIREMENTS#
	Ant installed, either eclipse plugin or command line

 #PROPERTIES FILE#
	In the build.properties file, you will need to change the output directory in order for the application to successfully build: 
	#source.server.dir=C:/IBM/WebShpere70/AppServer/server
	(another common location is C:/ibm/HttpServer70/htdocs)
	- Also you may need to change the message broker to use your specific server (and port, if necessary) 
	#messagebroker=http://7dev07.lw-lmco.com:9081
	
 #ADD CUSTOM CODE HINTS#
  	Overlay metadata.xml and metadata4.xml from the build/ directory to:
  	C:\Program Files\Adobe\Adobe Flash Builder 4.5\eclipse\plugins\com.adobe.flexbuilder.codemodel_4.5.0.308971\resources\metadata
  	- or - where Flash Builder 4.5 has been installed and restart application

 #BUILD#
	To Build, run the "Deploy All Development" from ANT, this builds out all the necessary items such as RSL, HTML templates, and all modules 
	that are used in the application and will also copy over demo data and background images. It also builds all applications, tests, and documentation.

 #ADD PERFORMANCE TO FLASH BUILDER#
	Edit the FlashBuilder.ini found in your Flash Builder install folder
	
	Change:
	-Xmx1024m
	
	Add: 
	-XX:MaxGCPauseMillis=10 
	–XX:MaxHeapFreeRatio=70 
	-XX:+UseConcMarkSweepGC 
	–XX:+CMSIncrementalPacing
	
	This runs the garbage collection in a separate thread and minimizes the pause when a collection occurs

 #Set Flash Builder SDK#
	In Flash Builder, to go to: Window > Preferences > Flash Builder > Installed Flex SDKs > Add...
	Locate SDK checked out in project: i.e. C:\starteamwf\ROSS\Source Code\rossTR\flexclient\sdks\4.1.0 
	Ensure this SDK is selected as default. 