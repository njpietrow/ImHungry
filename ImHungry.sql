DROP DATABASE IF EXISTS ImHungry;

CREATE DATABASE ImHungry;
USE ImHungry;

DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS List;
DROP TABLE IF EXISTS ListRestaurants;
DROP TABLE IF EXISTS ListRecipes;
DROP TABLE IF EXISTS Restaurant;
DROP TABLE IF EXISTS Recipe;

CREATE TABLE User(
   username varchar(100) primary key not null,
   password varchar(100) not null
   );
   
CREATE TABLE List(
	list_id int(2) primary key not null,
    list_name varchar(10) not null 
);

CREATE TABLE Restaurant(
	restaurant_id varchar(100) primary key not null,
    restaurant_name varchar(100) not null 
);

CREATE TABLE Recipe(
	recipe_url varchar(100) primary key not null,
    recipe_name varchar(100) primary key not null 
);
   
CREATE TABLE ListRestaurants(
	restaurant_id varchar(100) primary key not null,
    username varchar(100) not null,
    list_id int(2) not null,
    list_no int(10) not null,
    foreign key (username) references User(username),
    foreign key (restaurant_id) references Restaurant(restaurant_id),
    foreign key (list_id) references List(list_id) 
    );
    
CREATE TABLE ListRecipes(
	recipe_url varchar(100) primary key not null,
    username varchar(100) not null,
    list_id int(2) not null,
    list_no int(10) not null,
    foreign key (username) references User(username),
    foreign key (recipe_url) references Recipe(recipe_url),
     foreign key (list_id) references List(list_id) 

	);
    
