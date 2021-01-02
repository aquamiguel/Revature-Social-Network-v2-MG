create table users(
user_id serial primary key,
email varchar(30) unique not null,
password varchar(20) not null,
first_name varchar (20),
last_name varchar(20),
number_posts int,
birth_date date
);

create table posts(
post_id serial,
user_id int not null,
count_likes int,
post_text varchar (140),
post_date date,
post_media_url varchar(100),
foreign key (user_id) references users(user_id) on delete cascade
);

create table profile(
profile_id serial,
user_id int unique not null,
about_me varchar(140),
age int,
city varchar(30),
profession varchar(40),
favorite_planet varchar(30),
profile_picture varchar(100),
foreign key (user_id) references users(user_id) on delete cascade
);
