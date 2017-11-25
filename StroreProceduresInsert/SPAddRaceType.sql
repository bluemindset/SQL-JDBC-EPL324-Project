
Drop PROCEDURE dbo.raceTypeInsert

CREATE PROCEDURE dbo.raceTypeInsert @type nvarchar(15)

		  
AS


SET NOCOUNT ON


INSERT INTO [dbo].[RACE_TYPE]
           (   [type] )

     VALUES
           (@type)

GO


