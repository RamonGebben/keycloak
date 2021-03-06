/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.provider;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
public class ProviderConfigurationBuilder {

    private List<ProviderConfigProperty> properties = new LinkedList<>();

    private ProviderConfigurationBuilder() {
    }

    public static ProviderConfigurationBuilder create() {
        return new ProviderConfigurationBuilder();
    }

    public ProviderConfigPropertyBuilder property() {
        return new ProviderConfigPropertyBuilder();
    }

    public ProviderConfigurationBuilder property(ProviderConfigProperty property) {
        properties.add(property);
        return this;
    }

    public ProviderConfigurationBuilder property(String name, String label, String helpText, String type, Object defaultValue, List<String> options, boolean secret) {
        ProviderConfigProperty property = new ProviderConfigProperty(name, label, helpText, type, defaultValue);
        property.setOptions(options);
        property.setSecret(secret);
        properties.add(property);
        return this;
    }
    public ProviderConfigurationBuilder property(String name, String label, String helpText, String type, Object defaultValue, List<String> options) {
        ProviderConfigProperty property = new ProviderConfigProperty(name, label, helpText, type, defaultValue);
        property.setOptions(options);
        properties.add(property);
        return this;
    }

    public List<ProviderConfigProperty> build() {
        return properties;
    }

    public class ProviderConfigPropertyBuilder {

        private String name;
        private String label;
        private String helpText;
        private String type;
        private Object defaultValue;
        private List<String> options;
        private boolean secret;

        public ProviderConfigPropertyBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProviderConfigPropertyBuilder label(String label) {
            this.label = label;
            return this;
        }

        public ProviderConfigPropertyBuilder helpText(String helpText) {
            this.helpText = helpText;
            return this;
        }

        public ProviderConfigPropertyBuilder type(String type) {
            this.type = type;
            return this;
        }

        public ProviderConfigPropertyBuilder defaultValue(Object defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public ProviderConfigPropertyBuilder options(String... options) {
            this.options = Arrays.asList(options);
            return this;
        }

        public ProviderConfigPropertyBuilder options(List<String> options) {
            this.options = options;
            return this;
        }


        public ProviderConfigPropertyBuilder secret(boolean secret) {
            this.secret = secret;
            return this;
        }

        public ProviderConfigurationBuilder add() {
            ProviderConfigProperty property = new ProviderConfigProperty();
            property.setName(name);
            property.setLabel(label);
            property.setHelpText(helpText);
            property.setType(type);
            property.setDefaultValue(defaultValue);
            property.setOptions(options);
            property.setSecret(secret);
            ProviderConfigurationBuilder.this.properties.add(property);
            return ProviderConfigurationBuilder.this;
        }

    }

}
