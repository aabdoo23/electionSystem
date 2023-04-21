# electionSystem
Election System
The Election System is a program that allows users to manage election campaigns, register new candidates and voters, and monitor the voting process. It is a GUI-based desktop application built using Java Swing.

# Features
The Election System has the following features:

Create new elections
Add candidates to elections
Register new voters
Allow voters to log in and vote in the election
Monitor the voting process
Display the results of the election

# How to use
To use the Election System, follow these steps:

Clone or download the source code from the project repository.
Open the project in an IDE that supports Java.
Compile and run the Main class to start the application.
From the main menu, you can choose to create a new election, add candidates to an existing election, register new voters, or log in as a voter to vote in the election.
Once the voting period has ended, you can view the results of the election from the admin panel.

# Classes
The following classes are included in the Election System:

Main: The entry point of the application. It creates a new instance of the MainMenu class.
MainMenu: The main menu of the application. It allows users to create new elections, add candidates to existing elections, register new voters, and log in as a voter to vote in the election.
AdminForm: The admin panel of the application. It allows the administrator to view the results of the election.
Election: Represents an election campaign. It contains a list of candidates and voters.
Candidate: Represents a candidate in an election. It has a name, party affiliation, and a unique ID.
Voter: Represents a voter in an election. It has a name, address, and a unique ID.
DB: A static class that handles data storage and retrieval.

# Dependencies
The Election System depends on the following libraries:

javax.swing: A Java library for creating graphical user interfaces.
java.awt.event: A Java library for handling events.

# Authors
The Election System was developed by Abdelrahman Saleh as a project for syncInterns.
