IF OBJECT_ID ( 'horseSexInsert', 'P' ) IS NOT NULL
  Drop PROCEDURE horseSexInsert
GO
CREATE PROCEDURE dbo.horseSexInsert @sex nvarchar(15)
AS
SET NOCOUNT ON;

INSERT INTO [dbo].[HORSE_SEX]
           (   [horse_sex] )

     VALUES
           (@sex)
GO
