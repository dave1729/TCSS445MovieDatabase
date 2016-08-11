# Trim white spaces around Title fields.
UPDATE Genres SET Title = TRIM(Title);
UPDATE ProductionCompanies SET Title = TRIM(Title);
UPDATE Ratings SET Title = TRIM(Title);
UPDATE Actors SET Title = TRIM(Title);

# Trim quotations around Title fields.
UPDATE Genres SET Title = TRIM(BOTH '"' FROM Title);
UPDATE ProductionCompanies SET Title = TRIM(BOTH '"' FROM Title);
UPDATE Ratings SET Title = TRIM(BOTH '"' FROM Title);
UPDATE Actors SET Title = TRIM(BOTH '"' FROM Title);