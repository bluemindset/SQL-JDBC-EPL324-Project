-- *******************************************************************************************************************************************************************************
---- CREATE FOREIGN KEY CONSTRAINTS ---- CREATE FOREIGN KEY CONSTRAINTS ---- CREATE FOREIGN KEY CONSTRAINTS ---- CREATE FOREIGN KEY CONSTRAINTS---- CREATE FOREIGN KEY CONSTRAINTS

-- HORSE
ALTER TABLE [dbo].[HORSE] WITH CHECK ADD CONSTRAINT [FK_HORSE_HORSE_MOM]
FOREIGN KEY ([mama_id]) REFERENCES [dbo].[HORSE]([id])
GO
ALTER TABLE [dbo].[HORSE] WITH CHECK ADD CONSTRAINT [FK_HORSE_HORSE_DAD]
FOREIGN KEY ([dad_id]) REFERENCES [dbo].[HORSE]([id])
GO
ALTER TABLE [dbo].[HORSE] WITH CHECK ADD CONSTRAINT [FK_HORSE_JOCKEY]
FOREIGN KEY ([jockey_id]) REFERENCES [dbo].[JOCKEY]([id])
GO
ALTER TABLE [dbo].[HORSE] WITH CHECK ADD CONSTRAINT [FK_HORSE_BREEDER]
FOREIGN KEY ([breeder_id]) REFERENCES [dbo].[BREEDER]([id])
GO
ALTER TABLE [dbo].[HORSE] WITH CHECK ADD CONSTRAINT [FK_HORSE_HORSE_COLOR]
FOREIGN KEY ([color_name]) REFERENCES [dbo].[HORSE_COLOR]([color_name])
GO
ALTER TABLE [dbo].[HORSE] WITH CHECK ADD CONSTRAINT [FK_HORSE_TRAINER]
FOREIGN KEY ([trainer_id]) REFERENCES [dbo].[TRAINER]([id])
GO
ALTER TABLE [dbo].[HORSE] WITH CHECK ADD CONSTRAINT [FK_HORSE_OWNER]
FOREIGN KEY ([owner_id]) REFERENCES [dbo].[OWNER]([id])
GO
--FOREIGN KEYS FOR USER --
ALTER TABLE [dbo].[HORSE] WITH CHECK ADD CONSTRAINT [FK_HORSE_USER_CREATE]
FOREIGN KEY ([created_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO
ALTER TABLE [dbo].[HORSE] WITH CHECK ADD CONSTRAINT [FK_HORSE_USER_UPDATE]
FOREIGN KEY ([updated_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO

-- HORSE_COLOR
--FOREIGN KEYS FOR USER --
ALTER TABLE [dbo].[HORSE_COLOR] WITH CHECK ADD CONSTRAINT [FK_HORSE_COLOR_USER_CREATE]
FOREIGN KEY ([created_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO
ALTER TABLE [dbo].[HORSE_COLOR] WITH CHECK ADD CONSTRAINT [FK_HORSE_COLOR_USER_UPDATE]
FOREIGN KEY ([updated_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO

-- JOCKEY
--FOREIGN KEYS FOR USER --
ALTER TABLE [dbo].[JOCKEY] WITH CHECK ADD CONSTRAINT [FK_JOCKEY_USER_CREATE]
FOREIGN KEY ([created_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO
ALTER TABLE [dbo].[JOCKEY] WITH CHECK ADD CONSTRAINT [FK_JOCKEY_USER_UPDATE]
FOREIGN KEY ([updated_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO

--MEETING
--FOREIGN KEYS FOR USER --
ALTER TABLE [dbo].[MEETING] WITH CHECK ADD CONSTRAINT [FK_MEETING_USER_CREATE]
FOREIGN KEY ([created_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO
ALTER TABLE [dbo].[MEETING] WITH CHECK ADD CONSTRAINT [FK_MEETING_USER_UPDATE]
FOREIGN KEY ([updated_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO

-- RACE
ALTER TABLE [dbo].[RACE] WITH CHECK ADD CONSTRAINT [FK_RACE_RACE_TYPE]
FOREIGN KEY ([race_type]) REFERENCES [dbo].[RACE_TYPE]([type])
GO
ALTER TABLE [dbo].[RACE] WITH CHECK ADD CONSTRAINT [FK_RACE_FIELD_TYPE]
FOREIGN KEY ([field_type]) REFERENCES [dbo].[FIELD_TYPE]([type])
GO
ALTER TABLE [dbo].[RACE] WITH CHECK ADD CONSTRAINT [FK_RACE_MEETING]
FOREIGN KEY ([meeting_date]) REFERENCES [dbo].[MEETING]([datem])
GO
ALTER TABLE [dbo].[RACE] WITH CHECK ADD CONSTRAINT [FK_RACE_DISTANCE]
FOREIGN KEY ([distance]) REFERENCES [dbo].[RACE_DISTANCE]([distance])
GO
--FOREIGN KEYS FOR USER --
ALTER TABLE [dbo].[RACE] WITH CHECK ADD CONSTRAINT [FK_RACE_USER_CREATE]
FOREIGN KEY ([created_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO
ALTER TABLE [dbo].[RACE] WITH CHECK ADD CONSTRAINT [FK_RACE_USER_UPDATE]
FOREIGN KEY ([updated_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO

-- RACE_DISTANCE
--FOREIGN KEYS FOR USER --
ALTER TABLE [dbo].[RACE_DISTANCE] WITH CHECK ADD CONSTRAINT [FK_RACE_DISTANCE_USER_CREATE]
FOREIGN KEY ([created_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO
															
ALTER TABLE [dbo].[RACE_DISTANCE] WITH CHECK ADD CONSTRAINT [FK_RACE_DISTANCE_USER_UPDATE]
FOREIGN KEY ([updated_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO

--TRAINER
--FOREIGN KEYS FOR USER --
ALTER TABLE [dbo].[TRAINER] WITH CHECK ADD CONSTRAINT [FK_TRAINER_USER_CREATE]
FOREIGN KEY ([created_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO
ALTER TABLE [dbo].[TRAINER] WITH CHECK ADD CONSTRAINT [FK_TRAINER_USER_UPDATE]
FOREIGN KEY ([updated_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO

--RACE_TYPE
--FOREIGN KEYS FOR USER --
ALTER TABLE [dbo].[RACE_TYPE] WITH CHECK ADD CONSTRAINT [FK_RACE_TYPE_USER_CREATE]
FOREIGN KEY ([created_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO
ALTER TABLE [dbo].[RACE_TYPE] WITH CHECK ADD CONSTRAINT [FK_RACE_TYPE_USER_UPDATE]
FOREIGN KEY ([updated_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO

-- FIELD_TYPE
--FOREIGN KEYS FOR USER --
ALTER TABLE [dbo].[FIELD_TYPE] WITH CHECK ADD CONSTRAINT [FK_FIELD_TYPE_USER_CREATE]
FOREIGN KEY ([created_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO
ALTER TABLE [dbo].[FIELD_TYPE] WITH CHECK ADD CONSTRAINT [FK_FIELD_TYPE_USER_UPDATE]
FOREIGN KEY ([updated_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO

--BREEDER
--FOREIGN KEYS FOR USER --
ALTER TABLE [dbo].[BREEDER] WITH CHECK ADD CONSTRAINT [FK_BREEDER_USER_CREATE]
FOREIGN KEY ([created_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO
ALTER TABLE [dbo].[BREEDER] WITH CHECK ADD CONSTRAINT [FK_BREEDER_USER_UPDATE]
FOREIGN KEY ([updated_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO

--FAMILY
--FOREIGN KEYS FOR USER --
ALTER TABLE [dbo].[FAMILY] WITH CHECK ADD CONSTRAINT [FK_FAMILY_USER_CREATE]
FOREIGN KEY ([created_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO
ALTER TABLE [dbo].[FAMILY] WITH CHECK ADD CONSTRAINT [FK_FAMILY_USER_UPDATE]
FOREIGN KEY ([updated_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO

--SYSTEM_USER
--FOREIGN KEYS FOR USER --
ALTER TABLE [dbo].[SYSTEM_USER] WITH CHECK ADD CONSTRAINT [FK_SYSTEM_USER_USER_CREATE]
FOREIGN KEY ([created_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO
ALTER TABLE [dbo].[SYSTEM_USER] WITH CHECK ADD CONSTRAINT [FK_SYSTEM_USER_USER_UPDATE]
FOREIGN KEY ([updated_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO

-- PARTICIPATION
ALTER TABLE [dbo].[PARTICIPATION] WITH CHECK ADD CONSTRAINT [FK_PARTICIPATION_RACE]
FOREIGN KEY ([race_time],[meeting_date]) REFERENCES [dbo].[RACE]([race_time],[meeting_date])
GO
ALTER TABLE [dbo].[PARTICIPATION] WITH CHECK ADD CONSTRAINT [FK_PARTICIPATION_HORSE]
FOREIGN KEY ([horse_id]) REFERENCES [dbo].[HORSE]([id])
GO
ALTER TABLE [dbo].[PARTICIPATION] WITH CHECK ADD CONSTRAINT [FK_PARTICIPATION_JOCKEY]
FOREIGN KEY ([jockey_id]) REFERENCES [dbo].[JOCKEY]([id])
GO
ALTER TABLE [dbo].[PARTICIPATION] WITH CHECK ADD CONSTRAINT [FK_PARTICIPATION_TRAINER]
FOREIGN KEY ([trainer_id]) REFERENCES [dbo].[TRAINER]([id])
GO
--FOREIGN KEYS FOR USER --
ALTER TABLE [dbo].[PARTICIPATION] WITH CHECK ADD CONSTRAINT [FK_PARTICIPATION_USER_CREATE]
FOREIGN KEY ([created_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO
ALTER TABLE [dbo].[PARTICIPATION] WITH CHECK ADD CONSTRAINT [FK_PARTICIPATION_USER_UPDATE]
FOREIGN KEY ([updated_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO

-- OWNER
ALTER TABLE [dbo].[OWNER] WITH CHECK ADD CONSTRAINT [FK_OWNER]
FOREIGN KEY ([family_id]) REFERENCES [dbo].[FAMILY]([id])
--FOREIGN KEYS FOR USER --
ALTER TABLE [dbo].[OWNER] WITH CHECK ADD CONSTRAINT [FK_OWNER_USER_CREATE]
FOREIGN KEY ([created_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO
ALTER TABLE [dbo].[OWNER] WITH CHECK ADD CONSTRAINT [FK_OWNER_USER_UPDATE]
FOREIGN KEY ([updated_by]) REFERENCES [dbo].[SYSTEM_USER]([id])
GO
