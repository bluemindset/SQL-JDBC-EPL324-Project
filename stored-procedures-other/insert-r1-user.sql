CREATE PROCEDURE insertR1User
@id char(6), @username NVARCHAR(25), @firstName NVARCHAR(25), @lastName NVARCHAR(25)
AS
BEGIN
	INSERT INTO [SYSTEM_USER] (id, username, first_name, last_name, [role])
	VALUES (@id, @username, @firstName, @lastName, 'R1')
END 