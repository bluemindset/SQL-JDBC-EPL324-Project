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

  SELECT * FROM #temp ORDER BY onwer_family;

  SELECT COUNT(*), onwer_family
  FROM #temp
  GROUP BY onwer_family
  ORDER BY COUNT(*);

  DROP TABLE #temp;

END;

EXEC selectHorsesGroupedByFamily;
EXEC countHorsesGroupedByFamily;
