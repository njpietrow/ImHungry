DROP DATABASE IF EXISTS ImHungry;

SET GLOBAL time_zone = '-7:00';

CREATE DATABASE ImHungry;
USE ImHungry;

DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS List;
DROP TABLE IF EXISTS ListRestaurants;
DROP TABLE IF EXISTS ListRecipes;
DROP TABLE IF EXISTS Restaurant;
DROP TABLE IF EXISTS Recipe;
DROP TABLE IF EXISTS QuickAccess;
DROP TABLE IF EXISTS Groceries;

CREATE TABLE User(
   username varchar(100) primary key not null,
   password bigint(100) not null,
   list_size int(10) not null
   );
   
CREATE TABLE QuickAccess(
   queryID int (10) primary key auto_increment not null,
   username varchar(100) not null,
   keyword varchar(100) not null,
   num_results int(4) not null,
   foreign key (username) references User(username)
   );
   
CREATE TABLE List(
	list_id int(2) primary key not null,
    list_name varchar(15) not null 
);

CREATE TABLE Restaurant(
	restaurant_id varchar(100) primary key not null,
    restaurant_name varchar(100) not null 
);

CREATE TABLE Recipe(
	recipe_url varchar(100) primary key not null,
    recipe_name varchar(100) not null 
);
   
CREATE TABLE ListRestaurants(
	entry_no int(10) primary key auto_increment,
	restaurant_id varchar(100) not null,
    username varchar(100) not null,
    list_id int(2) not null,
    list_no int(10) not null,
    foreign key (username) references User(username),
    foreign key (restaurant_id) references Restaurant(restaurant_id),
    foreign key (list_id) references List(list_id) 
    );
    
CREATE TABLE ListRecipes(
	entry_no int(10) primary key auto_increment,
	recipe_url varchar(100) not null,
    username varchar(100) not null,
    list_id int(2) not null,
    list_no int(10) not null,
    foreign key (username) references User(username),
    foreign key (recipe_url) references Recipe(recipe_url),
     foreign key (list_id) references List(list_id) 

	);
    
CREATE TABLE Groceries(
   groceryID int (10) primary key auto_increment not null,
   username varchar(100) not null,
   ingredient varchar(10000) not null,
   recipe_url varchar(100) not null,
   foreign key (username) references User(username),
   foreign key (recipe_url) references Recipe(recipe_url)
   );
   
	

    
INSERT into User(username,password,list_size) values ("GJHalfond",6102,0);
INSERT into User(username,password,list_size) values ("KCalaway",30943,0);
Insert into List(list_id,list_name) values (0,"Favorites");
Insert into List(list_id,list_name) values (1,"To Explore");
Insert into List(list_id,list_name) values (2,"Do Not Show");
Insert into List(list_id,list_name) values (3,"Quick Access");

Select * from Recipe;
SELECT * FROM ListRecipes l2, Recipe r where username="GJHalfond" AND l2.recipe_url = r.recipe_url;
SELECT * From ListRestaurants;
Select * From QuickAccess;
Select * From Groceries;

Select * from restaurant;
#Select * from User;
#SELECT * FROM User WHERE username = "GJHalfond" AND password = "Scrum";