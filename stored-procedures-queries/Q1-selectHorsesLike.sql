IF OBJECT_ID ( 'selectHorsesLike', 'P' ) IS NOT NULL
    DROP PROCEDURE selectHorsesLike;
GO
CREATE PROCEDURE selectHorsesLike
@inputName nvarchar(25)
AS 
BEGIN 
	SET NOCOUNT ON;
	SELECT *
	FROM HORSE H
	WHERE SOUNDEX(H.name) = SOUNDEX(@inputName)
END;
-- EXEC selectHorsesLike('Jimmy');

