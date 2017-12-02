IF OBJECT_ID ( 'jockeysInsert', 'P' ) IS NOT NULL
	Drop PROCEDURE jockeysInsert
GO
CREATE PROCEDURE dbo.jockeysInsert @id char(6) , @firstName nvarchar(25),@secondName nvarchar(25)
AS
SET NOCOUNT ON
INSERT INTO [dbo].[JOCKEY]
           (   [id]
			  ,[first_name]
			  ,[last_name]
			  )

     VALUES
           (@id
		   ,@firstName
		   ,@secondName)
GO


