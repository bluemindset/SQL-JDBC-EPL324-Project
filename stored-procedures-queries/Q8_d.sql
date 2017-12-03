IF OBJECT_ID('query8_d', 'P') IS NOT NULL
  DROP PROCEDURE query8_d
GO
CREATE PROCEDURE query8_d
@inpYear INT
AS
  BEGIN
    SET NOCOUNT ON;
    -- for test
    DECLARE @inpYear INT;
    SET @inpYear = 2010;

    -- when I say sons I mean kids :P.
    -- Get the statistics of all the individual horses. this table is to be used with #FATHERS and #MOTHERS so individual father statistics are taken into consideration.
    CREATE TABLE #HORSES_STATS (
      id                   INT,
      name                 NVARCHAR(25),
      countFirstPositions  INT,
      countSecondPosisions INT,
      countThirdPositions  INT,
      countParticipations  INT,
      successRatio         FLOAT,
      totalWinnings        MONEY
    );

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

    --Father stats but taking only his kids stats into consideration.
    SELECT
      FS.dadId                     AS father_id,
      SUM(FS.countFirstPositions)  AS countFirstPositions,
      SUM(FS.countSecondPositions) AS countSecondPosisions,
      SUM(FS.countThirdPositions)  AS countThirdPositions,
      SUM(FS.countParticipations)  AS countParticipations,
      SUM(FS.totalWinnings)        AS totalWinnings
    INTO #FATHER_ONLY_KID_STATS
    FROM #FATHERS_SONS FS
    GROUP BY FS.dadId;

    --for test
    --     DECLARE @inpYear INT;
    --     SET @inpYear = 2010;

    INSERT INTO #HORSES_STATS
    EXEC query8_a @inpYear;

    --for test
--     SELECT *
--     FROM #HORSES_STATS;
--     SELECT *
--     FROM #HORSES_STATS HS, #FATHER_ONLY_KID_STATS F
--     WHERE HS.id = F.father_id;

    --	Add individual father statistics to the statistics of his sons.
    SELECT
      FOKS.father_id,
      HS.name,
      (HS.countFirstPositions + FOKS.countFirstPositions)                AS countFirstPositions,
      (HS.countSecondPosisions + FOKS.countSecondPosisions)              AS countSecondPositions,
      (HS.countThirdPositions + FOKS.countThirdPositions)                AS countThirdPositions,
      (HS.countParticipations + FOKS.countParticipations)                AS countParticipations,
      ROUND(CONVERT(FLOAT, (HS.countFirstPositions + FOKS.countFirstPositions))
            / (HS.countParticipations + FOKS.countParticipations), 5, 2) AS successRatio,
      (HS.totalWinnings + FOKS.totalWinnings)                            AS totalWinnings
    INTO #FATHERS
    FROM #HORSES_STATS HS, #FATHER_ONLY_KID_STATS FOKS
    WHERE HS.id = FOKS.father_id
    GROUP BY HS.id, HS.name, FOKS.father_id,
      HS.countFirstPositions, FOKS.countFirstPositions,
      HS.countSecondPosisions, FOKS.countSecondPosisions,
      HS.countThirdPositions, FOKS.countThirdPositions,
      HS.countParticipations, FOKS.countParticipations,
      HS.totalWinnings, FOKS.totalWinnings;

    --for test
--     SELECT *
--     FROM #FATHERS;

    -- for test
    DECLARE @inpYear INT;
    SET @inpYear = 2010;
    -- SELECT all mothers with their corresponding sons and their statistics. This table is to be used to get the statistic for each mothers based only on their sons statistics.
    SELECT H.id AS sonId, H.mama_id AS momId,
           SUM(CASE WHEN P.end_pos = 1 THEN 1 ELSE 0 END) AS countFirstPositions,
           SUM(CASE WHEN P.end_pos = 2 THEN 1 ELSE 0 END) AS countSecondPositions,
           SUM(CASE WHEN P.end_pos = 3 THEN 1 ELSE 0 END) AS countThirdPositions,
           COUNT(*) AS countParticipations,
           SUM(P.winnings) AS totalWinnings INTO #MOTHERS_SONS
    FROM HORSE H, PARTICIPATION P
    WHERE H.id = P.horse_id AND H.mama_id IS NOT NULL AND H.id NOT IN(SELECT FS.sonId FROM #FATHERS_SONS FS) AND  @inpYear = DATEPART(yyyy, P.meeting_date)
    GROUP BY H.id, H.mama_id
    ORDER BY totalWinnings DESC;

    --Check wether an eggonos is counted twice. This result-set must be empty.
    --     SELECT * FROM #MOTHERS_SONS;
    --     SELECT COUNT(*) FROM #MOTHERS_SONS MS, #FATHERS_SONS FS WHERE MS.sonId=FS.sonId;

    SELECT FS.dadId AS father_id, H.dad_id AS grandpa_id,
           SUM(FS.countFirstPositions) AS countFirstPositions,
           SUM(FS.countSecondPositions) AS countSecondPosisions,
           SUM(FS.countThirdPositions) AS countThirdPositions,
           SUM(FS.totalWinnings) AS totalWinnings INTO #GRANPAS_FATHERS
    FROM #FATHERS_SONS FS, HORSE H
    WHERE H.id = FS.dadId AND H.dad_id IS NOT NULL
    GROUP BY FS.dadId, H.dad_id;

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

--EXEC query8_c;