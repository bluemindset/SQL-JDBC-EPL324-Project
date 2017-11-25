
Drop PROCEDURE dbo.familyHorseInsert

CREATE PROCEDURE dbo.familyHorseInsert @id int,@name nvarchar(15)

		  
AS


SET NOCOUNT ON


INSERT INTO [dbo].[FAMILY]
           (   
			[id]
		   ,[name] )

     VALUES
           (@id
		   ,@name)

GO


