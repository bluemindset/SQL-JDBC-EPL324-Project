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

    SELECT H.id AS sonId, H.mama_id AS momId,
           SUM(CASE WHEN P.end_pos = 1 THEN 1 ELSE 0 END) AS countFirstPositions,
           SUM(CASE WHEN P.end_pos = 2 THEN 1 ELSE 0 END) AS countSecondPositions,
           SUM(CASE WHEN P.end_pos = 3 THEN 1 ELSE 0 END) AS countThirdPositions,
           SUM(P.winnings) AS totalWinnings INTO #MOTHERS_SONS
    FROM HORSE H, PARTICIPATION P
    WHERE H.id = P.horse_id AND H.mama_id IS NOT NULL AND H.id NOT IN(SELECT FS.sonId FROM #FATHERS_SONS FS)
    GROUP BY H.id, H.mama_id
    ORDER BY totalWinnings DESC;

    SELECT * FROM #MOTHERS_SONS;

    --Check wether an eggonos is counted twice. This result-set must be empty.
    SELECT COUNT(*) FROM #MOTHERS_SONS MS, #FATHERS_SONS FS WHERE MS.sonId=FS.sonId;

    SELECT FS.dadId AS father_id, H.dad_id AS grandpa_id,
           SUM(FS.countFirstPositions) AS countFirstPositions,
           SUM(FS.countSecondPositions) AS countSecondPosisions,
           SUM(FS.countThirdPositions) AS countThirdPositions,
           SUM(FS.totalWinnings) AS totalWinnings INTO #GRANPAS_FATHERS
    FROM #FATHERS_SONS FS, HORSE H
    WHERE H.id = FS.dadId AND H.dad_id IS NOT NULL
    GROUP BY FS.dadId, H.dad_id

    SELECT * FROM #GRANPAS_FATHERS;

    SELECT MS.momId AS mother_id, H.dad_id AS grandpa_id,
           SUM(MS.countFirstPositions) AS countFirstPositions,
           SUM(MS.countSecondPositions) AS countSecondPosisions,
           SUM(MS.countThirdPositions) AS countThirdPositions,
           SUM(MS.totalWinnings) AS totalWinnings INTO #GRANPAS_MOTHERS
    FROM #MOTHERS_SONS MS, HORSE H
    WHERE H.id = MS.momId AND H.dad_id IS NOT NULL
    GROUP BY MS.momId, H.dad_id;

    SELECT * FROM #GRANPAS_MOTHERS;

    --UNION of GRANPAS MOTHERS AND GRANPAS FATHER TO GET GRANDPAS with PARENTS ids
    SELECT * INTO #GRANPAS_PARENTS
    FROM(
    SELECT GM.mother_id AS parentId, GM.grandpa_id, GM.countFirstPositions, GM.countSecondPosisions, GM.countThirdPositions, GM.totalWinnings FROM #GRANPAS_MOTHERS GM
    UNION
    SELECT GF.father_id AS parentId, GF.grandpa_id, GF.countFirstPositions, GF.countSecondPosisions, GF.countFirstPositions, GF.totalWinnings FROM #GRANPAS_FATHERS GF)
        GF_GM_UNION;

    SELECT * FROM #GRANPAS_PARENTS;

    SELECT GP.grandpa_id,
           SUM(GP.countFirstPositions) AS countFirstPositions,
           SUM(GP.countSecondPosisions) AS countSecondPosisions,
           SUM(GP.countThirdPositions) AS countThirdPositions,
           SUM(GP.totalWinnings) AS totalWinnings INTO #GRANDPAS
    FROM #GRANPAS_PARENTS GP
    GROUP BY GP.grandpa_id
    ORDER BY totalWinnings DESC

    SELECT * FROM #GRANDPAS ORDER BY totalWinnings DESC;

    DROP TABLE #GRANDPAS;
    DROP TABLE #MOTHERS_SONS;
    DROP TABLE #GRANPAS_PARENTS;
    DROP TABLE #GRANPAS_MOTHERS;
    DROP TABLE #GRANPAS_FATHERS;
    DROP TABLE #FATHERS_SONS;
  END
GO
--DECLARE cursor
--EXEC query8_c;