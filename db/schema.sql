CREATE TABLE post (
   id SERIAL PRIMARY KEY,
   name TEXT
);
CREATE TABLE candidate (
   id SERIAL PRIMARY KEY,
   name TEXT,
   photoId INT,
   cityId INT
);
CREATE TABLE photo (
   id SERIAL PRIMARY KEY,
   file TEXT
);
CREATE TABLE users (
   id SERIAL PRIMARY KEY,
   name TEXT,
   email TEXT,
   pass TEXT
);
CREATE TABLE cities (
   id SERIAL PRIMARY KEY,
   city TEXT
)