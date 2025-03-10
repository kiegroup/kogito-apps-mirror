/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.kogito.jobs.service.messaging.kafka;

import org.kie.kogito.jobs.service.messaging.ReactiveMessagingEventConsumerTest;
import org.kie.kogito.jobs.service.repository.ReactiveJobRepository;
import org.kie.kogito.jobs.service.scheduler.impl.TimerDelegateJobScheduler;

import com.fasterxml.jackson.databind.ObjectMapper;

class KafkaReactiveMessagingEventConsumerTest extends ReactiveMessagingEventConsumerTest<KafkaReactiveMessagingEventConsumer> {

    @Override
    protected KafkaReactiveMessagingEventConsumer createEventConsumer(TimerDelegateJobScheduler scheduler,
            ReactiveJobRepository jobRepository,
            ObjectMapper objectMapper) {
        return new KafkaReactiveMessagingEventConsumer(scheduler, jobRepository, objectMapper);
    }
}
