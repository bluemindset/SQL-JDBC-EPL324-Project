
Drop PROCEDURE dbo.horseSexInsert

CREATE PROCEDURE dbo.horseSexInsert @sex nvarchar(15)

		  
AS


SET NOCOUNT ON


INSERT INTO [dbo].[HORSE_SEX]
           (   [horse_sex] )

     VALUES
           (@sex)

GO


