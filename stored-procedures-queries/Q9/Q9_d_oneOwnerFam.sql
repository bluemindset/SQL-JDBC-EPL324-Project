IF OBJECT_ID (N'Query9_d_oneOwnerFam1', N'P')IS NOT NULL  
    DROP PROCEDURE Query9_d_oneOwnerFam1;  

GO
CREATE PROCEDURE Query9_d_oneOwnerFam1 @ownerFam nvarchar(25)




AS 
BEGIN

	SET NOCOUNT ON

DECLARE @horsett table(horse_id int, meeting_date date,distance int,end_pos int);
DECLARE @horset table(horse_id int,owner_fam nvarchar (25), meeting_date date,race_time time,distance int,end_pos int,name varchar(20));
DECLARE @horset2 table(horse_id int,owner_fam nvarchar (25), meeting_date date,race_time time,distance int,name varchar(20),end_pos int, pos1 int,pos2 int,pos3 int,all_pos int,perc money);
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
Declare @same_owner nvarchar (25);
Declare @c float;
Declare @race_timet time;
Declare @owner_fam nvarchar (25);
Declare @namet varchar(25);
--Declare @ownerFam nvarchar(20);
SET NOCOUNT ON
--set @ownerFam ='Cork family';

/*GET ALL owner  fams OF  ALL RACES*/
INSERT INTO @horset
	SELECT  P.horse_id,O.onwer_family,P.meeting_date,P.race_time,R.distance,P.end_pos, H.name
	FROM	[PARTICIPATION]  P, [RACE] R,[HORSE] H,[OWNER] O
	WHERE	(P.meeting_date = R.meeting_date AND  P.race_time = R.race_time) AND P.horse_id = H.id AND  h.owner_id = O.id AND @ownerFam= O.onwer_family
	ORDER BY  P.meeting_date ASC ,P.race_time ASC ,H.name ASC

	

	SET @pos1tf =0 ;
	SET @pos2tf =0 ;
	SET @pos3tf = 0;
	SET @c = 0;
	SET @perc = 0;
	
	Declare HorseCursor2  CURSOR FOR
    select * from @horset h
	ORDER BY h.owner_fam ASC , h.meeting_date ASC ,h.race_time ASC


	OPEN HorseCursor2 
    FETCH NEXT FROM HorseCursor2 
    INTO @horse_idt,@owner_fam,@meeting_datet,@race_timet ,@distancet,@end_post,@namet

   SET @same_owner = @owner_fam;
 
    WHILE @@FETCH_STATUS = 0  
    BEGIN  
	  
		IF @same_owner = @owner_fam
			BEGIN
			SET @c= @c+1
			
			  IF @end_post = 1 SET @pos1tf =@pos1tf+1
			   ELSE IF @end_post = 2 SET @pos2tf=@pos2tf+1
				ELSE IF  @end_post = 3 SET @pos3tf=@pos3tf+1
			 
			END
		ELSE
			BEGIN
			SET @c= 1
			SET @pos1tf=0 
			SET @pos2tf=0
			SET @pos3tf=0
			IF @end_post = 1 SET @pos1tf =@pos1tf+1
			   ELSE IF @end_post = 2 SET @pos2tf=@pos2tf+1
				ELSE IF  @end_post = 3 SET @pos3tf=@pos3tf+1
				Insert INTO @horset2 values(NULL,'START',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
			
			END
			SET @perc =@pos1tf/@c
			
			Insert INTO @horset2
			SELECT @horse_idt ,@owner_fam, @meeting_datet,@race_timet ,@distancet ,@namet,@end_post, @pos1tf ,@pos2tf,@pos3tf
			,@c ,@perc

			SET @same_owner = @owner_fam;
    
	FETCH NEXT FROM HorseCursor2 
    INTO @horse_idt,@owner_fam,@meeting_datet,@race_timet ,@distancet,@end_post,@namet
		
		end;
  
CLOSE HorseCursor2;  
DEALLOCATE HorseCursor2; 
	

	Select   * from @horset2 h
	ORDER BY h.owner_fam ASC , h.meeting_date ASC

END

Go



--execute  Query9_d_oneOwnerFam1;