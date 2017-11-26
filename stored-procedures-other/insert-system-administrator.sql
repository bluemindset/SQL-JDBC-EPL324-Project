
CREATE PROCEDURE insertSystemAdministrator
@id char(6), @username NVARCHAR(25), @firstName NVARCHAR(25), @lastName NVARCHAR(25)
AS
BEGIN
	INSERT INTO [SYSTEM_USER] (id, username, password, first_name, last_name, [role])
	VALUES (@id, @username,@password, @firstName, @lastName, 'SA')
END 