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

  SELECT * FROM HORSE;
	SELECT * FROM #FATHERS_SONS;

	SELECT FS.dadId AS father_id,
		   SUM(FS.countFirstPositions) AS countFirstPositions, 
		   SUM(FS.countSecondPositions) AS countSecondPosisions, 
		   SUM(FS.countThirdPositions) AS countThirdPositions, 
		   SUM(FS.totalWinnings) AS totalWinnings INTO #FATHERS
	FROM #FATHERS_SONS FS
	GROUP BY FS.dadId

  CREATE TABLE #HORSES_STATS (id INT, name NVARCHAR(25), countFirstPositions INT, countSecondPosisions INT, countThirdPositions INT,countParticipations INT, successRatio FLOAT, totalWinnings MONEY);
  INSERT INTO #HORSES_STATS
  EXEC query8_a;

  SELECT * FROM #HORSES_STATS;

  SELECT HS.id,HS.name, SUM(countFirstPositions), SUM(HS.countSecondPosisions), SUM(HS.countThirdPositions),
  FROM #HORSES_STATS HS, #FATHERS F
  WHERE HS.id = F.father_id
  GROUP BY F.father_id;
	SELECT * FROM #FATHERS;

  DROP TABLE #HORSES_STATS;
	DROP TABLE #FATHERS;
	DROP TABLE #FATHERS_SONS;
END
GO
--DECLARE cursor
EXEC query8_c;