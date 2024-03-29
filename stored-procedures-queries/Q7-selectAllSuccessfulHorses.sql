IF OBJECT_ID('selectAllSuccessfulHorses', 'P') IS NOT NULL
    DROP PROCEDURE  selectAllSuccessfulHorses;
GO
CREATE PROCEDURE selectAllSuccessfulHorses
AS 
BEGIN 
	SET NOCOUNT ON;
	SELECT H.id, H.name, COUNT(*) AS numOfParticipations
	FROM HORSE H, PARTICIPATION P
	WHERE H.id = P.horse_id
	GROUP BY H.id, H.name
-- Check if num of 1stPostParticipations != 0.
	HAVING  (SELECT COUNT(*) AS numOf1stPosParticipations FROM PARTICIPATION P2 WHERE P2.horse_id = H.id AND p2.end_pos = '1') != 0
						AND
					(CONVERT(FLOAT, (SELECT COUNT(*) AS numOf1stPosParticipations FROM PARTICIPATION P2 WHERE P2.horse_id = H.id AND p2.end_pos = '1')) / COUNT(*)) >= 0.2;
--(number of all participations / number of 1st position participations) > (20/100)\
--CONVERT(FLOAT, (SELECT COUNT(*) AS numOf1stPosParticipations FROM PARTICIPATION P2 WHERE P2.horse_id = 17 AND p2.end_pos = '1'));
END;
GO
exec selectAllSuccessfulHorses;