IF OBJECT_ID (N'Query9_b_Date', N'P')IS NOT NULL  
    DROP PROCEDURE Query9_b_Date;  
GO
CREATE PROCEDURE Query9_b_Date  @meeting_date_of_current_race date, @time_of_current_race time

AS 
BEGIN

	SET NOCOUNT ON

 DECLARE @I int  = 0 ; 
 DECLARE @eighthorse table(horse int);
DECLARE @horset table(horse_id int,trainer_id int, meeting_date date,race_time time,distance int,end_pos int,name varchar(20),pos1 int,pos2 int,pos3 int);
DECLARE @horset2 table(horse_id int,trainer_id int, meeting_date date,race_time time,distance int,end_pos int,name varchar(20), pos1 int,pos2 int,pos3 int,all_pos int,perc money);

 Declare @race_time time;
 DECLARE @HorseCursor CURSOR;
 DECLARE @TrainerIt int;
 DECLARE @allpositions int;


SET NOCOUNT ON


/*GET THE 8 HORSES OF THE CURRENT RACE*/
INSERT INTO @eighthorse 
	SELECT  [PARTICIPATION].trainer_id 
	FROM	[PARTICIPATION] ,RACE 
	WHERE	[Race].[race_time] =[PARTICIPATION].race_time AND
			[Race].meeting_date=[PARTICIPATION].meeting_date AND [Race].meeting_date = @meeting_date_of_current_race 
			AND	[Race].race_time = @time_of_current_race 
	

 SET @HorseCursor = CURSOR FOR
    select top 8 horse from @eighthorse h
	
	OPEN @HorseCursor 
    FETCH NEXT FROM @HorseCursor 
    INTO @TrainerIt
	 Declare @x int;	
    WHILE @@FETCH_STATUS = 0
    BEGIN
		 
		 

	Insert into @horset(meeting_date,horse_id,trainer_id,race_time,end_pos,distance,name,pos1,pos2,pos3)
		SELECT   [PARTICIPATION].meeting_date ,[PARTICIPATION].horse_id,PARTICIPATION.trainer_id, [Race].Race_time,  [PARTICIPATION].end_pos , RACE.distance, HORSE.name	,

		
			SUM(CASE WHEN [PARTICIPATION].end_pos = 1 THEN 1 ELSE 0 END) AS countFirstPositions,
			SUM(CASE WHEN [PARTICIPATION].end_pos = 2 THEN 1 ELSE 0 END) AS countSecondPositions,
			SUM(CASE WHEN [PARTICIPATION].end_pos = 3 THEN 1 ELSE 0 END) AS countThirdPositions
		

		FROM  [PARTICIPATION],[RACE],HORSE
		
		WHERE [PARTICIPATION].trainer_id = @TrainerIt AND (([PARTICIPATION].meeting_date = @meeting_date_of_current_race AND
		[PARTICIPATION].race_time<=@time_of_current_race) OR( [PARTICIPATION].meeting_date < @meeting_date_of_current_race)) 
		AND  ( [PARTICIPATION].Race_time=[RACE].Race_time AND [PARTICIPATION].meeting_date= [RACE].meeting_date	)AND [PARTICIPATION].horse_id = HORSE.ID
		GROUP BY [PARTICIPATION].meeting_date , [PARTICIPATION].horse_id, [PARTICIPATION].end_pos,RACE.distance,[Race].race_time,HORSE.name,PARTICIPATION.trainer_id
		ORDER BY [PARTICIPATION].horse_id ASC ,[PARTICIPATION].meeting_date ASC,[Race].race_time ASC
		



		
	  FETCH NEXT FROM @HorseCursor 
      INTO @TrainerIt 

	  
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
	Declare @race_timet time;
	Declare @end_post int;
	Declare @all_post int;
	Declare @perc money;
	Declare @same_trainer int;
	Declare @c float;

Declare @trainer_idt int;
Declare @namet varchar(25);
	SET @pos1tf =0 ;
	SET @pos2tf =0 ;
	SET @pos3tf = 0;
	SET @c = 0;
	SET @perc = 0;


		Declare HorseCursor2  CURSOR FOR
    select DISTINCT *  from @horset h
	ORDER BY h.trainer_id ASC , h.meeting_date ASC ,h.race_time ASC


	OPEN HorseCursor2 
    FETCH NEXT FROM HorseCursor2 
    INTO @horse_idt,@trainer_idt,@meeting_datet,@race_timet ,@distancet,@end_post,@namet,@pos1t,@pos2t,@pos3t
	

   SET @same_trainer = @trainer_idt;
 
    WHILE @@FETCH_STATUS = 0  
    BEGIN  
	  
		IF @same_trainer = @trainer_idt
			BEGIN
			SET @c= @c+1
			
			  IF @pos1t = 1 SET @pos1tf =@pos1tf+1
			   ELSE IF @pos2t = 1 SET @pos2tf=@pos2tf+1
				ELSE IF  @pos3t = 1 SET @pos3tf=@pos3tf+1
			 
			END
		ELSE
			BEGIN
				Insert INTO @horset2 values(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

			SET @c= 1
			SET @pos1tf=0 
			SET @pos2tf=0
			SET @pos3tf=0
			 IF @pos1t = 1 SET @pos1tf =@pos1tf+1
			   ELSE IF @pos2t = 1 SET @pos2tf=@pos2tf+1
				ELSE IF  @pos3t = 1 SET @pos3tf=@pos3tf+1

				end

			SET @perc =@pos1tf/@c
			Insert INTO @horset2
			SELECT @horse_idt ,@trainer_idt, @meeting_datet,@race_timet ,@distancet ,@end_post,@namet, @pos1tf ,@pos2tf,@pos3tf,@c ,@perc
			SET @same_trainer = @trainer_idt;
  
	FETCH NEXT FROM HorseCursor2 
        INTO @horse_idt,@trainer_idt,@meeting_datet,@race_timet ,@distancet,@end_post,@namet,@pos1t,@pos2t,@pos3t
		


		end;
  
CLOSE HorseCursor2;  
DEALLOCATE HorseCursor2; 
	

	Select   * from @horset2 h
	--ORDER BY h.trainer_id ASC , h.meeting_date ASC

END


