connect 'jdbc:derby://localhost:1527/toppurbase;user=user;password=pass';

INSERT INTO department(department_name) VALUES('Avdeling A');
INSERT INTO department(department_name) VALUES('Avdeling B');
INSERT INTO department(department_name) VALUES('Avdeling C');
INSERT INTO department(department_name) VALUES('Avdeling D');
INSERT INTO department(department_name) VALUES('Avdeling E');

INSERT INTO patient(firstname, lastname, social_security_number, department_id)
VALUES('Reidar', 'Hansen', '31013394849', 1);

INSERT INTO patient(firstname, lastname, social_security_number, department_id)
VALUES('Jorun', 'Loe', '22112974449', 2);

INSERT INTO patient(firstname, lastname, social_security_number, department_id)
VALUES('Bjørg', 'Olsen', '10053624748', 3);




INSERT INTO medicine(name) VALUES ('Ibux');
INSERT INTO medicine(name) VALUES ('Livostin');

INSERT INTO medicine_form(form) VALUES ('Flytende');
INSERT INTO medicine_form(form) VALUES ('Tablet');


INSERT INTO task(timestamp, dosage, executed, patient_id, medicine_form, medicine_id)
VALUES(current_timestamp, '50 ml', 0, 1, 'Flytende', 1);

INSERT INTO task(timestamp, dosage, executed, patient_id, medicine_form, medicine_id)
VALUES(current_timestamp, '50 ml', 0, 1, 'Flytende', 1);

INSERT INTO task(timestamp, dosage, executed, patient_id, medicine_form, medicine_id)
VALUES(current_timestamp, '100 mg', 0, 2, 'Tablet', 2);

INSERT INTO task(timestamp, dosage, executed, patient_id, medicine_form, medicine_id)
VALUES(current_timestamp, '500 mg', 0, 2, 'Tablet', 2);

INSERT INTO task(timestamp, dosage, executed, patient_id, medicine_form, medicine_id)
VALUES(current_timestamp, '25 ml', 0, 3, 'Flytende', 1);

INSERT INTO task(timestamp, dosage, executed, patient_id, medicine_form, medicine_id)
VALUES(current_timestamp, '200 mg', 0, 3, 'Tablet', 2);


INSERT INTO deviation(description, timestamp, patient_id)
VALUES('Har falt', current_timestamp, 1);

INSERT INTO deviation(description, timestamp, patient_id)
VALUES('Har falt', current_timestamp, 2);

exit;