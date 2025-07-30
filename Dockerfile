FROM postgres
ENV POSTGRES_DB='house_hold_budget'
COPY initial_data.sql /docker-entrypoint-initdb.d/