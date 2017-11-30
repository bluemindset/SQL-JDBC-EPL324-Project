IF OBJECT_ID('selectHorsesByAge', 'P') IS NOT NULL
		DROP PROCEDURE selectHorsesByAge
GO
CREATE PROCEDURE selectHorsesByAge
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
	IF (@maxAge != 0)
		BEGIN
			SET @MAX_AGE = @max_age;
		END
		
	SELECT H.id, H.name, H.age
	FROM HORSE H
	WHERE H.age BETWEEN @MIN_AGE AND @MAX_AGE
	GROUP BY  H.id, H.name, H.age
	ORDER BY H.age
END
GO

EXEC selectHorsesByAge @minAge = 8, @maxAge = 0;