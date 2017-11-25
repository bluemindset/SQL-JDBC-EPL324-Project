Drop PROCEDURE dbo.breedersInsert

CREATE PROCEDURE dbo.breedersInsert @id char(6) , @firstname nvarchar(25),@secondName nvarchar(25)

AS

SET NOCOUNT ON

INSERT INTO [dbo].[BREEDER]
           (  [id]
			  ,[first_name]
			  ,[last_name]
			  )

     VALUES
           (@id
		   ,@firstname
		   ,@secondName)

GO


