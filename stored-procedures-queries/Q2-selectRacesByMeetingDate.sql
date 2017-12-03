IF OBJECT_ID ( 'selectRacesByMeetingDate', 'P' ) IS NOT NULL
    DROP PROCEDURE selectRacesByMeetingDate;
GO
CREATE PROCEDURE selectRacesByMeetingDate
@meetingDate DATETIME
AS
BEGIN
	SET NOCOUNT ON;
	SELECT P.meeting_date, P.race_time, R.race_type ,P.horse_id,  P.jockey_id, P.trainer_id, P.star_pos, P.end_pos, P.winnings
	FROM RACE R, PARTICIPATION P
	WHERE CONVERT(DATE, R.meeting_date) = CONVERT(DATE, @meetingDate) AND
				(P.meeting_date = R.meeting_date AND P.race_time = R.race_time)
	ORDER BY meeting_date, race_time, end_pos;
END;
GO
-- EXEC selectRacesByMeetingDate '2010-01-08';
-- EXEC selectRacesByMeetingDate '2010-01-15';
-- EXEC selectRacesByMeetingDate '2010-01-15';
-- EXEC selectRacesByMeetingDate '2010-01-22';
-- EXEC selectRacesByMeetingDate '2010-01-29';
-- EXEC selectRacesByMeetingDate '2010-02-05';
-- EXEC selectRacesByMeetingDate '2010-02-12';
-- EXEC selectRacesByMeetingDate '2010-02-19';
-- EXEC selectRacesByMeetingDate '2010-02-26';
-- EXEC selectRacesByMeetingDate '2010-03-05';
-- EXEC selectRacesByMeetingDate '2010-03-12';
-- EXEC selectRacesByMeetingDate '2010-03-19';
-- EXEC selectRacesByMeetingDate '2011-02-04';
-- EXEC selectRacesByMeetingDate '2011-11-04';
--
-- SELECT DISTINCT MEETING.datem from MEETING;
GO