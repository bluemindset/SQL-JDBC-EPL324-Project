--------------------------------------------------
--Q4
IF OBJECT_ID('countHorsesGroupedByFamily','P') IS NOT NULL
	DROP PROCEDURE countHorsesGroupedByFamily
GO
CREATE PROCEDURE countHorsesGroupedByFamily
AS
	BEGIN
		SELECT COUNT(*) AS countHorses, O.id, O.onwer_family AS family_name INTO #temp
		FROM HORSE H, [OWNER] O
		WHERE H.owner_id = O.id  AND O.onwer_family != '#N/A'
		GROUP BY O.id, O.onwer_family;

-- 		SELECT * FROM #temp 	ORDER BY countHorses DESC;
-- 		SELECT * FROM [OWNER];
--    SELECT * FROM FAMILY;

		SELECT F.name AS family_name, SUM(T.countHorses) AS countFamilyHorses
		FROM #temp T, FAMILY F
		WHERE T.family_name = F.name
		GROUP BY F.name
    ORDER BY  countFamilyHorses DESC;

		DROP TABLE #temp;
	END
GO
EXEC countHorsesGroupedByFamily;

