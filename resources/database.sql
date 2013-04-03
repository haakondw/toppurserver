connect 'jdbc:derby://localhost:1527/toppurbase;user=user;password=pass';
CREATE TABLE app_user (user_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
					username VARCHAR(20) NOT NULL,
					password VARCHAR(20) NOT NULL,
					firstname VARCHAR(30),
					lastname VARCHAR(30),
					role VARCHAR(20) NOT NULL);

CREATE TABLE role (role VARCHAR(20) NOT NULL PRIMARY KEY);

CREATE TABLE department (department_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
						department_name VARCHAR(50) NOT NULL);

CREATE TABLE patient (patient_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), 
						firstname VARCHAR(50)NOT NULL,
						lastname VARCHAR(50)NOT NULL,
						social_security_number VARCHAR(11) NOT NULL,
						picture blob(16M),
						department_id INT);

CREATE TABLE emergency_contact (emergency_contact_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
								firstname VARCHAR(50) NOT NULL,
								lastname VARCHAR(50) NOT NULL,
								phone_number VARCHAR(15) NOT NULL,
								patient_id INT NOT NULL);

CREATE TABLE task (task_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
					timestamp TIMESTAMP NOT NULL,
					dosage VARCHAR(20) NOT NULL,
					executed SMALLINT NOT NULL,
					medicine_id INT NOT NULL,
					medicine_form VARCHAR(20));

CREATE TABLE medicine(medicine_id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
						name VARCHAR(50));

CREATE TABLE medicine_form(form VARCHAR(20) NOT NULL PRIMARY KEY);

CREATE TABLE deviation(deviation_id INT PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
						description VARCHAR(200) NOT NULL,
						timestamp TIMESTAMP NOT NULL,
						patient_id INT NOT NULL);

ALTER TABLE app_user ADD CONSTRAINT fk_role FOREIGN KEY (role) REFERENCES role ON DELETE CASCADE;
ALTER TABLE patient ADD CONSTRAINT fk_department_id FOREIGN KEY (department_id) REFERENCES department ON DELETE SET NULL;
ALTER TABLE emergency_contact ADD CONSTRAINT fk_patient_id FOREIGN KEY (patient_id) REFERENCES patient ON DELETE CASCADE;
ALTER TABLE task ADD CONSTRAINT fk_medicine_id FOREIGN KEY (medicine_id) REFERENCES medicine ON DELETE CASCADE;
ALTER TABLE task ADD CONSTRAINT fk_medicine_form FOREIGN KEY (medicine_form) REFERENCES medicine_form ON DELETE SET NULL;
ALTER TABLE deviation ADD CONSTRAINT fk2_patient_id FOREIGN KEY (patient_id) REFERENCES patient ON DELETE CASCADE;