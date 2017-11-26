Drop PROCEDURE dbo.racesInsert

CREATE PROCEDURE dbo.racesInsert @date date, @time time,@race_type nvarchar(25) ,@field nvarchar(25),@distance int,@prize_1 money,@prize_2 money ,@prize_3 money
AS
SET NOCOUNT ON
INSERT INTO [dbo].[RACE]
           (	[meeting_date]
				,[time]
				,[race_type]
				,[field_type]
				,[distance]
				,[prize_1]
				,[prize_2]
				,[prize_3]
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
		   )
GO
