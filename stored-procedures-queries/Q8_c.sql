IF OBJECT_ID('query8_c', 'P') IS NOT NULL
	DROP PROCEDURE query8_c
GO
CREATE PROCEDURE query8_c
@inpYear INT
AS
BEGIN
	SET NOCOUNT ON;
-- SELECT all fathers with their corresponding sons and their statistics. This table is to be used to get the statistic for each father based only on their sons statistics.
	SELECT H.id AS sonId, H.dad_id AS dadId,
		   SUM(CASE WHEN P.end_pos = 1 THEN 1 ELSE 0 END) AS countFirstPositions,
		   SUM(CASE WHEN P.end_pos = 2 THEN 1 ELSE 0 END) AS countSecondPositions,
		   SUM(CASE WHEN P.end_pos = 3 THEN 1 ELSE 0 END) AS countThirdPositions,
			 COUNT(*) AS countParticipations,
		   SUM(P.winnings) AS totalWinnings INTO #FATHERS_SONS
	FROM HORSE H, PARTICIPATION P
	WHERE H.id = P.horse_id AND H.dad_id IS NOT NULL AND  @inpYear = DATEPART(yyyy, P.meeting_date)
	GROUP BY H.id, H.dad_id
	ORDER BY totalWinnings DESC;

  SELECT * FROM HORSE;

	SELECT * FROM #FATHERS_SONS;

	SELECT FS.dadId AS father_id,
		   SUM(FS.countFirstPositions) AS countFirstPositions,
		   SUM(FS.countSecondPositions) AS countSecondPosisions,
		   SUM(FS.countThirdPositions) AS countThirdPositions,
			 SUM(FS.countParticipations) AS countParticipations,
		   SUM(FS.totalWinnings) AS totalWinnings INTO #FATHER_ONLY_KID_STATS
	FROM #FATHERS_SONS FS
	GROUP BY FS.dadId;

-- 	DECLARE @inpYear INT;
-- 	SET @inpYear = 2010;
-- Get the statistics of all the individual horses. this table is to be used with #FATHERS so individual father statistics are taken into consideration.
  CREATE TABLE #HORSES_STATS (id INT, name NVARCHAR(25), countFirstPositions INT, countSecondPosisions INT, countThirdPositions INT,countParticipations INT, successRatio FLOAT, totalWinnings MONEY);
  INSERT INTO #HORSES_STATS
  EXEC query8_a @year = @inpYear;

  SELECT * FROM #HORSES_STATS;
	SELECT * FROM #HORSES_STATS HS, #FATHER_ONLY_KID_STATS F WHERE HS.id = F.father_id;

--	Add individual father statistics to the statistics of his sons.
  SELECT FOKS.father_id, HS.name,
		(HS.countFirstPositions + FOKS.countFirstPositions)                                                                                      AS countFirstPositions ,
		(HS.countSecondPosisions + FOKS.countSecondPosisions)                                                                                    AS countSecondPositions,
		(HS.countThirdPositions + FOKS.countThirdPositions)                                                                                      AS countThirdPositions,
		(HS.countParticipations + FOKS.countParticipations)                                                                                      AS countParticipations,
		ROUND (CONVERT(FLOAT, (HS.countFirstPositions + FOKS.countFirstPositions)) / (HS.countParticipations + FOKS.countParticipations) , 5, 2) AS successRatio,
		(HS.totalWinnings + FOKS.totalWinnings)                                                                                                  AS totalWinnings INTO #FATHERS
  FROM #HORSES_STATS HS, #FATHER_ONLY_KID_STATS FOKS
  WHERE HS.id = FOKS.father_id
  GROUP BY HS.id, HS.name,FOKS.father_id,
		HS.countFirstPositions, FOKS.countFirstPositions,
		HS.countSecondPosisions, FOKS.countSecondPosisions,
		HS.countThirdPositions, FOKS.countThirdPositions,
		HS.countParticipations, FOKS.countParticipations,
	  HS.totalWinnings, FOKS.totalWinnings;

	SELECT * FROM #FATHERS;

  DROP TABLE #HORSES_STATS;
	DROP TABLE #FATHER_ONLY_KID_STATS;
	DROP TABLE #FATHERS_SONS;
	DROP TABLE #FATHERS;
END
GO

-- EXEC query8_c  2010;
-- GO