-- Create Role table
CREATE TABLE role (
  role_id INT PRIMARY KEY AUTO_INCREMENT,
  role_name VARCHAR(100) NOT NULL,
  flag BOOLEAN NOT NULL
);

-- Create User table
CREATE TABLE users (
  user_id INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(100) NOT NULL,
  middle_name VARCHAR(100),
  last_name VARCHAR(100) NOT NULL,
  role_id INT,
  flag BOOLEAN NOT NULL,
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
  FOREIGN KEY (assignee_id) REFERENCES users(user_id),
  FOREIGN KEY (report_to_id) REFERENCES users(user_id)
);