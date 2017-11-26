CREATE PROCEDURE selectHorsesGroupedByFamily
AS 
BEGIN 
	SELECT COUNT(*) AS countHorses, O.id, O.family_id INTO #temp 
	FROM HORSE H, [OWNER] O
	WHERE H.owner_id = O.id
	GROUP BY O.id, O.family_id

	SELECT 
	FROM HORSE H

	 

END;
