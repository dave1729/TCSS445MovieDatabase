#SELECT MovieID, Title, Year, Genre, COUNT(*)
#FROM Genres
#GROUP BY MovieID, Title, Year, Genre
#HAVING COUNT(*) > 1;

DELETE FROM ProductionCompanies
WHERE MovieID IN (SELECT MovieID FROM Temp);

INSERT INTO ProductionCompanies
SELECT * FROM Temp;

DROP TABLE Temp;