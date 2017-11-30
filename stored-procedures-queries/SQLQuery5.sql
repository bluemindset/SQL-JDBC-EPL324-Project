IF OBJECT_ID('selectTrainerWithNo1winnings', 'P') IS NOT NULL
    DROP PROCEDURE  selectTrainerWithNo1winnings;
GO
CREATE PROCEDURE selectTrainerWithNo1winnings
AS 
BEGIN
select T.id,T.compressed_name
FROM TRAINER T
WHERE T.ID NOT IN (
		SELECT distinct T.id
		FROM PARTICIPATION P
		WHERE P.trainer_id= T.id AND P.end_pos !='1'
		)

--   select DISTINCT T.id,T.compressed_name
--   FROM TRAINER T, PARTICIPATION P
--   WHERE T.id = P.trainer_id AND P.end_pos != '1';
END;

-- SELECT trainer_id
-- FROM PARTICIPATION P, TRAINER T
-- WHERE P.trainer_id= T.id AND P.end_pos ='1'
--
-- SELECT COUNT(*)
-- FROM PARTICIPATION P
-- WHERE P.end_pos ='1';
--exec selectTrainerWithNo1winnings;

