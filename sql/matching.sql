# Matching MovieID for Actors table.
EXPLAIN UPDATE Actors, Ratings
SET Actors.MovieID = Ratings.MovieID
WHERE Actors.Title = Ratings.Title
AND Actors.Year = Ratings.Year;

# Query for matching after adding indexes.
INSERT INTO ProductionCompanies
SELECT Ratings.MovieID, Ratings.Title, Ratings.Year, ProductionCompaniesOriginal.ProductionCompany
FROM ProductionCompaniesOriginal, Ratings
WHERE ProductionCompaniesOriginal.Title = Ratings.Title AND ProductionCompaniesOriginal.Year = Ratings.Year;

# Match query #2
EXPLAIN UPDATE Genres, Ratings
SET Genres.fk_MovieID = Ratings.MovieID
WHERE Genres.Title = Ratings.Title
AND Genres.Year = Ratings.Year;

# Get dupes from Ratings table and insert into secondary table.
INSERT INTO RatingDupesFull (
SELECT * FROM Ratings
WHERE Ratings.fk_MovieID
IN (SELECT * FROM RatingDupes)
ORDER BY fk_MovieID ASC);