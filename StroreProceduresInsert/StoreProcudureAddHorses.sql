IF OBJECT_ID('horseInsert','P') IS NOT NULL
	Drop PROCEDURE dbo.horseInsert
GO
CREATE PROCEDURE dbo.horseInsert @id int, @name nvarchar(25),@sex nvarchar(20),
@color nvarchar(15), @date_of_birth date ,@cur_weight float, @Trainer int,
@Owner int,@Breeder int ,@Father_ID int ,@MOTHER_ID int	   
AS
SET NOCOUNT ON;

INSERT INTO [dbo].[HORSE]
           (   [id]
			  ,[name]
			  ,[sex]
			  ,[color_name]
			  ,[date_of_birth]
			  ,[cur_weight]
			  ,[trainer_id]
			  ,[owner_id]
			  ,[breeder_id]
			  ,[dad_id]
			  ,[mama_id]
			  )

     VALUES
           (@id
           ,@name
           ,@sex
           ,@color
           ,@DATE_OF_BIRTH
           ,@cur_weight
		   ,@Trainer
		   ,@Owner
		   ,@Breeder
		   ,@Father_ID
		   ,@MOTHER_ID
		   )

GO

