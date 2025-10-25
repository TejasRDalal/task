-- Create Role table
CREATE TABLE role (
  role_id INT PRIMARY KEY AUTO_INCREMENT,
  role_name VARCHAR(100) NOT NULL,
  flag BOOLEAN NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create User table
CREATE TABLE users (
  user_id INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(100) NOT NULL,
  middle_name VARCHAR(100),
  last_name VARCHAR(100) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  role_id INT,
  flag BOOLEAN NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (role_id) REFERENCES role(role_id)
);

-- Create Task table
CREATE TABLE task (
  task_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(500),
  start_date DATE,
  end_date DATE,
  assignee_id INT,
  report_to_id INT,
  flag BOOLEAN NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (assignee_id) REFERENCES users(user_id),
  FOREIGN KEY (report_to_id) REFERENCES users(user_id)
);

-- V2__create_auth_credentials_table.sql
CREATE TABLE auth_credentials (
  auth_id INT PRIMARY KEY AUTO_INCREMENT,
  userId INT NOT NULL,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL,
  flag BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT uq_auth_credentials_username UNIQUE (username),
  CONSTRAINT fk_auth_credentials_user FOREIGN KEY (userId) REFERENCES users(user_id)
);