--	Q1	
IF EXISTS(SELECT 1 FROM sys.procedures
          WHERE Name = 'selectHorsesLike')

DROP PROCEDURE selectHorsesLike
GO
CREATE PROCEDURE selectHorsesLike
@inputName nvarchar(25)
AS 
BEGIN 
	SET NOCOUNT ON;
	SELECT *
	FROM HORSE H
	WHERE SOUNDEX(H.name) = SOUNDEX(@inputName)
	OR
	H.name like '%'+@inputName+'%'
END

-- EXEC selectHorsesLike "900";

