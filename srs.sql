-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Czas generowania: 15 Sty 2020, 21:39
-- Wersja serwera: 10.4.8-MariaDB
-- Wersja PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `srs`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `buildings`
--

CREATE TABLE `buildings` (
  `ID_building` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8mb4_polish_ci NOT NULL,
  `fullName` varchar(300) COLLATE utf8mb4_polish_ci NOT NULL,
  `town` varchar(50) COLLATE utf8mb4_polish_ci NOT NULL DEFAULT '-',
  `address` varchar(50) COLLATE utf8mb4_polish_ci NOT NULL DEFAULT '-',
  `area` varchar(30) COLLATE utf8mb4_polish_ci NOT NULL DEFAULT '-'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `buildings`
--

INSERT INTO `buildings` (`ID_building`, `name`, `fullName`, `town`, `address`, `area`) VALUES
(1, 'A0', 'Kolegium Nauk Przyrodniczych UR', 'Rzeszów', 'ul. Pigonia 1', 'Rejtana'),
(2, 'A1', '', 'Rzeszów', 'ul. Rejtana 16C', 'Rejtana'),
(3, 'A2', 'Kolegium Nauk Humanistycznych UR', 'Rzeszów', 'ul. Kopisto 2A', 'Rejtana'),
(4, 'G1', 'Biblioteka Główna', 'Rzeszów', 'ul. Pigonia 8', 'Pigonia'),
(5, 'G4', '', 'Rzeszów', 'ul. Warzywna 1', 'Pigonia'),
(6, 'G5', '', 'Rzeszów', 'ul. Warzywna 1', 'Pigonia'),
(8, 'G5432', '', 'Rzeszów', 'ul. Warzywna 1', 'Pigonia');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `equipment`
--

CREATE TABLE `equipment` (
  `ID_equipment` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8mb4_polish_ci NOT NULL,
  `description` varchar(500) COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `equipment`
--

INSERT INTO `equipment` (`ID_equipment`, `name`, `description`) VALUES
(1, 'Projektor FullHD', 'HDMI, D-SUB'),
(2, 'Mikroport krawatowy', ''),
(3, 'Nagłośnienie sali', '5.1'),
(4, 'Mikrofon stacjonarny', ''),
(5, 'Tablica kredowa', ''),
(6, 'Tablica biała', ''),
(7, 'Laptop', 'Przygotowanie pod programowanie w środowisku Java, Matlab'),
(8, 'Stanowisko komputerowe', 'Dostęp do Internetu, dostęp do Katalogu Bibliotecznego'),
(9, 'Materac piankowy DUŻY', '2mx1,5mx0,05m'),
(10, 'Piłka do siatkówki', '');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `equipment_allocation`
--

CREATE TABLE `equipment_allocation` (
  `ID_room` int(11) NOT NULL,
  `ID_equipment` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `ID_alloc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `equipment_allocation`
--

INSERT INTO `equipment_allocation` (`ID_room`, `ID_equipment`, `amount`, `ID_alloc`) VALUES
(5, 1, 1, 1),
(7, 1, 1, 2),
(5, 2, 2, 3),
(9, 8, 10, 4),
(10, 5, 2, 5),
(5, 3, 1, 6),
(7, 3, 1, 7),
(14, 9, 5, 8),
(14, 10, 2, 9),
(15, 6, 1, 10),
(15, 4, 1, 11),
(12, 6, 4, 12),
(13, 5, 2, 13);

-- --------------------------------------------------------

--
-- Zastąpiona struktura widoku `equipment_info`
-- (Zobacz poniżej rzeczywisty widok)
--
CREATE TABLE `equipment_info` (
`ID_equipment` int(11)
,`name` varchar(200)
,`description` varchar(500)
,`ID_room` int(11)
,`amount` int(11)
,`ID_alloc` int(11)
);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `reservations`
--

CREATE TABLE `reservations` (
  `ID_reservation` int(11) NOT NULL,
  `ID_user` int(11) NOT NULL,
  `ID_room` int(11) NOT NULL,
  `meet_time_start` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `meet_time_end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `reservations`
--

INSERT INTO `reservations` (`ID_reservation`, `ID_user`, `ID_room`, `meet_time_start`, `meet_time_end`) VALUES
(26, 1, 12, '2020-01-01 07:00:00', '2020-01-01 19:00:00'),
(27, 1, 5, '2020-01-11 08:00:00', '2020-01-12 15:00:00'),
(28, 1, 7, '2020-01-11 08:00:00', '2020-01-12 15:00:00'),
(29, 1, 8, '2020-01-11 20:57:46', '2020-01-12 16:00:00'),
(30, 1, 10, '2020-01-11 08:00:00', '2020-01-12 15:00:00'),
(31, 1, 7, '2020-01-15 08:00:00', '2020-01-15 15:00:00'),
(32, 1, 15, '2020-01-13 23:00:00', '2020-01-15 09:00:00'),
(34, 1, 15, '2020-01-15 09:00:00', '2020-01-15 15:00:00'),
(35, 1, 12, '2020-01-21 08:00:00', '2020-01-21 15:00:00'),
(38, 1, 10, '2020-01-29 07:00:00', '2020-01-29 08:00:00'),
(39, 1, 9, '2020-01-15 08:00:00', '2020-01-15 15:00:00');

-- --------------------------------------------------------

--
-- Zastąpiona struktura widoku `reservations_view`
-- (Zobacz poniżej rzeczywisty widok)
--
CREATE TABLE `reservations_view` (
`ID_room` int(11)
,`roomName` varchar(30)
,`roomFullName` varchar(300)
,`seats` int(11)
,`floor` varchar(10)
,`wing` varchar(20)
,`max_time_reservation` int(11)
,`reservation_ability` tinyint(1)
,`ID_building` int(11)
,`buildingName` varchar(200)
,`buildingFullName` varchar(300)
,`town` varchar(50)
,`address` varchar(50)
,`area` varchar(30)
,`id_type` int(11)
,`typeName` varchar(30)
,`ID_reservation` int(11)
,`meet_time_start` timestamp
,`meet_time_end` timestamp
,`ID_user` int(11)
,`userName` varchar(30)
,`userSurname` varchar(30)
,`userDescription` varchar(50)
);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rooms`
--

CREATE TABLE `rooms` (
  `ID_room` int(11) NOT NULL,
  `ID_building` int(11) NOT NULL,
  `name` varchar(30) COLLATE utf8mb4_polish_ci NOT NULL,
  `fullName` varchar(300) COLLATE utf8mb4_polish_ci NOT NULL DEFAULT '-',
  `seats` int(11) NOT NULL,
  `floor` varchar(10) COLLATE utf8mb4_polish_ci NOT NULL DEFAULT '-',
  `wing` varchar(20) COLLATE utf8mb4_polish_ci NOT NULL DEFAULT '-',
  `max_time_reservation` int(11) NOT NULL DEFAULT -1,
  `reservation_ability` tinyint(1) NOT NULL,
  `id_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `rooms`
--

INSERT INTO `rooms` (`ID_room`, `ID_building`, `name`, `fullName`, `seats`, `floor`, `wing`, `max_time_reservation`, `reservation_ability`, `id_type`) VALUES
(5, 2, '132', '-', 50, '-', 'B2', -1, 1, 3),
(7, 1, '127', '-', 100, '1', 'B2', -1, 1, 1),
(8, 2, '220', '-', 20, '2', '-', 120, 1, 2),
(9, 1, '126', '-', 100, '-', 'B2', -1, 1, 1),
(10, 3, '105', '-', 50, '-', '-', 120, 1, 3),
(11, 3, '107', '-', 50, '1', '-', 120, 1, 3),
(12, 5, '-105', '-', 120, '-1', '-', -1, 1, 1),
(13, 5, '-101', '-', 120, '-1', '-', -1, 1, 1),
(14, 6, '112', '-', 20, '1', '-', -1, 1, 6),
(15, 6, '101', '-', 25, '1', '-', -1, 1, 7),
(16, 6, '125', 'Sekretariat KNoZ', 0, '1', '-', -1, 0, 4),
(17, 1, '46', 'Sekretariat KNP', 0, 'Parter', 'B2', -1, 0, 4),
(18, 6, '1255555555', 'Sekretariat KNoZ', 0, '1', '-', 0, 0, 4);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `room_types`
--

CREATE TABLE `room_types` (
  `id_type` int(11) NOT NULL,
  `name` varchar(30) COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `room_types`
--

INSERT INTO `room_types` (`id_type`, `name`) VALUES
(1, 'Sala wykładowa'),
(2, 'Sala komputerowa'),
(3, 'Sala ćwiczeniowa'),
(4, 'Pomieszczenie administracyjne'),
(5, 'Pomieszczenie ogólnodostępne'),
(6, 'Sala gimnastyczna'),
(7, 'Sala audytoryjna');

-- --------------------------------------------------------

--
-- Zastąpiona struktura widoku `table_rooms`
-- (Zobacz poniżej rzeczywisty widok)
--
CREATE TABLE `table_rooms` (
`ID_room` int(11)
,`roomName` varchar(30)
,`roomFullName` varchar(300)
,`seats` int(11)
,`floor` varchar(10)
,`wing` varchar(20)
,`max_time_reservation` int(11)
,`reservation_ability` tinyint(1)
,`ID_building` int(11)
,`buildingName` varchar(200)
,`buildingFullName` varchar(300)
,`town` varchar(50)
,`address` varchar(50)
,`area` varchar(30)
,`id_type` int(11)
,`typeName` varchar(30)
);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `ID_user` int(11) NOT NULL,
  `name` varchar(30) COLLATE utf8mb4_polish_ci NOT NULL,
  `surname` varchar(30) COLLATE utf8mb4_polish_ci NOT NULL,
  `reservation_ability` tinyint(1) NOT NULL,
  `description` varchar(50) COLLATE utf8mb4_polish_ci NOT NULL,
  `role` int(11) NOT NULL,
  `username` varchar(30) COLLATE utf8mb4_polish_ci NOT NULL,
  `password` varchar(300) COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`ID_user`, `name`, `surname`, `reservation_ability`, `description`, `role`, `username`, `password`) VALUES
(1, 'Marcin', 'Wielgos', 1, 'Administrator', 1, 'wielgos', '123'),
(2, 'Tomasz', 'Nowak', 1, 'Student', 0, 'nowak', 'qwerty');

-- --------------------------------------------------------

--
-- Struktura widoku `equipment_info`
--
DROP TABLE IF EXISTS `equipment_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `equipment_info`  AS  select `e`.`ID_equipment` AS `ID_equipment`,`e`.`name` AS `name`,`e`.`description` AS `description`,`ea`.`ID_room` AS `ID_room`,`ea`.`amount` AS `amount`,`ea`.`ID_alloc` AS `ID_alloc` from (`equipment` `e` join `equipment_allocation` `ea` on(`ea`.`ID_equipment` = `e`.`ID_equipment`)) ;

-- --------------------------------------------------------

--
-- Struktura widoku `reservations_view`
--
DROP TABLE IF EXISTS `reservations_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `reservations_view`  AS  select `r`.`ID_room` AS `ID_room`,`r`.`name` AS `roomName`,`r`.`fullName` AS `roomFullName`,`r`.`seats` AS `seats`,`r`.`floor` AS `floor`,`r`.`wing` AS `wing`,`r`.`max_time_reservation` AS `max_time_reservation`,`r`.`reservation_ability` AS `reservation_ability`,`b`.`ID_building` AS `ID_building`,`b`.`name` AS `buildingName`,`b`.`fullName` AS `buildingFullName`,`b`.`town` AS `town`,`b`.`address` AS `address`,`b`.`area` AS `area`,`t`.`id_type` AS `id_type`,`t`.`name` AS `typeName`,`res`.`ID_reservation` AS `ID_reservation`,`res`.`meet_time_start` AS `meet_time_start`,`res`.`meet_time_end` AS `meet_time_end`,`u`.`ID_user` AS `ID_user`,`u`.`name` AS `userName`,`u`.`surname` AS `userSurname`,`u`.`description` AS `userDescription` from ((((`rooms` `r` join `buildings` `b` on(`b`.`ID_building` = `r`.`ID_building`)) join `room_types` `t` on(`r`.`id_type` = `t`.`id_type`)) join `reservations` `res` on(`r`.`ID_room` = `res`.`ID_room`)) join `users` `u` on(`u`.`ID_user` = `res`.`ID_user`)) ;

-- --------------------------------------------------------

--
-- Struktura widoku `table_rooms`
--
DROP TABLE IF EXISTS `table_rooms`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `table_rooms`  AS  select `r`.`ID_room` AS `ID_room`,`r`.`name` AS `roomName`,`r`.`fullName` AS `roomFullName`,`r`.`seats` AS `seats`,`r`.`floor` AS `floor`,`r`.`wing` AS `wing`,`r`.`max_time_reservation` AS `max_time_reservation`,`r`.`reservation_ability` AS `reservation_ability`,`b`.`ID_building` AS `ID_building`,`b`.`name` AS `buildingName`,`b`.`fullName` AS `buildingFullName`,`b`.`town` AS `town`,`b`.`address` AS `address`,`b`.`area` AS `area`,`t`.`id_type` AS `id_type`,`t`.`name` AS `typeName` from ((`rooms` `r` join `buildings` `b` on(`b`.`ID_building` = `r`.`ID_building`)) join `room_types` `t` on(`r`.`id_type` = `t`.`id_type`)) ;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `buildings`
--
ALTER TABLE `buildings`
  ADD PRIMARY KEY (`ID_building`);

--
-- Indeksy dla tabeli `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`ID_equipment`);

--
-- Indeksy dla tabeli `equipment_allocation`
--
ALTER TABLE `equipment_allocation`
  ADD PRIMARY KEY (`ID_alloc`),
  ADD KEY `ID_room` (`ID_room`),
  ADD KEY `ID_equipment` (`ID_equipment`);

--
-- Indeksy dla tabeli `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`ID_reservation`),
  ADD KEY `ID_user` (`ID_user`),
  ADD KEY `ID_room` (`ID_room`);

--
-- Indeksy dla tabeli `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`ID_room`),
  ADD KEY `id_type` (`id_type`),
  ADD KEY `ID_building` (`ID_building`) USING BTREE;

--
-- Indeksy dla tabeli `room_types`
--
ALTER TABLE `room_types`
  ADD PRIMARY KEY (`id_type`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `buildings`
--
ALTER TABLE `buildings`
  MODIFY `ID_building` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT dla tabeli `equipment`
--
ALTER TABLE `equipment`
  MODIFY `ID_equipment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT dla tabeli `equipment_allocation`
--
ALTER TABLE `equipment_allocation`
  MODIFY `ID_alloc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT dla tabeli `reservations`
--
ALTER TABLE `reservations`
  MODIFY `ID_reservation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT dla tabeli `rooms`
--
ALTER TABLE `rooms`
  MODIFY `ID_room` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT dla tabeli `room_types`
--
ALTER TABLE `room_types`
  MODIFY `id_type` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `ID_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `equipment_allocation`
--
ALTER TABLE `equipment_allocation`
  ADD CONSTRAINT `equipment_allocation_ibfk_1` FOREIGN KEY (`ID_equipment`) REFERENCES `equipment` (`ID_equipment`),
  ADD CONSTRAINT `equipment_allocation_ibfk_2` FOREIGN KEY (`ID_room`) REFERENCES `rooms` (`ID_room`);

--
-- Ograniczenia dla tabeli `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`ID_room`) REFERENCES `rooms` (`ID_room`),
  ADD CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`ID_user`) REFERENCES `users` (`ID_user`);

--
-- Ograniczenia dla tabeli `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `rooms_ibfk_1` FOREIGN KEY (`id_type`) REFERENCES `room_types` (`id_type`),
  ADD CONSTRAINT `rooms_ibfk_2` FOREIGN KEY (`ID_building`) REFERENCES `buildings` (`ID_building`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
