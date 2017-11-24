/*
Store Procedure for adding horsesHorses 
*/
Drop PROCEDURE dbo.horseInsert

CREATE PROCEDURE dbo.horseInsert @id int, @name varchar(25),@sex varchar(20),
@color varchar(15), @date_of_birth date ,@cur_weight float, @Trainer int,
@Owner int,@Breeder int ,@Father_ID int ,@MOTHER_ID int
		   
		   
AS



SET NOCOUNT ON


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


/*
CREATE PROCEDURE dbo.horseInsert , @id int, @name varchar(25), @compressed_name varchar(25), 
@cur_height varchar(15),cur_weight , varchar(15) @date_of_birth varchar(25),
@age int, @sex ,@is_purebred , @record, @origin_country varchar(25), 
@mama_id,@dad_id,@jockey_id,@breeder_id,@color_name,@trainer_id,@owner_id,
@created_by, @date_created , @updated_by, @date_updated datetime
*/	