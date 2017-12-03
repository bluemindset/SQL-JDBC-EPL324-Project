IF OBJECT_ID('query8_c', 'P') IS NOT NULL
  DROP PROCEDURE query8_c
GO
CREATE PROCEDURE query8_c
    @inpYear INT
AS
  BEGIN
    SET NOCOUNT ON;
  --for test
--     DECLARE @inpYear INT;
--     SET @inpYear = 2010;
    -- Get the statistics of all the individual horses. this table is to be used with #FATHERS so individual father statistics are taken into consideration.
    SELECT id,
      [name],
      countFirstPositions,
      countSecondPosisions,
      countThirdPositions,
      countParticipations,
      successRatio,
      totalWinnings   INTO  #HORSES_STATS_c FROM query8_a_fn(@inpYear);

    -- SELECT all fathers with their corresponding sons and their statistics. This table is to be used to get the statistic for each father based only on their sons statistics.
    SELECT
      H.id            AS sonId,
      H.dad_id        AS dadId,
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
    INTO #FATHERS_SONS_c
    FROM HORSE H, PARTICIPATION P
    WHERE H.id = P.horse_id AND H.dad_id IS NOT NULL AND @inpYear = DATEPART(YYYY, P.meeting_date)
    GROUP BY H.id, H.dad_id
    ORDER BY totalWinnings DESC;

    SELECT
      FS.dadId                     AS father_id,
      SUM(FS.countFirstPositions)  AS countFirstPositions,
      SUM(FS.countSecondPositions) AS countSecondPosisions,
      SUM(FS.countThirdPositions)  AS countThirdPositions,
      SUM(FS.countParticipations)  AS countParticipations,
      SUM(FS.totalWinnings)        AS totalWinnings
    INTO #FATHER_ONLY_KID_STATS_c
    FROM #FATHERS_SONS_c FS
    GROUP BY FS.dadId;

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
    INTO #FATHERS_c
    FROM #HORSES_STATS_c HS, #FATHER_ONLY_KID_STATS_c FOKS
    WHERE HS.id = FOKS.father_id
    GROUP BY HS.id, HS.name, FOKS.father_id,
      HS.countFirstPositions, FOKS.countFirstPositions,
      HS.countSecondPosisions, FOKS.countSecondPosisions,
      HS.countThirdPositions, FOKS.countThirdPositions,
      HS.countParticipations, FOKS.countParticipations,
      HS.totalWinnings, FOKS.totalWinnings;

    --for test
    --     SELECT * FROM HORSE;
    --     SELECT * FROM #FATHERS_SONS WHERE #FATHERS_SONS.dadId = 9;
    --     SELECT * FROM HORSE H, PARTICIPATION P WHERE H.id = P.horse_id AND H.id = 9;
    --     SELECT * FROM #FATHER_ONLY_KID_STATS FS WHERE FS.father_id = 9;
    --     SELECT * FROM #HORSES_STATS HS WHERE HS.id = 9;
    --     SELECT * FROM #FATHERS F WHERE F.father_id = 9;

    SELECT * FROM #FATHERS_c;

    --get all individual horse stats.
    DROP TABLE #HORSES_STATS_c;
    --get a table with each horse and its father.
    DROP TABLE #FATHERS_SONS_c;
    --from the previous table group by father and add each kids stats to the father.
    DROP TABLE #FATHER_ONLY_KID_STATS_c;
    --add each father individual stats to his score.
    DROP TABLE #FATHERS_c;
  END;
GO
--EXEC query8_c  2010;
GO