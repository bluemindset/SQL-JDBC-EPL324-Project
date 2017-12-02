IF OBJECT_ID ( 'selectRacesByMeetingDate', 'P' ) IS NOT NULL
    DROP PROCEDURE selectRacesByMeetingDate;
GO
CREATE PROCEDURE selectRacesByMeetingDate
@meetingDate DATE
AS 
BEGIN
	SELECT *
	FROM MEETING M, RACE R
	WHERE M.[datem] = R.[meeting_date] 
	--AND R.race_time = P.race_time
	AND M.[datem] = @meetingDate
	AND CONVERT(DATE, R.meeting_date) = CONVERT(DATE, @meetingDate)
	ORDER BY P.end_pos DESC
END;

--SELECT *
--FROM MEETING

--SELECT *
--FROM RACE

EXEC selectRacesByMeetingDate '2010-01-08';
--How to compare dates? What is the format for dates ?