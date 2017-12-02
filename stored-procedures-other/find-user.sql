IF OBJECT_ID('find_user', 'P') IS NOT NULL
    DROP PROCEDURE  find_user
GO
CREATE PROCEDURE find_user
  @username NVARCHAR(25),
  @password NVARCHAR(50)
AS
  SELECT SU.id, SU.username
  FROM [SYSTEM_USER] SU
  WHERE SU.passwordUser = @password AND SU.username = @username;
GO