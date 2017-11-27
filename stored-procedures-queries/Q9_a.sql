/*CREATE PROCEDURE Query9




AS 

BEGIN 
*/



 DECLARE	@meeting_date_of_current_race date = '2010-01-29' ;
 DECLARE @time_of_current_race time = '20:00:00';
 DECLARE  @I int  = 0 ; 
 DECLARE @eighthorse table(horse int);
 DECLARE @HorseCursor CURSOR;
 DECLARE @HorseIt int;
	SET NOCOUNT ON
	
/*Get horse from the current Ipodromia

WHILE (SELECT /*HORSE*/ FROM /*HORSE*/)
SET @TimesFirstpos = (Select top 1 ParLngId from T_Param where ParStrNom = 'Extranet Client')
SET @TimesSecondpos = (Select top 1 ParLngId from T_Param where ParStrNom = 'Extranet Client')
SET @TimesThirdpos = (Select top 1 ParLngId from T_Param where ParStrNom = 'Extranet Client')

*/

/*

SELECT * FROM  [PARTICIPATION]
WHERE horse_id  IN (SELECT horse_id FROM [PARTICIPATION]
GROUP BY horse_id HAVING COUNT(*) > 1)

*/



/*GET THE 8 HORSES OF THE CURRENT RACE*/
INSERT INTO @eighthorse 
	SELECT  [PARTICIPATION].horse_id 
	FROM  [PARTICIPATION],RACE
	WHERE	[Race].[race_time] =[PARTICIPATION].race_time AND
			[Race].meeting_date=[PARTICIPATION].meeting_date AND [Race].meeting_date = @meeting_date_of_current_race 
			AND	[Race].race_time = @time_of_current_race 



 SET @HorseCursor = CURSOR FOR
    select top 8 horse from @eighthorse

	OPEN @HorseCursor 
    FETCH NEXT FROM @HorseCursor 
    INTO @HorseIt

    WHILE @@FETCH_STATUS = 0
    BEGIN
      
		SELECT  *
		FROM  [PARTICIPATION]
		
		WHERE [PARTICIPATION].horse_id = @HorseIt AND 
		
		CASE WHEN [PARTICIPATION].meeting_date = @meeting_date_of_current_race THEN 
			'1'
		
		ELSE
			'2'

      FETCH NEXT FROM @HorseCursor 
      INTO @HorseIt 
    END; 

    CLOSE @HorseCursor ;
    DEALLOCATE @HorseCursor;

	/*

WHILE @I <7
BEGIN
	
	SET @I = @I + 1;

END;

 
select *
from @eighthorse
For EVERY OF THE 8 HORSES get the all THE PREVIOUS RACES 


SELECT *
FROM PARTICIPATION 
WHERE horse_id = 100 and meeting_date <  '2010-01-29' 


*/
