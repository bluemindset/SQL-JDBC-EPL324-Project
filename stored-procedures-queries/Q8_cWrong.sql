IF OBJECT_ID('query8_c', 'P') IS NOT NULL
	DROP PROCEDURE query8_c
GO
CREATE PROCEDURE query8_c
--@fathersCursor CURSOR OUTPUT
AS 
BEGIN
	SET NOCOUNT ON

	CREATE TABLE #FATHERS (father_horse_id INT, countFirstPositions INT, countSecondPositions INT, countThirdPositions INT, totalWinnings MONEY);
	INSERT INTO #FATHERS  
	(father_horse_id, countFirstPositions, countSecondPositions, countThirdPositions, totalWinnings)
	VALUES (1,1,2,3,4);
	SELECT * FROM #FATHERS;
	DROP TABLE #FATHERS;
END
GO
--DECLARE cursor
EXEC query8_c;