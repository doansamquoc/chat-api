CREATE TABLE conversations
(
    id         BIGSERIAL PRIMARY KEY,
    creator_id BIGINT      REFERENCES users (id) ON DELETE SET NULL,
    name       VARCHAR(255)         DEFAULT NULL, -- Null if is a group
    type       VARCHAR(50) NOT NULL CHECK ( type IN ('DIRECT', 'GROUP') ),
    avatar_url TEXT,
    created_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE conversation_members
(
    id              BIGSERIAL PRIMARY KEY,
    conversation_id BIGINT       NOT NULL REFERENCES conversations (id) ON DELETE CASCADE,
    user_id         BIGINT REFERENCES users (id) ON DELETE CASCADE,
    nickname        VARCHAR(255) NOT NULL, -- Use user's display name
    role            VARCHAR(50)  NOT NULL CHECK ( role IN ('ADMIN', 'MEMBER') ),
    created_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (conversation_id, user_id)
);

CREATE TABLE messages
(
    id                  BIGSERIAL PRIMARY KEY,
    conversation_id     BIGINT      NOT NULL REFERENCES conversations (id) ON DELETE CASCADE,
    sender_id           BIGINT      REFERENCES users (id) ON DELETE SET NULL,
    type                VARCHAR(50) NOT NULL DEFAULT 'TEXT' CHECK ( type IN ('TEXT', 'IMAGE', 'FILE', 'VIDEO', 'AUDIO', 'SYSTEM') ),
    content             TEXT        NOT NULL,
    reply_to_message_id BIGINT      REFERENCES messages (id) ON DELETE SET NULL,
    status              VARCHAR(20) NOT NULL DEFAULT 'SENT' CHECK (status IN ('SENDING', 'SENT', 'DELIVERED', 'SEEN')),
    created_at          TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted_at          TIMESTAMP
);

CREATE TABLE attachments
(
    id            BIGSERIAL PRIMARY KEY,
    message_id    BIGINT    NOT NULL REFERENCES messages (id) ON DELETE CASCADE,
    file_url      TEXT      NOT NULL,
    file_type     VARCHAR(50),
    file_size     BIGINT,
    thumbnail_url TEXT,
    created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);