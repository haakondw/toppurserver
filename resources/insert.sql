connect 'jdbc:derby://localhost:1527/toppurbase;user=user;password=pass';
INSERT INTO department(name) VALUES(Avdeling A);
INSERT INTO department(name) VALUES(Avdeling B);
INSERT INTO department(name) VALUES(Avdeling C);

INSERT INTO pasient(firstname, lastname, social_security_number, department_id)
VALUES('Reidar', 'Hansen', 31013394849, 1);

INSERT INTO pasient(firstname, lastname, social_security_number)
VALUES('Jorun', 'Loe', 22112974449, 1);

INSERT INTO pasient(firstname, lastname, social_security_number)
VALUES('Bjørg', 'Olsen', 10053624748, 1);


INSERT INTO medicine(name) VALUES (Ibux);
INSERT INTO medicine(name) VALUES (Livostin);

INSERT INTO medicine_form(form) VALUES ('Flytende');
INSERT INTO medicine_form(form) VALUES ('Tablet');


INSERT INTO task(timestamp, dosage, executed, pasient_id, form, medicine_id)
VALUES(current_timestamp, '50 ml' 0, 0, 'Flytende', 1);

INSERT INTO task(timestamp, dosage, executed, pasient_id, form, medicine_id)
VALUES(current_timestamp, '50 ml' 0, 0, 'Flytende', 1);

INSERT INTO task(timestamp, dosage, executed, pasient_id, form, medicine_id)
VALUES(current_timestamp, '100 mg' 0, 1, 'Tablet', 0);

INSERT INTO task(timestamp, dosage, executed, pasient_id, form, medicine_id)
VALUES(current_timestamp, '500 mg' 0, 1, 'Tablet', 0);

INSERT INTO task(timestamp, dosage, executed, pasient_id, form, medicine_id)
VALUES(current_timestamp, '25 ml' 0, 2, 'Flytende', 1);

INSERT INTO task(timestamp, dosage, executed, pasient_id, form, medicine_id)
VALUES(current_timestamp, '200 mg' 0, 0, 'Tablet', 0);


INSERT INTO deviation(description, timestamp, pasient_id)
VALUES('Har falt', current_timestamp, 1);

INSERT INTO deviation(description, timestamp, pasient_id)
VALUES('Har falt', current_timestamp, 2);

INSERT INTO deviation(description, timestamp, pasient_id)
VALUES('Har falt', current_timestamp, 0);

