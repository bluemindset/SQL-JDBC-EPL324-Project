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
 DECLARE @horset table(horse_id int, meeting_date date,distance int,end_pos int, pos1 int,pos2 int,pos3 int);
  DECLARE @horset2 table(horse_id int, meeting_date date,distance int,end_pos int, pos1 int,pos2 int,pos3 int,all_pos int,perc money);


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
	

	Declare @horse_idt int;
	Declare @pos1t int;
	Declare @pos2t int;
	Declare @pos3t int;
	Declare @pos1tf int;
	Declare @pos2tf int;
	Declare @pos3tf int;
	Declare @meeting_datet date;
	Declare @distancet int;
	Declare @end_post int;
	Declare @all_post int;
	Declare @perc money;
	Declare @same_horse int;
	Declare @c float;

	SET @pos1tf =0 ;
	SET @pos2tf =0 ;
	SET @pos3tf = 0;
	SET @c = 0;
	SET @perc = 0;
	Declare HorseCursor2  CURSOR FOR
    select * from @horset

	OPEN HorseCursor2 
    FETCH NEXT FROM HorseCursor2 
    INTO @horse_idt,@meeting_datet,@distancet,@end_post,@pos1t,@pos2t,@pos3t
		
   SET @same_horse = @horse_idt;
 
    WHILE @@FETCH_STATUS = 0  
    BEGIN  
	  
		IF @same_horse = @horse_idt
			BEGIN
			SET @c= @c+1
			
			  IF @pos1t = 1 SET @pos1tf =@pos1tf+1
			   ELSE IF @pos2t = 1 SET @pos2tf=@pos2tf+1
				ELSE IF  @pos3t = 1 SET @pos3tf=@pos3tf+1
			 
			END
		ELSE
			BEGIN
			SET @c= 1
			SET @pos1tf=0 
			SET @pos2tf=0
			SET @pos3tf=0
			 IF @pos1t = 1 SET @pos1tf =@pos1tf+1
			   ELSE IF @pos2t = 1 SET @pos2tf=@pos2tf+1
				ELSE IF  @pos3t = 1 SET @pos3tf=@pos3tf+1

			
			END
			SET @perc =@pos1tf/@c
			
			Insert INTO @horset2
			SELECT @horse_idt , @meeting_datet ,@distancet ,@end_post, @pos1tf ,@pos2tf,@pos3tf
			,@c ,@perc

			SET @same_horse = @horse_idt;
    
	FETCH NEXT FROM HorseCursor2 
    INTO @horse_idt,@meeting_datet,@distancet,@end_post,@pos1t,@pos2t,@pos3t
		
		end;
  
CLOSE HorseCursor2;  
DEALLOCATE HorseCursor2; 
	

	Select   * from @horset2 h
	


END
GO

EXECUTE Query9_a_date;