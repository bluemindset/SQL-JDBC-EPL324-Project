IF OBJECT_ID (N'Query9_c_oneOwner', N'P')IS NOT NULL
    DROP PROCEDURE Query9_c_oneOwner;

GO
CREATE PROCEDURE Query9_c_oneOwner  @owner_id int




AS
BEGIN

	SET NOCOUNT ON

	-- declare @owner_id int
	--set @owner_id = 110423
DECLARE @horsett table(horse_id int, meeting_date date,distance int,end_pos int);
DECLARE @horset table(horse_id int,owner_id int, meeting_date date,race_time time,distance int,end_pos int,name varchar(20));
DECLARE @horset2 table(horse_id int,owner_id int, meeting_date date,race_time time,distance int,name varchar(20),end_pos int, pos1 int,pos2 int,pos3 int,all_pos int,perc money);
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
Declare @same_owner int;
Declare @c float;
Declare @race_timet time;
Declare @owner_idt int;
Declare @namet varchar(25);

SET NOCOUNT ON


/*GET ALL owners OF  ALL RACES*/
INSERT INTO @horset
	SELECT  P.horse_id,H.owner_id,P.meeting_date,P.race_time,R.distance,P.end_pos, H.name
	FROM	[PARTICIPATION]  P, [RACE] R,[HORSE] H
	WHERE	(P.meeting_date = R.meeting_date AND  P.race_time = R.race_time) AND P.horse_id = H.id AND H.owner_id = @owner_id
	ORDER BY h.owner_id ASC , P.meeting_date ASC ,P.race_time ASC



	SET @pos1tf =0 ;
	SET @pos2tf =0 ;
	SET @pos3tf = 0;
	SET @c = 0;
	SET @perc = 0;

	Declare HorseCursor2  CURSOR FOR
    select DISTINCT * from @horset h
	ORDER BY h.owner_id ASC , h.meeting_date ASC ,h.race_time ASC


	OPEN HorseCursor2
    FETCH NEXT FROM HorseCursor2
    INTO @horse_idt,@owner_idt,@meeting_datet,@race_timet ,@distancet,@end_post,@namet

   SET @same_owner = @owner_idt;

    WHILE @@FETCH_STATUS = 0
    BEGIN

		IF @same_owner = @owner_idt
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


			END
			SET @perc =@pos1tf/@c

			Insert INTO @horset2
			SELECT @horse_idt ,@owner_idt, @meeting_datet,@race_timet ,@distancet ,@namet,@end_post, @pos1tf ,@pos2tf,@pos3tf
			,@c ,@perc

			SET @same_owner = @owner_idt;

	FETCH NEXT FROM HorseCursor2
    INTO @horse_idt,@owner_idt,@meeting_datet,@race_timet ,@distancet,@end_post,@namet

		end;

CLOSE HorseCursor2;
DEALLOCATE HorseCursor2;


	Select   * from @horset2 h
	--ORDER BY h.owner_id ASC , h.meeting_date ASC

END
GO
--DONE
-- execute  Query9_c_oneOwner 110423  ;