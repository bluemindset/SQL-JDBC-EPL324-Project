ALTER PROCEDURE selectHorsesGroupedByFamily
AS 
BEGIN 
	SELECT  COUNT(*) AS countHorses, F.id, O.id
	FROM HORSE H
	LEFT JOIN 
	([OWNER] O 
	LEFT JOIN  FAMILY F 
		ON F.id=O.family_id
	)ON O.id=H.owner_id
	GROUP BY O.id,F.id
	ORDER BY countHorses DESC


END;
