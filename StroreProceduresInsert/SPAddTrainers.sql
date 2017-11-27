IF OBJECT_ID('trainersInsert', 'P') IS NOT NULL
	Drop PROCEDURE dbo.trainersInsert
GO
CREATE PROCEDURE dbo.trainersInsert @id char(6) , @firstName nvarchar(25),@secondName nvarchar(25)	   
AS
SET NOCOUNT ON
INSERT INTO [dbo].[TRAINER]
           (   [id]
			  ,[first_name]
			  ,[last_name]
			  )
     VALUES
           (@id
		   ,@firstName
		   ,@secondName)
GO

