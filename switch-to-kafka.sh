cf set-env portfolio-pricer SPRING_CLOUD_STREAM_BINDINGS_PRICERTICK_BINDER=kafka && \
  cf set-env portfolio-pricer SPRING_CLOUD_STREAM_BINDINGS_PORTFOLIOPRICING_BINDER=kafka && \
  cf set-env tick-producer SPRING_CLOUD_STREAM_BINDINGS_PRICERTICK_BINDER=kafka && \
  cf set-env event-store SPRING_CLOUD_STREAM_BINDINGS_PRICERTICK_BINDER=kafka && \
  cf set-env event-store SPRING_CLOUD_STREAM_BINDINGS_PRICERTICK_BINDER=kafka && \
  cf set-env portfolio-pricer && \
  cf delete-service amqp && \
  cf restart tick-producer && \
  cf restart portfolio-pricer && \
  cf restart event-store
