IF OBJECT_ID('query8_c_mother', 'P') IS NOT NULL
  DROP PROCEDURE query8_c_mother
GO
CREATE PROCEDURE query8_c_mother
    @inpYear INT
AS
  BEGIN
    SET NOCOUNT ON;
  --for test
--     DECLARE @inpYear INT;
--     SET @inpYear = 2010;

    SELECT  id,
            [name],
            countFirstPositions,
            countSecondPosisions,
            countThirdPositions,
            countParticipations,
            successRatio,
            totalWinnings    INTO  #HORSES_STATS_c2 FROM query8_a_fn(@inpYear);

    -- SELECT all fathers with their corresponding sons and their statistics. This table is to be used to get the statistic for each father based only on their sons statistics.
    SELECT
      H.id            AS sonId,
      H.mama_id       AS momId,
      SUM(CASE WHEN P.end_pos = 1
        THEN 1
          ELSE 0 END) AS countFirstPositions,
      SUM(CASE WHEN P.end_pos = 2
        THEN 1
          ELSE 0 END) AS countSecondPositions,
      SUM(CASE WHEN P.end_pos = 3
        THEN 1
          ELSE 0 END) AS countThirdPositions,
      COUNT(*)        AS countParticipations,
      SUM(P.winnings) AS totalWinnings
    INTO #MOTHERS_SONS_c2
    FROM HORSE H, PARTICIPATION P
    WHERE H.id = P.horse_id AND H.mama_id IS NOT NULL AND @inpYear = DATEPART(YYYY, P.meeting_date) AND H.dad_id IS NULL
    GROUP BY H.id, H.mama_id
    ORDER BY totalWinnings DESC;

    SELECT
      MS.momId                     AS mother_id,
      SUM(MS.countFirstPositions)  AS countFirstPositions,
      SUM(MS.countSecondPositions) AS countSecondPosisions,
      SUM(MS.countThirdPositions)  AS countThirdPositions,
      SUM(MS.countParticipations)  AS countParticipations,
      SUM(MS.totalWinnings)        AS totalWinnings
    INTO #MOTHER_ONLY_KIDS_STATS_c2
    FROM #MOTHERS_SONS_c2 MS
    GROUP BY MS.momId;

    --	Add individual father statistics to the statistics of his sons.
    SELECT
      MOKS.mother_id,
      HS.name,
      (HS.countFirstPositions + MOKS.countFirstPositions)                AS countFirstPositions,
      (HS.countSecondPosisions + MOKS.countSecondPosisions)              AS countSecondPositions,
      (HS.countThirdPositions + MOKS.countThirdPositions)                AS countThirdPositions,
      (HS.countParticipations + MOKS.countParticipations)                AS countParticipations,
      ROUND(CONVERT(FLOAT, (HS.countFirstPositions + MOKS.countFirstPositions))
            / (HS.countParticipations + MOKS.countParticipations), 5, 2) AS successRatio,
      (HS.totalWinnings + MOKS.totalWinnings)                            AS totalWinnings
    INTO #MOTHER_c2
    FROM #HORSES_STATS_c2 HS, #MOTHER_ONLY_KIDS_STATS_c2 MOKS
    WHERE HS.id = MOKS.mother_id
    GROUP BY HS.id, HS.name, MOKS.mother_id,
      HS.countFirstPositions, MOKS.countFirstPositions,
      HS.countSecondPosisions, MOKS.countSecondPosisions,
      HS.countThirdPositions, MOKS.countThirdPositions,
      HS.countParticipations, MOKS.countParticipations,
      HS.totalWinnings, MOKS.totalWinnings;

    --for test
--     SELECT * FROM #HORSES_STATS_c2 WHERE id = 2;
--     SELECT * FROM #MOTHERS_SONS_c2 WHERE momId = 2;
--     SELECT * FROM #MOTHER_ONLY_KIDS_STATS_c2 WHERE mother_id = 2;
--     SELECT * FROM #MOTHER_c2 where mother_id =2;

    SELECT * FROM #MOTHER_c2;

    --get all individual horse stats.
    DROP TABLE #HORSES_STATS_c2;
    --get a table with each horse and its father.
    DROP TABLE #MOTHERS_SONS_c2;
    --from the previous table group by father and add each kids stats to the father.
    DROP TABLE #MOTHER_ONLY_KIDS_STATS_c2;
    --add each father individual stats to his score.
    DROP TABLE #MOTHER_c2;
  END;
GO
--EXEC query8_c_mother 2010;
GO