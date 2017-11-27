IF OBJECT_ID('query8_c', 'P') IS NOT NULL
  DROP PROCEDURE query8_c
GO
CREATE PROCEDURE query8_c
  --@fathersCursor CURSOR OUTPUT
AS
  BEGIN
    SET NOCOUNT ON;
    SELECT H.id AS sonId, H.dad_id AS dadId,
           SUM(CASE WHEN P.end_pos = 1 THEN 1 ELSE 0 END) AS countFirstPositions,
           SUM(CASE WHEN P.end_pos = 2 THEN 1 ELSE 0 END) AS countSecondPositions,
           SUM(CASE WHEN P.end_pos = 3 THEN 1 ELSE 0 END) AS countThirdPositions,
           SUM(P.winnings) AS totalWinnings INTO #FATHERS_SONS
    FROM HORSE H, PARTICIPATION P
    WHERE H.id = P.horse_id AND H.dad_id IS NOT NULL
    GROUP BY H.id, H.dad_id
    ORDER BY totalWinnings DESC;

    SELECT * FROM #FATHERS_SONS;

    SELECT FS.dadId AS father_id, H.dad_id AS grandpa_id,
           SUM(FS.countFirstPositions) AS countFirstPositions,
           SUM(FS.countSecondPositions) AS countSecondPosisions,
           SUM(FS.countThirdPositions) AS countThirdPositions,
           SUM(FS.totalWinnings) AS totalWinnings INTO #GRANPAS_FATHERS
    FROM #FATHERS_SONS FS, HORSE H
    WHERE H.id = FS.dadId AND H.dad_id IS NOT NULL
    GROUP BY FS.dadId, H.dad_id

    SELECT * FROM #GRANPAS_FATHERS;

    SELECT GF.grandpa_id,
           SUM(GF.countFirstPositions) AS countFirstPositions,
           SUM(GF.countSecondPosisions) AS countSecondPosisions,
           SUM(GF.countThirdPositions) AS countThirdPositions,
           SUM(GF.totalWinnings) AS totalWinnings INTO #GRANDPAS
    FROM #GRANPAS_FATHERS GF
    GROUP BY GF.grandpa_id

    SELECT * FROM #GRANDPAS;

    DROP TABLE #GRANDPAS;
    DROP TABLE #GRANPAS_FATHERS;
    DROP TABLE #FATHERS_SONS;
  END
GO
--DECLARE cursor
EXEC query8_c;