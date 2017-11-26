IF OBJECT_ID ( 'selectRacesByMeetingDate', 'P' ) IS NOT NULL
    DROP PROCEDURE selectRacesByMeetingDate;
GO
CREATE PROCEDURE selectRacesByMeetingDate
@meetingDate DATETIME
AS 
BEGIN 
	
	SELECT *
	FROM MEETING M, RACE R, PARTICIPATION P
	WHERE M.[datem] = R.[meeting_date] 
	AND R.race_id=P.race_id
	AND M.[datem] = @meetingDate
	AND CONVERT(TIME, R.[time]) = CONVERT(TIME, @meetingDate)
	ORDER BY P.end_pos DESC
END;

SELECT *
FROM MEETING


SELECT *
FROM RACE


EXEC selectRacesByMeetingDate '8-1-2010 00:00:00'
--How to compare dates? What is the format for dates ?