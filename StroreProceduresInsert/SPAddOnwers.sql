IF OBJECT_ID('onwersInsert','P') IS NOT NULL
	Drop PROCEDURE dbo.onwersInsert
GO
CREATE PROCEDURE dbo.onwersInsert @id int, @firstname nvarchar(20),@lastname nvarchar(20),@title nvarchar(10)
,@uniform nvarchar(15),@onwerfam nvarchar(20)
AS
  BEGIN
    DECLARE @familyid INT;
    SET @familyid = (SELECT F.id
                     FROM FAMILY F
                     WHERE F.name = @onwerfam);

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
  END
GO