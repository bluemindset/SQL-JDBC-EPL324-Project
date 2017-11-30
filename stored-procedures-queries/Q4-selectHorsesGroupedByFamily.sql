--------------------------------------------------
--Q4
IF OBJECT_ID('selectHorsesGroupedByFamily','P') IS NOT NULL
	DROP PROCEDURE selectHorsesGroupedByFamily
GO
CREATE PROCEDURE selectHorsesGroupedByFamily
AS
	BEGIN
		SELECT COUNT(*) AS countHorses, O.id,F.family_id --INTO #temp
		FROM HORSE H, [OWNER] O, FAMILY F
		WHERE H.owner_id = O.id  AND O.f != '#N/A'
		GROUP BY O.id, O.family_id
		ORDER BY countHorses DESC;

		--SELECT * FROM #temp
		--SELECT * FROM [OWNER]
    SELECT * FROM FAMILY

		SELECT T.family_id, F.name AS family_name, SUM(T.countHorses) AS countFamilyHorses
		FROM #temp T, FAMILY F
		WHERE T.family_id = F.id
		GROUP BY T.family_id, F.name

		DROP TABLE #temp
	END
GO
EXEC selectHorsesGroupedByFamily

