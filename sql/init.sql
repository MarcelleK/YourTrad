
SET FOREIGN_KEY_CHECKS=1;

CREATE DATABASE IF NOT EXISTS YourTrad ;

CREATE TABLE IF NOT EXISTS utilisateurs
(
  id_utilisateur SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  prenom_utilisateur VARCHAR(30) NOT NULL,
  nom_utilisateur VARCHAR(30) NOT NULL,
  email_utilisateur VARCHAR(100) UNIQUE NOT NULL,
  mot_de_passe_utilisateur VARCHAR(2048) NOT NULL,
  droits_utilisateur ENUM('ADMIN', 'USER') NOT NULL DEFAULT 'USER',
  date_creation_utilisateur TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  date_modification_utilisateur TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT pk_utilisateurs PRIMARY KEY (id_utilisateur)
)
ENGINE=InnoDB;

INSERT INTO utilisateurs (prenom_utilisateur, nom_utilisateur, email_utilisateur, mot_de_passe_utilisateur, droits_utilisateur) VALUES
('John', 'Doe', 'john.doe@gmail.com', '$2y$10$2JWla8OoVycIMtqWQnJoVuCbQFFvobjZjaTOi/XkaKUdpyXPpInwK', 'CLIENT'),
('Sarah', 'Conor', 'sarah.conor@gmail.com', '$2y$10$Nv18Z.w58HnJTDpvtVRQduhIaiKl08J1GC/BxGYVKXqo55aFgxcwO', 'ADMIN');
