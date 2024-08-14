ALTER TABLE `sqmusic`.`download_info`
    ADD COLUMN `audio_book` varchar(255) NULL AFTER `spring_name`,
    ADD COLUMN `add_subsonic_playList_name` varchar(255) NULL AFTER `audio_book`;
