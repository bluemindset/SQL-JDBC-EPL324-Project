IF OBJECT_ID (N'Query9_a_allHorses', N'P')IS NOT NULL  
    DROP PROCEDURE Query9_a_allHorses;  

GO
CREATE PROCEDURE Query9_a_allHorses


AS 
BEGIN

	SET NOCOUNT ON


DECLARE @horset table(horse_id int, meeting_date date,race_time time,distance int,end_pos int);
DECLARE @horset2 table(horse_id int, meeting_date date,race_time time,distance int,end_pos int, pos1 int,pos2 int,pos3 int,all_pos int,perc money);
DECLARE @HorseCursor CURSOR;
DECLARE @HorseIt int;
DECLARE @allpositions int;
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
Declare @race_timet time;

SET NOCOUNT ON


/*GET ALL HORSES OF  ALL RACES*/
INSERT INTO @horset
	SELECT  P.horse_id,P.meeting_date,P.race_time,R.distance,P.end_pos
	FROM	[PARTICIPATION]  P, [RACE] R
	WHERE	P.meeting_date = R.meeting_date AND  P.race_time = R.race_time
	ORDER BY P.horse_id ASC,R.meeting_date ASC ,P.race_time ASC 

	

	SET @pos1tf =0 ;
	SET @pos2tf =0 ;
	SET @pos3tf = 0;
	SET @c = 0;
	SET @perc = 0;
	Declare HorseCursor2  CURSOR FOR
    select DISTINCT * from @horset h
	 ORDER BY h.horse_id ASC
	OPEN HorseCursor2 
    FETCH NEXT FROM HorseCursor2 
    INTO @horse_idt,@meeting_datet,@race_timet,@distancet,@end_post

   SET @same_horse = @horse_idt;
 
    WHILE @@FETCH_STATUS = 0  
    BEGIN  
	  
		IF @same_horse = @horse_idt
			BEGIN
			SET @c= @c+1
			
			  IF @end_post = 1 SET @pos1tf =@pos1tf+1
			   ELSE IF @end_post = 2 SET @pos2tf=@pos2tf+1
				ELSE IF  @end_post = 3 SET @pos3tf=@pos3tf+1
			 
			END
		ELSE
			BEGIN
				Insert INTO @horset2 values(NULL,null,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
			SET @c= 1
			SET @pos1tf=0 
			SET @pos2tf=0
			SET @pos3tf=0
			IF @end_post = 1 SET @pos1tf =@pos1tf+1
			   ELSE IF @end_post = 2 SET @pos2tf=@pos2tf+1
				ELSE IF  @end_post = 3 SET @pos3tf=@pos3tf+1

			
			END
			SET @perc =@pos1tf/@c
			
			Insert INTO @horset2
			SELECT @horse_idt,@meeting_datet,@race_timet,@distancet ,@end_post, @pos1tf ,@pos2tf,@pos3tf,@c ,@perc
			
			SET @same_horse = @horse_idt;
    
	FETCH NEXT FROM HorseCursor2 
    INTO @horse_idt,@meeting_datet,@race_timet,@distancet,@end_post
		
		end;
  
CLOSE HorseCursor2;  
DEALLOCATE HorseCursor2; 
	

	Select   * from @horset2 
	

END
--DONE
--GO
--EXECUTE Query9_a_allHorses;