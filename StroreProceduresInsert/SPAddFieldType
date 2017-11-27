IF OBJECT_ID ( 'fieldTypeInsert', 'P' ) IS NOT NULL
  Drop PROCEDURE dbo.fieldTypeInsert
GO
CREATE PROCEDURE dbo.fieldTypeInsert @fieldType NVARCHAR(25)
AS
  SET NOCOUNT ON
  INSERT INTO [dbo].[FIELD_TYPE]
  ([type])
  VALUES
    (@fieldType)
GO
