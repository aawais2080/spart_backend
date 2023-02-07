INSERT INTO sport_category (id, name)
VALUES ('4706550d-8610-4e2e-a71d-5d1126b1a379', 'Soccer'), ('0bd98263-c712-4bbc-9559-58413d6553c7', 'Basketball');

INSERT INTO municipality(id, name)
VALUES ('03d9f02b-9618-4564-8cbe-097a4247cc2a', 'Gemeente Utrecht');

INSERT INTO spartan (id, municipality_id, name, email, date_of_birth)
VALUES ('96e6b1e9-0e84-4fdc-aa6d-9392def7b85f','03d9f02b-9618-4564-8cbe-097a4247cc2a', 'Firstname Lastname', 'test@test.com', '1997-01-12');

INSERT INTO sport (id, sport_category_id, name, description)
VALUES ('65be6312-da0d-4d4e-ba32-b6434924cb9e', '4706550d-8610-4e2e-a71d-5d1126b1a379', 'Soccer', 'Soccer test'), ('7f40716e-d236-4bbb-b345-6bd3f47a1361', '0bd98263-c712-4bbc-9559-58413d6553c7', 'Basketball', 'Basketball test');

INSERT INTO sport_type (id, sport_id, name, description)
VALUES ('4c4cee77-eda4-4f8d-b1b1-927b7b688e3a', '65be6312-da0d-4d4e-ba32-b6434924cb9e', 'Soccer_type_test', 'Soccer_type_test_description');

INSERT INTO location (id, municipality_id, name, address, longitude, latitude, description)
VALUES ('67916f31-dd6d-441f-a5eb-6594b143179e', '03d9f02b-9618-4564-8cbe-097a4247cc2a', 'Locatie_Utrecht_test', 'Ringwade 1', '52.0907374', '5.1214201', 'Locatie_Utrecht_test_description');

INSERT INTO sport_club (id, location_id, name, primaryColor, secondaryColor, phoneNumber, email, description)
VALUES ('c35ebf6d-260f-4c3e-8d7a-f50664c558a1', '67916f31-dd6d-441f-a5eb-6594b143179e', 'Sportclub_Utrecht_test', '#000000', '#FFFFFF', '030-1234567', 'sportclub_test@test.com', 'sportclub_description_test');

INSERT INTO spartan_liked_sport(id, sport_id, spartan_id)
VALUES ('09953e6b-1b33-401b-b8ef-203fce9e6ad4', '65be6312-da0d-4d4e-ba32-b6434924cb9e', '96e6b1e9-0e84-4fdc-aa6d-9392def7b85f');

INSERT INTO sport_event (id, sport_id, location_id, name, description, date_time, price, level, maxParticipants)
VALUES ('bb365894-3cdf-4552-9bcc-fc4bd0bbbde0', '65be6312-da0d-4d4e-ba32-b6434924cb9e', '67916f31-dd6d-441f-a5eb-6594b143179e', 'Sport_event_test', 'Sport_event_test_description', '2020-01-01 12:00:00', '10', 1, '10');

INSERT INTO spartan_in_event (id, sport_event_id, spartan_id)
VALUES ('a2925812-f8ec-42eb-ae1e-51656e6aa9b1', 'bb365894-3cdf-4552-9bcc-fc4bd0bbbde0', '96e6b1e9-0e84-4fdc-aa6d-9392def7b85f');

INSERT INTO sport_event_comment (id, sport_event_id, spartan_id, comment, date_time)
VALUES ('befcc3c2-6832-4b27-ab27-2546be365816', 'bb365894-3cdf-4552-9bcc-fc4bd0bbbde0', '96e6b1e9-0e84-4fdc-aa6d-9392def7b85f', 'Sport_event_comment_test', '2020-01-01 12:00:00');

