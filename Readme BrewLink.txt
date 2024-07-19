ClientsMenu - Front Office Functionality
Overview
The ClientsMenu class is part of the Front Office functionality, facilitating user interactions within a coffee shop application. It allows users to perform various tasks, such as logging in, registering, managing delivery addresses, and placing orders.

How It Works
The MainMenuClient method is the entry point for user interactions. It presents a menu-driven interface to users and handles their input to perform specific actions based on their choices. Here's an overview of the functionalities:

Login & Registration:

Users can log in with existing credentials or register as new clients.
For login, the system validates the provided user credentials against the database.
For registration, users are prompted to provide necessary details like email, password, name, contact, and address. The system then adds this information to the database.
Order Management:

Logged-in users can create new orders, view current orders, and access their order history.
Orders are represented by the Order class, and relevant actions like creating, displaying current/historical orders are handled within this context.
Address Management:

Users can edit their delivery addresses, such as adding, updating, or removing addresses.
Menu Navigation:

The menu navigation allows users to easily switch between different functionalities or exit the system.
Methods Overview
MainMenuClient: Handles user interactions based on the selected options.
ClientMenu: Displays the client menu based on user status (logged-in or not).
Login: Facilitates user login by prompting for credentials and validating them against the database.
validateUser: Validates user credentials by comparing them with stored information in the database.
Register: Allows new users to register by providing necessary details and adds them to the database.
Usage
To use the ClientsMenu functionality:

Compile and Run:

Compile the Java files and run the MainMenuClient method to initiate the user interaction.
Navigate the Menu:

Follow the displayed options to perform login, registration, manage addresses, and create/view orders.
Requirements
This code requires access to a database that stores user information, orders, and addresses. Make sure the necessary database configurations and connections are set up for seamless functionality.

NOTE: place BrewLink.db to path C:\BrewLinkDB and place on the same directory sqlite-jdbc-3.30.1.jar (directory added to code path to make easy the move to C:\)