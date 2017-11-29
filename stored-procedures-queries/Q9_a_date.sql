IF OBJECT_ID (N'Query9', N'P')IS NOT NULL  
    DROP PROCEDURE Query9_a_date;  
GO
CREATE PROCEDURE Query9_a_date


AS 
BEGIN

	SET NOCOUNT ON


 DECLARE @meeting_date_of_current_race date = '2010-01-29' ;
 DECLARE @time_of_current_race time = '19:00:00';
 DECLARE @I int  = 0 ; 
 DECLARE @eighthorse table(horse int);
 DECLARE @horset table(horse_id int, meeting_date date,distance int,end_pos int, pos1 int,pos2 int,pos3 int,all_pos int);
 DECLARE @HorseCursor CURSOR;
 DECLARE @HorseIt int;
 DECLARE @allpositions int;


SET NOCOUNT ON


/*GET THE 8 HORSES OF THE CURRENT RACE*/
INSERT INTO @eighthorse 
	SELECT  [PARTICIPATION].horse_id 
	FROM	[PARTICIPATION],RACE
	WHERE	[Race].[race_time] =[PARTICIPATION].race_time AND
			[Race].meeting_date=[PARTICIPATION].meeting_date AND [Race].meeting_date = @meeting_date_of_current_race 
			AND	[Race].race_time = @time_of_current_race 


 SET @HorseCursor = CURSOR FOR
    select top 8 horse from @eighthorse

	OPEN @HorseCursor 
    FETCH NEXT FROM @HorseCursor 
    INTO @HorseIt
	 Declare @x int;	
    WHILE @@FETCH_STATUS = 0
    BEGIN
		 
		 

	Insert into @horset(meeting_date,horse_id,end_pos,distance,pos1,pos2,pos3)
		SELECT   [PARTICIPATION].meeting_date , [PARTICIPATION].horse_id, [PARTICIPATION].end_pos,RACE.distance,

		
			SUM(CASE WHEN [PARTICIPATION].end_pos = 1 THEN 1 ELSE 0 END) AS countFirstPositions,
			SUM(CASE WHEN [PARTICIPATION].end_pos = 2 THEN 1 ELSE 0 END) AS countSecondPositions,
			SUM(CASE WHEN [PARTICIPATION].end_pos = 3 THEN 1 ELSE 0 END) AS countThirdPositions
		

		FROM  [PARTICIPATION],[RACE]
		
		WHERE [PARTICIPATION].horse_id = @HorseIt AND (([PARTICIPATION].meeting_date = @meeting_date_of_current_race AND
		[PARTICIPATION].race_time<=@time_of_current_race) OR( [PARTICIPATION].meeting_date < @meeting_date_of_current_race))
		AND  ( [PARTICIPATION].Race_time=[RACE].Race_time AND [PARTICIPATION].meeting_date= [RACE].meeting_date	)
		GROUP BY [PARTICIPATION].meeting_date , [PARTICIPATION].horse_id, [PARTICIPATION].end_pos,RACE.distance
		ORDER BY [PARTICIPATION].horse_id
		



		
	  FETCH NEXT FROM @HorseCursor 
      INTO @HorseIt 

	  
    END; 
	

    CLOSE @HorseCursor ;
    DEALLOCATE @HorseCursor;
	SELECT * FROM @horset

	DECLARE @PART  table(part int,horse_id int);
		



	SELECT COUNT( h.horse_id) AS all_part,horse_id
	FROM @horset h
	GROUP BY h.horse_id
	
	


	Declare @horse_idt int;
	Declare @pos1t int;
	Declare @pos2t int;
	Declare @pos3t int;
	Declare @xa int;
	Declare @c int;
	SET @xa= 1;
	SET @c = 0;
	Declare HorseCursor2  CURSOR FOR
    select horse_id,pos1,pos2,pos3 from @horset

	OPEN HorseCursor2 
    FETCH NEXT FROM HorseCursor2 
    INTO @horse_idt,@pos1t,@pos2t,@pos3t
	PRINT 'HORSE START'	
   SET @xa = @horse_idt;
    WHILE @@FETCH_STATUS = 0  
    BEGIN  


	  
		IF @xa = @horse_idt
			SET @c= @c+1
				
		ELSE
			BEGIN
				SET @c= 1
				PRINT 'HORSE START'
			END
			
			PRINT CONVERT(VARCHAR(10), @horse_idt) + '    '+CONVERT(varchar(10),@c)
			SET @xa = @horse_idt;
    
	FETCH NEXT FROM HorseCursor2 
    INTO @horse_idt,@pos1t,@pos2t,@pos3t
		
		end;
  
CLOSE HorseCursor2;  
DEALLOCATE HorseCursor2; 



	
	
	SELECT h.horse_id,h.meeting_date,h.distance,h.end_pos,h.pos1,h.pos2,h.pos3,p.part
	FROM @horset h ,@PART p
	where h.horse_id= p.horse_id

END
GO

EXECUTE Query9_a_date;