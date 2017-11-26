CREATE PROCEDURE selectHorsesThatParticipatedInAllMeetings
AS 
BEGIN 
	SET NOCOUNT ON;
	SELECT *
	FROM HORSE H
	WHERE NOT EXISTS(--All the meetings.
					 SELECT M.id
					 FROM MEETING M
					 EXCEPT
					 --All the meetings that the horse participated in.
					 (SELECT DISTINCT P.meeting_id
					 FROM PARTICIPATION P
					 WHERE H.id = P.horse_id))
END;