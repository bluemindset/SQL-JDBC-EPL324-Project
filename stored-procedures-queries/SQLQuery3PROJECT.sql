IF OBJECT_ID (N'dbo.countHorsesByAge', N'TF') IS NOT NULL
    DROP FUNCTION countHorsesByAge;
GO
IF OBJECT_ID (N'dbo.countHorsesByAge', N'P') IS NOT NULL
		DROP PROC countHorsesByAge;
GO
CREATE Function countHorsesByAge
(
@MIN_AGE INT,--0
@MAX_AGE INT--10000
)
returns @Data Table
(
   countHorse int,
   age int
)
 begin

--   if (@MAX_AGE = 10000)
--	set @MAX_AGE = (SELECT MAX(age)
--					FROM HORSE)
--else
--	set @MAX_AGE = (	select -1)

 IF @MIN_AGE=0 and @MAX_AGE=( SELECT MAX(age) FROM HORSE)-- no parameters
	begin
	Insert into @Data(countHorse, age)
		SELECT COUNT(*),[age]
		FROM HORSE 
		GROUP BY age
	end

	ELSE IF(@MAX_AGE= ( SELECT MAX(age) FROM HORSE))-- one parameter (horses grader than min_age)
	begin
	Insert into @Data(countHorse, age)
		SELECT COUNT(*),[age]
		FROM HORSE 
		WHERE age > @MIN_AGE
		GROUP BY age
	end
	ELSE 
	begin
	Insert into @Data(countHorse, age)
		SELECT COUNT(*), age		   	
		FROM HORSE 
		WHERE age BETWEEN @MIN_AGE AND @MAX_AGE
		GROUP BY age
	end
    RETURN
 END
GO
-- SELECT * FROM countHorsesByAge (0, 25);
-- GO
--SELECT * FROM countHorsesByAge (0,1000)
-- 1000 apla giaa ena max pu piani to integer 

--SELECT * FROM HORSE
