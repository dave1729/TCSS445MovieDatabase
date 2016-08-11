#CREATE DATABASE yamdb;

USE yamdb;

# Generate table definitions.
CREATE TABLE `yamdb`.`Genres` (
	`Title` VARCHAR(200) NULL,
	`Year` YEAR NULL,
	`Genres` VARCHAR(20) NULL);

CREATE TABLE `yamdb`.`Ratings` (
	`Distribution` VARCHAR(10) NULL DEFAULT '..........',
	`Votes` INT NULL,
	`Rank` TINYINT NULL,
	`Title` VARCHAR(200) NULL,
	`Year` YEAR NULL,
	`Episode` VARCHAR(200) NULL);

CREATE TABLE `yamdb`.`ProductionCompanies` (
	`Title` VARCHAR(200) NULL,
	`Year` YEAR NULL,
	`ProductionCompany` VARCHAR(200) NULL);

CREATE TABLE `yamdb`.`Actors` (
  `ActorID` INT(11) NULL,
  `Name` VARCHAR(100) NULL,
  `Title` VARCHAR(200) NULL,
  `Year` YEAR NULL,
  `Gender` CHAR(1) NULL);

# Import actors and set the Gender attribute (update to absolute path here).
LOAD DATA LOCAL INFILE '/home/vaio/Actors.txt'
	INTO TABLE Actors;

# Import remaining data (update to absolute path here).
LOAD DATA LOCAL INFILE '/home/vaio/genres.csv' 
	INTO TABLE Genres IGNORE 1 LINES;

LOAD DATA LOCAL INFILE '/home/vaio/ratings.csv' 
	INTO TABLE Ratings IGNORE 1 LINES;

LOAD DATA LOCAL INFILE '/home/vaio/production-companies.tsv' 
	INTO TABLE ProductionCompanies IGNORE 3 LINES;


# Load data from local files into table (update to absolute path!).
#LOAD DATA LOCAL INFILE '/home/peter/summer2016/tcss445/project/genres.csv' 
#	INTO TABLE Genres IGNORE 1 LINES;

#LOAD DATA LOCAL INFILE '/home/peter/summer2016/tcss445/project/ratings.csv' 
#	INTO TABLE Ratings IGNORE 1 LINES;

#LOAD DATA LOCAL INFILE '/home/peter/summer2016/tcss445/project/production-companies.tsv' 
#	INTO TABLE ProductionCompanies IGNORE 3 LINES;

# Create movie lookup table and insert unique pairs.
CREATE TABLE Movies(
	Title VARCHAR(200) NOT NULL,
	Year YEAR(4) NOT NULL,
	UNIQUE KEY (Title, Year)
);

INSERT INTO Movies (
	SELECT DISTINCT Title, Year
	FROM Genres 
	GROUP BY Title, Year
);

ALTER TABLE `yamdb`.`Movies` 
ADD COLUMN `MovieID` INT NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`MovieID`);

# Prepare Genres table for FK matching from Movies table.
ALTER TABLE `yamdb`.`Genres` 
ADD COLUMN `fk_MovieID` INT NULL FIRST;

# Prepare Ratings table for FK matching from Movies table.
ALTER TABLE `yamdb`.`Ratings` 
ADD COLUMN `fk_MovieID` INT NULL FIRST;

# Prepare ProductionCompanies table for FK matching from Movies table.
ALTER TABLE `yamdb`.`ProductionCompanies` 
ADD COLUMN `fk_MovieID` INT NULL FIRST;

UPDATE Genres, Movies
SET Genres.fk_MovieID = Movies.MovieID
WHERE Genres.Title = Movies.Title
AND Genres.Year = Movies.Year;

UPDATE Ratings, Movies
SET Ratings.fk_MovieID = Movies.MovieID
WHERE Ratings.Title = Movies.Title
AND Ratings.Year = Movies.Year;

UPDATE ProductionCompanies, Movies
SET ProductionCompanies.fk_MovieID = Movies.MovieID
WHERE ProductionCompanies.Title = Movies.Title
AND ProductionCompanies.Year = Movies.Year;

UPDATE Actors, Movies
SET Actors.fk_MovieID = Movies.MovieID
WHERE Actors.Title = Movies.Title
AND Actors.Year = Movies.Year;

# Delete non-matching data from all tables.
DELETE FROM Genres WHERE fk_MovieID IS NULL;
DELETE FROM Ratings WHERE fk_MovieID IS NULL;
DELETE FROM ProductionCompanies WHERE fk_MovieID IS NULL;
DELETE FROM Actors WHERE fk_MovieID IS NULL;

# Delete tuples containing episode data (not needed).
DELETE FROM Ratings
WHERE Episode IS NOT NULL;

# Alter Ratings table for primary.
ALTER TABLE `yamdb`.`Ratings` 
DROP COLUMN `Episode`,
CHANGE COLUMN `fk_MovieID` `MovieID` INT(11) NOT NULL AUTO_INCREMENT,
ADD PRIMARY KEY (`MovieID`);

# Ratings duplicates tests
SELECT fk_MovieID, Title, COUNT(*)
FROM Ratings
GROUP BY fk_MovieID, Title
HAVING COUNT(*) > 1;

SELECT * 
FROM Ratings
WHERE Episode IS NOT NULL 
INTO OUTFILE '/var/lib/mysql-files/ratings_episodes_list.csv' 
FIELDS TERMINATED BY '\t';

# Waiting on Dave and Vlad for Actors and Ratings dupes for appending and updating