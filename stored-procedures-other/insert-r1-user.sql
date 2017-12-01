IF OBJECT_ID('insertR1User','P') IS NOT NULL
  DROP PROCEDURE insertR1User
GO
CREATE PROCEDURE insertR1User
    @id char(8), @username NVARCHAR(25),@password NVARCHAR(50),  @firstName NVARCHAR(25), @lastName NVARCHAR(25),
    @errorNum INT OUTPUT
AS
  BEGIN
    IF ( EXISTS (SELECT * FROM [SYSTEM_USER] SU WHERE SU.id = @id)  )
      BEGIN
        SET @errorNum = 1;
        RETURN;
      END
    ELSE IF (  EXISTS (SELECT * FROM [SYSTEM_USER] SU WHERE SU.username = @username) )
      BEGIN
        SET @errorNum = 2;
        RETURN;
      END
    ELSE
      BEGIN
        SET @errorNum = 0;
        INSERT INTO [SYSTEM_USER] (id, username, passwordUser, first_name, last_name, [role])
        VALUES (@id, @username, @password, @firstName, @lastName, 'R1');
      END
  END