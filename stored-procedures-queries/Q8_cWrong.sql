IF OBJECT_ID('query8_c', 'P') IS NOT NULL
	DROP PROCEDURE query8_c
GO
CREATE PROCEDURE query8_c
--@fathersCursor CURSOR OUTPUT
AS 
BEGIN
	SET NOCOUNT ON
	--asdsasdasadsa
	--CREATE TABLE #FATHERS (father_horse_id INT, countFirstPositions INT, 
	--					   countSecondPositions INT, countThirdPositions INT, 
	--					   totalWinnings MONEY
	--					   ,CONSTRAINT [PK_TEMP_F] PRIMARY KEY(father_horse_id ASC));
	--INSERT INTO #FATHERS  
	--(father_horse_id, countFirstPositions, countSecondPositions, countThirdPositions, totalWinnings)
	--VALUES (1,1,2,3,4);
	--CREATE TABLE #FATHERS_SONS (father_horse_id INT, son_horse_id INT, 
	--							countFirstPositions INT, countSecondPositions INT, 
	--							countThirdPositions INT, totalWinnings MONEY, 
	--							CONSTRAINT [PK_TEMP_FS] PRIMARY KEY([father_horse_id] ASC, [son_horse_id] ASC));
	
	SELECT H.id AS sonId, H.dad_id AS dadId,
		   SUM(CASE WHEN P.end_pos = 1 THEN 1 ELSE 0 END) AS countFirstPositions,
		   SUM(CASE WHEN P.end_pos = 2 THEN 1 ELSE 0 END) AS countSecondPositions,
		   SUM(CASE WHEN P.end_pos = 3 THEN 1 ELSE 0 END) AS countThirdPositions,
		   SUM(P.winnings) AS totalWinnings INTO #FATHERS_SONS
	FROM HORSE H, PARTICIPATION P
	WHERE H.id = P.horse_id AND H.dad_id IS NOT NULL
	GROUP BY H.id, H.dad_id
	ORDER BY totalWinnings DESC

	SELECT FS.dadId AS father_id,
		   SUM(FS.countFirstPositions) AS countFirstPositions, 
		   SUM(FS.countSecondPositions) AS countSecondPosisions, 
		   SUM(FS.countThirdPositions) AS countThirdPositions, 
		   SUM(FS.totalWinnings) AS totalWinnings INTO #FATHERS
	FROM #FATHERS_SONS FS
	GROUP BY FS.dadId

	SELECT * FROM #FATHERS;
	DROP TABLE #FATHERS;
	DROP TABLE #FATHERS_SONS;
END
GO
--DECLARE cursor
EXEC query8_c;