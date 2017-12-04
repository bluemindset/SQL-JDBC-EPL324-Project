IF OBJECT_ID ( 'insertSystemAdministrator', 'P' ) IS NOT NULL
  DROP PROCEDURE insertSystemAdministrator
GO
CREATE PROCEDURE insertSystemAdministrator
		@id char(8), @username NVARCHAR(25), @firstName NVARCHAR(25), @lastName NVARCHAR(25)
		AS
		BEGIN
			INSERT INTO [SYSTEM_USER] (id, username, first_name, last_name, [role])
			VALUES (@id, @username, @firstName, @lastName, 'SA')
		END

	EXEC insertSystemAdministrator 'dbo', 'tomis', 'chrysostomos', 'chadiminas';