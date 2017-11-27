IF OBJECT_ID('dbo.raceTypeInsert', 'P' ) IS NOT NULL
  Drop PROCEDURE dbo.raceTypeInsert
GO
CREATE PROCEDURE dbo.raceTypeInsert @type nvarchar(15)
AS
SET NOCOUNT ON
INSERT INTO [dbo].[RACE_TYPE]
           (   [type] )
     VALUES
           (@type)
GO


