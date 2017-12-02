IF OBJECT_ID ( 'raceDistancesInsert', 'P' ) IS NOT NULL
  Drop PROCEDURE raceDistancesInsert
GO
CREATE PROCEDURE dbo.raceDistancesInsert @distance int

		  
AS


SET NOCOUNT ON


INSERT INTO [dbo].[RACE_DISTANCE]
           (   [distance] )

     VALUES
           (@distance)

GO


