--	Q1	
USE ACHRYS16
GO
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

SELECT *
FROM HORSE
insert into [HORSE] ([name]) VALUES ('ANNITA HOURSE');
insert into [HORSE] ([name]) VALUES ('SLOW HOURSE');
insert into [HORSE] ([name]) VALUES ('FAST HOURSE');
insert into [HORSE] ([name]) VALUES ('FAST');

--selectHorsesLike 

