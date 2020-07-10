/* Création de la base de données YOURTRAD */
/* Avec le fichier de données (mdf) et le journal de transaction (ldf) */

/*
CREATE DATABASE [YOURTRAD] ON PRIMARY
(
NAME=data_mdf,
FILENAME='E:\Documents\ESME SUDRIA\Android\YourTrad\YourTrad\data_yourtrad.mdf',
SIZE=15Mb, -- Taille du fichier mdf
MAXSIZE=30, -- Taille maximale du fichier mdf
FILEGROWTH=5MB -- Augmentation par défaut en cas de saturation
)
LOG ON
(
NAME=journal_ldf,
FILENAME='E:\Documents\ESME SUDRIA\Android\YourTrad\YourTrad\journal_yourtrad.ldf',
SIZE=10Mb, -- Taille du fichier ldf
MAXSIZE=20, -- Taille maximale du fichier ldf
FILEGROWTH=2MB -- Augmentation par défaut en cas de saturation
);
*/

USE [YOURTRAD];

/* Création de la table USER */


CREATE TABLE [USER]
(
id INT NOT NULL IDENTITY,
firstname NVARCHAR(50),
lastname NVARCHAR(50),
username NVARCHAR(50),
birthdate DATE,
email NVARCHAR(130),
pays NVARCHAR(70),
password NVARCHAR(64),
CONSTRAINT USER_PK PRIMARY KEY NONCLUSTERED (id) ON[PRIMARY]
);


/* Création de la table TRADUCTION_1GRAM */

/*
CREATE TABLE [USER_HISTORY]
(
id INT NOT NULL,
id_user INT NOT NULL,
language_from NVARCHAR(50),
language_to NVARCHAR(50),
translated_from NVARCHAR(50),
translated_to NVARCHAR(50),
CONSTRAINT USER_HISTORY_PK PRIMARY KEY NONCLUSTERED (id) ON[PRIMARY],
FOREIGN KEY (id_user) REFERENCES [USER](id)
);
*/

/* Création de la table TRADUCTION_1GRAM */

/*
CREATE TABLE [TRADUCTION_1GRAM]
(
id INT NOT NULL,
word_FR NVARCHAR(50),
word_EN NVARCHAR(50),
word_ES NVARCHAR(50),
word_DE NVARCHAR(50),
CONSTRAINT TRADUCTION_1GRAM_PK PRIMARY KEY NONCLUSTERED (id) ON[PRIMARY]
);
*/