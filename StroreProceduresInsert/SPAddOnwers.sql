Drop PROCEDURE dbo.onwersInsert

CREATE PROCEDURE dbo.onwersInsert @id int, @firstname nvarchar(20),@lastname nvarchar(20),@title nvarchar(10)
,@uniform nvarchar(15),@onwerfam nvarchar(20),@familyid int

AS
	


SET NOCOUNT ON


INSERT INTO [dbo].[OWNER]
           (	[id]
				,[first_name]
				,[last_name]
				,[title]
				,[uniform]
				,[onwer_family]
				,[family_id]
			  )

     VALUES
           (@id
           ,@firstname
		   ,@lastname
           ,@title
           ,@uniform
           ,@onwerfam
		   ,@familyid
			)


GO		





	