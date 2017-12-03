IF OBJECT_ID( 'selectHorsesGroupedByFamily' , 'P') IS NOT NULL
		DROP PROCEDURE selectHorsesGroupedByFamily;
GO
CREATE PROCEDURE selectHorsesGroupedByFamily
AS 
BEGIN 
	SELECT  H.id AS horseId, H.name AS horseName, O.onwer_family INTO #temp
	FROM HORSE H, OWNER O
  WHERE O.onwer_family!= '#N/A'  AND H.owner_id = O.id
	GROUP BY O.id, O.onwer_family, H.id, H.name;
--   SELECT * FROM #temp ORDER BY onwer_family;
	CREATE TABLE #tmp_count_family ( family_name NVARCHAR(35), countFamilyHorses INT);
	INSERT INTO #tmp_count_family
	EXEC countHorsesGroupedByFamily;
	--Gia taksinomisi.
	SELECT T.horseId, T.horseName, T.onwer_family AS owner_family
	FROM #temp T, #tmp_count_family TCF
	WHERE T.onwer_family = TCF.family_name
	ORDER BY TCF.countFamilyHorses DESC;
--   SELECT COUNT(*), onwer_family
--   FROM #temp
--   GROUP BY onwer_family
--   ORDER BY COUNT(*);
  DROP TABLE #temp;
	DROP TABLE #tmp_count_family;
END;
GO
EXEC selectHorsesGroupedByFamily;
EXEC countHorsesGroupedByFamily;
