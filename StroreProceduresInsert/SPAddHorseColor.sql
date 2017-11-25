
Drop PROCEDURE dbo.horseColorInsert

CREATE PROCEDURE dbo.horseColorInsert @color nvarchar(15)

		  
AS


SET NOCOUNT ON


INSERT INTO [dbo].[HORSE_COLOR]
           (   [color_name] )

     VALUES
           (@color)

GO


