IF OBJECT_ID('countHorsesByAgeProc', 'P') IS NOT NULL
		DROP PROCEDURE countHorsesByAgeProc;
GO
CREATE PROCEDURE countHorsesByAgeProc
--If max age is 0 maxAge is not taken into consideration and
--(Horses that are shown are from @minAge to MAX_HORSE_AGE
@minAge  AS INTEGER , @maxAge	AS INTEGER
AS
BEGIN
	SELECT H.age, COUNT(*) countHorsesByAge
	FROM HORSE H
	WHERE H.age BETWEEN @minAge AND @maxAge
	GROUP BY H.age
	ORDER BY H.age;
END;
GO
EXEC countHorsesByAgeProc @minAge = 9, @maxAge =  9;

