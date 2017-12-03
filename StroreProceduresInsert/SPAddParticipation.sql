IF OBJECT_ID ( 'participationInsert', 'P' ) IS NOT NULL
  Drop PROCEDURE dbo.participationInsert
GO
CREATE PROCEDURE dbo.participationInsert @race_time TIME, @meeting_date DATE, @horse_id INT, @jockey_id CHAR(6), @trainer_id CHAR(6), @star_pos INT, @end_pos INT
AS
  DECLARE @curHorseWeight FLOAT;
  DECLARE @curHorseAge FLOAT;
  DECLARE @winnings MONEY;

  SET @curHorseWeight = (SELECT H.cur_weight FROM HORSE H WHERE H.id = @horse_id);
  SET @curHorseAge = (SELECT H.age FROM HORSE H WHERE H.id = @horse_id);

--   IF @end_pos = 3
--     BEGIN
--       SET @winnings = (SELECT R.prize_3 FROM RACE R WHERE R.race_time = @race_time AND CONVERT(DATE, R.meeting_date) = CONVERT(DATE, @meeting_date));
--     END
--   ELSE IF @end_pos = 2
--     BEGIN
--       SET @winnings = (SELECT R.prize_2 FROM RACE R WHERE R.race_time = @race_time AND CONVERT(DATE, R.meeting_date) = CONVERT(DATE, @meeting_date));
--     END
--   ELSE IF @end_pos = 1
--     BEGIN
--       SET @winnings = (SELECT R.prize_1 FROM RACE R WHERE R.race_time = @race_time AND CONVERT(DATE, R.meeting_date) = CONVERT(DATE, @meeting_date));
--     END
--   ELSE
--     SET @winnings = 0;

  BEGIN
    INSERT INTO [dbo].[PARTICIPATION]
    ( race_time,   meeting_date,  horse_id,  jockey_id,  trainer_id,  cur_horse_weight,cur_horse_age, star_pos, end_pos)
    VALUES( @race_time, @meeting_date, @horse_id, @jockey_id, @trainer_id, @curHorseWeight, @curHorseAge, @star_pos, @end_pos);
  END
GO