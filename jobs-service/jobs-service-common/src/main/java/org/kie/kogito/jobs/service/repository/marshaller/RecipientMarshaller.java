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
package org.kie.kogito.jobs.service.repository.marshaller;

import java.util.Objects;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import org.kie.kogito.jobs.service.model.Recipient;
import org.kie.kogito.jobs.service.model.RecipientInstance;

import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class RecipientMarshaller implements Marshaller<Recipient, JsonObject> {

    public static final String CLASS_TYPE = "classType";

    @Override
    public JsonObject marshall(Recipient recipient) {
        if (Objects.isNull(recipient)) {
            return null;
        }
        return JsonObject
                .mapFrom(recipient.getRecipient())
                .put(CLASS_TYPE, recipient.getRecipient().getClass().getName());
    }

    @Override
    public Recipient unmarshall(JsonObject jsonObject) {
        if (Objects.isNull(jsonObject)) {
            return null;
        }
        String classType = Optional.ofNullable(jsonObject).map(o -> (String) o.remove(CLASS_TYPE)).orElse(null);
        if (Objects.isNull(classType)) {
            return null;
        }
        try {
            return new RecipientInstance((org.kie.kogito.jobs.service.api.Recipient<?>) jsonObject.mapTo(Class.forName(classType)));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
