Drop PROCEDURE dbo.raceDistancesInsert


CREATE PROCEDURE dbo.raceDistancesInsert @distance int

		  
AS


SET NOCOUNT ON


INSERT INTO [dbo].[RACE_DISTANCE]
           (   [distance] )

     VALUES
           (@distance)

GO


