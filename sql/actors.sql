# Created appropriate indexes for fast lookups.

# Matching MovieID for Actors table.
UPDATE Actors, Ratings
SET Actors.MovieID = Ratings.MovieID
WHERE Actors.Title = Ratings.Title
AND Actors.Year = Ratings.Year;

SELECT COUNT(*) FROM Actors;

# Check the count for bad matches.
SELECT * FROM Actors WHERE MovieID IS NULL;
SELECT COUNT(*) FROM Actors WHERE MovieID IS NULL;

# Export unmatched records to file before deleting from table.
SELECT * FROM Actors
WHERE MovieID IS NULL
INTO OUTFILE '/var/lib/mysql-files/unmatched_actors.csv'
FIELDS TERMINATED BY '\t';

DELETE FROM Actors WHERE MovieID IS NULL;

ALTER TABLE `yamdb`.`Actors` 
ADD INDEX `ActorID` (`ActorID` ASC);
