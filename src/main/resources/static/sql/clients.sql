create table clients(
        id int primary key generated by default as identity,
        name varchar(30) NOT NULL,
        age int check ( age < 150 ),
        password varchar(100) NOT NULL,
        email varchar NOT NULL,
        drivingExperience int,
        role varchar(5) NOT NULL
)