
INSERT INTO oauth_client_details
    (client_id, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information, autoapprove)
VALUES
    ('hrmanagerClient', '$2a$11$KwHTVyUa8kAbIh1yehn99.cH1EOF058gJ8KjFHOetX0qbrxRqhNhK', 'read,write',
    'password,authorization_code,refresh_token', null, 'ADMIN,USER', 360000, 360000, null, true);
-- hrmanagerSecret