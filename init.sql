create table book (
                      isbn varchar(20),
                      title varchar(50),
                      status char(1) -- A..available, L..lent
);

insert into book (isbn, title, status)
values ('978-3-7068-4352-2', 'Systemplanung und Projektentwicklung', 'A'),
       ('978-3-446-45486-6', 'Programmieren Trainieren', 'L'),
       ('0-201-56545-5', 'The SQL Guide to ORACLE', 'A');

