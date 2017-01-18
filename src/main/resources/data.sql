-- This SQL file will automatically be picked up by Spring Boot and execute the SQL to populate the in-memory database on startup
-- TODO :: find out how to run these scripts only under the 'dev' environment, as you can do with @Profile
INSERT INTO AUTHOR (ID, AGE, PSEUDONYM, NAME, SURNAME, VERSION) VALUES
(1, 34, 'Robert Galbraith', 'Joanne', 'Rowling', 1);