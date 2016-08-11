


LOAD DATA LOCAL INFILE '/home/peter/summer2016/tcss445/project/genres.csv' 
	INTO TABLE Genres IGNORE 1 LINES;

LOAD DATA LOCAL INFILE '/home/peter/summer2016/tcss445/project/ratings.csv' 
	INTO TABLE Ratings IGNORE 1 LINES;

LOAD DATA LOCAL INFILE '/home/peter/summer2016/tcss445/project/production-companies.tsv' 
	INTO TABLE ProductionCompanies IGNORE 3 LINES;
