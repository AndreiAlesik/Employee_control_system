CREATE TABLE dml_audit (
                           id SERIAL PRIMARY KEY,
                           username VARCHAR(50),
                           action VARCHAR(50),
                           table_name VARCHAR(100),
                           old_data JSONB,
                           new_data JSONB,
                           event_time TIMESTAMP
);

DROP FUNCTION log_dml_event() CASCADE;
CREATE OR REPLACE FUNCTION log_dml_event()
    RETURNS trigger AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO dml_audit (username, action, table_name, new_data, event_time)
        VALUES (current_user, TG_OP, TG_TABLE_NAME, row_to_json(NEW), clock_timestamp());
    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO dml_audit (username, action, table_name, old_data, new_data, event_time)
        VALUES (current_user, TG_OP, TG_TABLE_NAME, row_to_json(OLD), row_to_json(NEW), clock_timestamp());
    ELSIF TG_OP = 'DELETE' THEN
        INSERT INTO dml_audit (username, action, table_name, old_data, event_time)
        VALUES (current_user, TG_OP, TG_TABLE_NAME, row_to_json(OLD), clock_timestamp());
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER dml_event_trigger
    AFTER INSERT OR UPDATE OR DELETE ON users
    FOR EACH ROW
EXECUTE FUNCTION log_dml_event();