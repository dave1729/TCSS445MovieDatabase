# Purpose: Get an unfiltered random movie.
# Description: A direct query for a random record in MySQL takes
# ~1.25 seconds. Getting the record count takes ~0.11 seconds
# Both generating a random number and querying for a random row 
# is instantaneous.

# 1) Query the Ratings table for the number of records
SELECT COUNT(*) FROM Ratings;

# 2) Use returned value as upper limit for Random object's nextInt() method.
# 	 java snippet:
#	 import java.util.Random;
#	 private int r = (new Random()).nextInt(1, value+1); // last value is exclusive so need +1

# 3) Query the Ratings table again for random row number:
SELECT * FROM Ratings ORDER BY MovieID ASC LIMIT 9999, 1; # Gets number+1'th record (replace 9999 with variable)