IF OBJECT_ID('selectHorsesThatParticipatedInAllMeetings', 'P') IS NOT NULL
    DROP PROCEDURE  selectHorsesThatParticipatedInAllMeetings;
GO
CREATE PROCEDURE selectHorsesThatParticipatedInAllMeetings
AS 
BEGIN 
	SET NOCOUNT ON;
	SELECT H.id, H.name, H.compressed_name
	FROM HORSE H
	WHERE NOT EXISTS(--All the meetings.
					 SELECT M.datem
					 FROM MEETING M
					 EXCEPT
					 --All the meetings that the horse participated in.
					 (SELECT DISTINCT P.meeting_date
					 FROM PARTICIPATION P
					 WHERE H.id = P.horse_id))

--   SELECT M.datem
--   FROM MEETING M
--   EXCEPT
--   --All the meetings that the horse participated in.
--   (SELECT DISTINCT P.meeting_date
--    FROM PARTICIPATION P
--    WHERE 41 = P.horse_id)
--
--   -- The total number of meetings.
--   SELECT COUNT(*) numOfMeetings FROM MEETING;
--   -- The number of different meetings the horse took place too
--   DECLARE @horseId INT;
--   SET @horseId = 41;
--   SELECT P.meeting_date, COUNT(*) numOfRaceTheHorseParticipatedInInThatMeeting FROM PARTICIPATION P WHERE P.horse_id = @horseId GROUP BY P.meeting_date;
--
--   SELECT COUNT(*) numOfParticipationsForHorse, P.horse_id FROM PARTICIPATION P, HORSE H WHERE H.id = P.horse_id GROUP BY P.horse_id;
--   SELECT * FROM HORSE;
--   SELECT * FROM PARTICIPATION;


END;

--EXEC selectHorsesThatParticipatedInAllMeetings