CREATE PROCEDURE selectAllSuccessfulHorses
AS 
BEGIN 
	SET NOCOUNT ON;
	SELECT H.name, H.id
	FROM HORSE H, PARTICIPATION P
	WHERE H.id = P.horse_id
	GROUP BY H.id, H.name
-- Check if num of 1stPostParticipations != 0.
	HAVING  (SELECT COUNT(*) AS numOf1stPosParticipations FROM PARTICIPATION P2 WHERE P2.horse_id = H.id AND p2.end_pos = '1') != 0
						AND
					(COUNT(*) / (SELECT COUNT(*) AS numOf1stPosParticipations FROM PARTICIPATION P2 WHERE P2.horse_id = H.id AND p2.end_pos = '1')) >= (20/100)
--(number of all participations / number of 1st position participations) > (20/100)
END;
