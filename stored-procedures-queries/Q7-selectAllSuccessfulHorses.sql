CREATE PROCEDURE selectAllSuccessfulHorses
AS 
BEGIN 
	SET NOCOUNT ON;
	SELECT *
	FROM HORSE H, PARTICIPATION P
	WHERE H.id = P.horse_id
	HAVING (COUNT(*) / (SELECT COUNT(*) AS numOf1stPosParticipations
					   FROM PARTICIPATION P2
					   WHERE P2.horse_id = H.id
					   AND p2.end_pos = '1')) >= (20/100)
--(number of all participations / number of 1st position participations) > (20/100)
END;
--How to compare dates? What is the format for dates ?