-- changeset amritkthapa:2
-- preconditions onFail:CONTINUE onError:HALT
CREATE TABLE IF NOT EXISTS booking (
    id              BIGINT AUTO_INCREMENT   NOT NULL,
    version         BIGINT                  NOT NULL,
    customerName    VARCHAR(255)            NOT NULL,
    mobileNumber    VARCHAR(255)            NOT NULL,
    start_time      TIME                    NOT NULL,
    end_time        TIME                    NOT NULL,
    PRIMARY KEY (id)
    );