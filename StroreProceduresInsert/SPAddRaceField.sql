Drop PROCEDURE dbo.raceFieldInsert



CREATE PROCEDURE dbo.raceFieldInsert @field nvarchar(15)

		  
AS


SET NOCOUNT ON


INSERT INTO [dbo].[RACE_FIELDS]
           (   [field] )

     VALUES
           (@field)

GO


