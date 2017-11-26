CREATE PROCEDURE countHorsesByAge
--If max age is 0 maxAge is not taken into consideration.
--(Horses that are shown are from @minAge to MAX_HORSE_AGE
@minAge AS INTEGER, @maxAge	AS INTEGER
AS 
BEGIN 
	DECLARE @MIN_AGE INTEGER;
	DECLARE @MAX_AGE INTEGER;
	SET @MIN_AGE = @minAge;
	SET @MAX_AGE = (SELECT MAX(H.age) AS oldestHorse
				    FROM HORSE H);
	IF @maxAge != 0
		SET @MAX_AGE = @max_age;
		
	SELECT COUNT(*) countHorsesByAge		   	
	FROM HORSE H
	WHERE H.age BETWEEN @minAge AND @maxAge
	GROUP BY H.age
END;