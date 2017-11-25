Drop PROCEDURE dbo.racesInsert

CREATE PROCEDURE dbo.racesInsert @date date, @time time,@race_type nvarchar(25) ,@field nvarchar(25),@distance int,@prize_1 money,@prize_2 money ,@prize_3 money , @meetings int
		   
AS
	


SET NOCOUNT ON


INSERT INTO [dbo].[RACE]
           (	[date]
				,[time]
				,[race_type]
				,[field_type]
				,[distance]
				,[prize_1]
				,[prize_2]
				,[prize_3]
				,[meeting_id]
			  )

     VALUES
           (@date
           ,@time
		   ,@race_type
           ,@field
           ,@distance
           ,@prize_1
           ,@prize_2
		   ,@prize_3
		   ,@meetings
		   )

GO



/*
CREATE PROCEDURE dbo.horseInsert , @id int, @name varchar(25), @compressed_name varchar(25), 
@cur_height varchar(15),cur_weight , varchar(15) @date_of_birth varchar(25),
@age int, @sex ,@is_purebred , @record, @origin_country varchar(25), 
@mama_id,@dad_id,@jockey_id,@breeder_id,@color_name,@trainer_id,@owner_id,
@created_by, @date_created , @updated_by, @date_updated datetime
*/	