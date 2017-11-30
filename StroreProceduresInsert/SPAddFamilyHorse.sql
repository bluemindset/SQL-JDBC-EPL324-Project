IF OBJECT_ID('familyHorseInsert' ,'P') IS NOT NULL
    DROP PROCEDURE familyHorseInsert
GO
CREATE PROCEDURE dbo.familyHorseInsert
@name NVARCHAR(15)
AS
INSERT INTO [dbo].[FAMILY]
           ( [name] )
     VALUES (@name)
GO