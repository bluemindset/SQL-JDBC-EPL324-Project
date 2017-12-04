IF OBJECT_ID('query8_d', 'P') IS NOT NULL
  DROP PROCEDURE query8_d
GO
CREATE PROCEDURE query8_d
@inpYear INT
AS
  BEGIN
    SET NOCOUNT ON;

    --for test
--     DECLARE @inpYear INT;
--     SET @inpYear = '2010';
    SELECT  id,
      [name],
      countFirstPositions,
      countSecondPosisions,
      countThirdPositions,
      countParticipations,
      successRatio,
      totalWinnings    INTO  #HORSES_STATS_d FROM query8_a_fn(@inpYear);

    --#FATHERS stats from query Q8_c
    CREATE  TABLE #FATHERS_d (
      father_id INT,
      [name] NVARCHAR(25),
      countFirstPositions INT,
      countSecondPositions INT,
      countThirdPositions INT,
      countParticipations INT,
      successRatio FLOAT,
      totalWinnings MONEY);
    INSERT INTO #FATHERS_d
    EXEC query8_c @inpYear;

    --#MOTHERS stats from query Q8_c_mothers
    CREATE  TABLE #MOTHERS_d (
      mother_id INT,
      [name] NVARCHAR(25),
      countFirstPositions INT,
      countSecondPositions INT,
      countThirdPositions INT,
      countParticipations INT,
      successRatio FLOAT,
      totalWinnings MONEY);
    INSERT INTO #MOTHERS_d
    EXEC query8_c_mother @inpYear;

    --for test
--     SELECT * FROM #FATHERS_d WHERE father_id = 151;

    -- This is to be used to get the id of the father of the father_id. This is to be used to get grandpa but only with fathers .
    SELECT F.father_id, H.dad_id AS grandpaId, F.name, F.countFirstPositions, F.countSecondPositions, F.countThirdPositions, F.countParticipations, F.totalWinnings
    INTO #FATHER_GRANDPA_d
    FROM #FATHERS_d F, HORSE H
    WHERE F.father_id = H.id AND H.dad_id IS NOT NULL;

    SELECT
      FG.grandpaId                 AS grandpa_id,
      SUM(FG.countFirstPositions)  AS countFirstPositions,
      SUM(FG.countSecondPositions) AS countSecondPosisions,
      SUM(FG.countThirdPositions)  AS countThirdPositions,
      SUM(FG.countParticipations)  AS countParticipations,
      SUM(FG.totalWinnings)        AS totalWinnings
    INTO #GRANDPA_ONLY_FATHER_STATS
    FROM #FATHER_GRANDPA_d FG
    GROUP BY FG.grandpaId;

    -- This is to be used to get the id of the father of the father_id. This is to be used to get grandpa but only with fathers .
    SELECT M.mother_id, H.dad_id AS grandpaId, M.name, M.countFirstPositions, M.countSecondPositions, M.countThirdPositions, M.countParticipations, M.totalWinnings
    INTO #MOTHER_GRANDPA_d
    FROM #MOTHERS_d M, HORSE H
    WHERE M.mother_id = H.id AND H.dad_id IS NOT NULL;

    SELECT
      FG.grandpaId                 AS grandpa_id,
      SUM(FG.countFirstPositions)  AS countFirstPositions,
      SUM(FG.countSecondPositions) AS countSecondPosisions,
      SUM(FG.countThirdPositions)  AS countThirdPositions,
      SUM(FG.countParticipations)  AS countParticipations,
      SUM(FG.totalWinnings)        AS totalWinnings
    INTO #GRANDPA_ONLY_MOTHER_STATS
    FROM #MOTHER_GRANDPA_d FG
    GROUP BY FG.grandpaId;

    SELECT *
    INTO #GRANDPA_ONLY_KID_STATS
    FROM (
      SELECT * FROM #GRANDPA_ONLY_MOTHER_STATS
      UNION
      SELECT * FROM #GRANDPA_ONLY_FATHER_STATS
    ) x;

    --	Add individual father statistics to the statistics of his sons.
    SELECT
      GOKS.grandpa_id,
      HS.name,
      (HS.countFirstPositions + GOKS.countFirstPositions)                AS countFirstPositions,
      (HS.countSecondPosisions + GOKS.countSecondPosisions)              AS countSecondPositions,
      (HS.countThirdPositions + GOKS.countThirdPositions)                AS countThirdPositions,
      (HS.countParticipations + GOKS.countParticipations)                AS countParticipations,
      ROUND(CONVERT(FLOAT, (HS.countFirstPositions + GOKS.countFirstPositions))
            / (HS.countParticipations + GOKS.countParticipations), 5, 2) AS successRatio,
      (HS.totalWinnings + GOKS.totalWinnings)                            AS totalWinnings
    INTO #GRANDPAS
    FROM #HORSES_STATS_d HS, #GRANDPA_ONLY_KID_STATS GOKS
    WHERE HS.id = GOKS.grandpa_id
    GROUP BY HS.id, HS.name, GOKS.grandpa_id,
      HS.countFirstPositions, GOKS.countFirstPositions,
      HS.countSecondPosisions, GOKS.countSecondPosisions,
      HS.countThirdPositions, GOKS.countThirdPositions,
      HS.countParticipations, GOKS.countParticipations,
      HS.totalWinnings, GOKS.totalWinnings;

    SELECT * FROM #GRANDPAS;

    DROP TABLE #FATHERS_d;
    DROP TABLE #FATHER_GRANDPA_d;
    DROP TABLE #GRANDPA_ONLY_FATHER_STATS;

    DROP TABLE #MOTHERS_d;
    DROP TABLE #MOTHER_GRANDPA_d;
    DROP TABLE #GRANDPA_ONLY_MOTHER_STATS;

    DROP TABLE #GRANDPA_ONLY_KID_STATS;
    DROP TABLE #HORSES_STATS_d;

    DROP TABLE #GRANDPAS;
  END;
GO
--DECLARE cursor
EXEC query8_d2 2010;

--for test
--     SELECT * FROM #FATHERS_d WHERE father_id = 202;
--     SELECT * FROM #FATHERS_d WHERE father_id = 367;
--     SELECT * FROM #FATHERS_d WHERE father_id = 641;
--     SELECT * FROM #FATHER_GRANDPA_d WHERE grandpaId = 11;
--     SELECT * FROM #GRANDPA_ONLY_FATHER_STATS WHERE  grandpa_id = 11;
--     SELECT * FROM #GRANDPA_ONLY_KID_STATS WHERE grandpa_id = 11;
