use hibernate_lesson_2;

delete from CustomerProduct where true;
delete from Product where true;
delete from Customer where true;

INSERT INTO Customer (name)
VALUES ('Alex'), ('Petya');

INSERT INTO Product (name, price)
VALUES ('Bread', 30), ('Milk', 70), ('Cake', 150);
