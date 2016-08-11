
SHOW processlist;

SELECT * FROM Ratings WHERE Year = '1999';


SELECT * FROM Ratings
INNER JOIN Actors ON Ratings.MovieID = Actors.MovieID
WHERE Ratings.MovieID = 145778;

SELECT * FROM Actors 
INNER JOIN Ratings
ON Actors.MovieID = Ratings.MovieID
WHERE Actors.MovieID = 13710
ORDER BY Name ASC LIMIT 100;

SELECT * FROM Ratings WHERE Title = 'Friends';

SELECT * FROM Actors WHERE Name LIKE '%,%,%';
SELECT * FROM Actors
WHERE MovieID IS NULL
INTO OUTFILE '/var/lib/mysql-files/unmatched_actors.csv'
FIELDS TERMINATED BY '\t';