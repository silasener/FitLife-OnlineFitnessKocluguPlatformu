# FitLife - Online Fitness Coaching Platform

## Introduction

The FitLife project aims to create a web application where users can receive online fitness coaching services. This platform provides personalized guidance and support to help users achieve their fitness and healthy lifestyle goals. Users can access workout programs, nutrition plans, and communication features with their trainers. The project focuses on acquiring knowledge and skills in web programming, creating web pages, establishing and utilizing a database, and developing a dynamic program.

## Project Overview
The FitLife platform comprises several key features:

1. **User Registration and Login:**
Users can register by providing basic personal information such as name, surname, date of birth, gender, email address, phone number, and profile photo.
Users can log in using their email and securely stored password. Authentication processes should be implemented.
A "forgot password" option is available for users to reset their passwords.


2. **User Profiles:**
Users can edit their personal profile pages, add profile pictures, and update basic information.
2.1.**User Roles:**
The application has three user roles: Admin, Trainer, and Trainee.
Admins have comprehensive access rights for general application management, including managing user accounts and data.
Trainers can edit their profile information, access information about assigned trainees, create and update exercise programs, and create and update nutrition plans.
Trainees can view and edit their profiles, access exercise and nutrition plans, communicate with trainers, add and manage progress records, and view visual reports.

2.2.**Trainer Profiles:**
Trainers can edit their profile information, including name, surname, specialization areas (weight gain, weight loss, weight maintenance, muscle gain), experiences, and contact details.
3.**Trainer-Trainee Relationship:**
After trainees enter their personal information and goals, the system will systematically match trainers based on their expertise and the number of trainees they can handle.
Each trainer can have a maximum of 5 trainees.


4.**Database Design:**
Utilize a cloud-based relational database service (e.g., Amazon RDS, Firebase, Microsoft Azure SQL).
Design tables such as 'User,' 'Trainer,' 'Workout Programs,' 'Nutrition Plans,' 'Messaging,' and 'User-Trainer Relationship.'
Establish relationships between tables using keys, ensuring normalization.

## Technologies Used
Programming Languages: Java Spring, React
Database: Cloud-based relational database service
Web Development: HTML, CSS, JavaScript

## Usage
1. **Installation:**
   - Clone the repository: 
git clone https://github.com/your-username/fitlife.git

 - Navigate to the project directory:
cd fitlife

2. **Running the Application:**
   -Start the application using your preferred web development environment.
3. **Accessing the Interface:**
  -The application should provide a user-friendly web interface for users, trainers, and admins.

## Important Notes

-Visualize the dynamic aspects of the program, such as progress reports and interactive communication.
-Regularly update the documentation to reflect any changes or improvements in the project.








