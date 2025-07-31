# Student Registration System

## Overview
This is a Java-based Student Registration System developed for the Object-Oriented Programming course. It demonstrates core OOP principles, JDBC integration, and file handling.

## Features
- Add, update, delete and list student records
- Register courses
- Admin and Student roles
- Data persistence using MySQL via JDBC
- Logging and configuration file via File I/O

## How to Run
1. Make sure you have JDK 8+ and MySQL installed.
2. Import the project into an IDE (e.g., IntelliJ or Eclipse).
3. Configure your `config.properties` file with DB credentials.
4. Run the `Main.java` from `project.ui`.

## Team Members
- Ezra Admasu
- Robengetu

## File Structure
- `project.model` - Data classes (POJOs)
- `project.abstract` - Abstract class (e.g., Account)
- `project.interfaces` - Interfaces for behaviors
- `project.dao` - JDBC database access
- `project.service` - Business logic
- `project.ui` - CLI or GUI interface
- `project.util` - Logging or utilities

## Notes
- Ensure your MySQL database is set up as described in the code.
- Use Git and GitHub for version control.

