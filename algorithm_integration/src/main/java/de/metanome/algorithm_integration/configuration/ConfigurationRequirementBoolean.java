/*
 * Copyright 2014 by the Metanome project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.metanome.algorithm_integration.configuration;


import com.google.common.annotations.GwtIncompatible;

import com.fasterxml.jackson.annotation.JsonTypeName;

import de.metanome.algorithm_integration.AlgorithmConfigurationException;

import javax.xml.bind.annotation.XmlTransient;


/**
 * Concrete {@link ConfigurationRequirement} for booleans.
 *
 * @author Jakob Zwiener
 * @see ConfigurationRequirement
 */
@JsonTypeName("ConfigurationRequirementBoolean")
public class ConfigurationRequirementBoolean extends ConfigurationRequirementDefaultValue<Boolean> {

  public ConfigurationSettingBoolean[] settings;

  /**
   * Exists for serialization.
   */
  public ConfigurationRequirementBoolean() {
  }

  /**
   * Construct a {@link ConfigurationRequirementBoolean}, requesting 1 value.
   *
   * @param identifier the specification's identifier
   */
  public ConfigurationRequirementBoolean(String identifier) {
    super(identifier);
  }

  /**
   * Constructs a {@link ConfigurationRequirementBoolean}, potentially requesting several values.
   *
   * @param identifier      the specification's identifier
   * @param numberOfSettings the number of settings expected
   */
  public ConfigurationRequirementBoolean(String identifier,
                                         int numberOfSettings) {
    super(identifier, numberOfSettings);
  }

  /**
   * Constructs a {@link ConfigurationRequirementBoolean}, requesting several values.
   *
   * @param identifier         the specification's identifier
   * @param minNumberOfSetting the min number of settings expected
   * @param maxNumberOfSetting the max number of settings expected
   */
  public ConfigurationRequirementBoolean(String identifier,
                                         int minNumberOfSetting,
                                         int maxNumberOfSetting) {
    super(identifier, minNumberOfSetting, maxNumberOfSetting);
  }

  @Override
  public ConfigurationSettingBoolean[] getSettings() {
    return settings;
  }

  /**
   * Sets the default values on the settings, if both are set.
   */
  @XmlTransient
  @Override
  public void applyDefaultValues() {
    if (this.defaultValues == null || this.settings == null ||
        this.defaultValues.length != this.settings.length)
      return;

    for (int i = 0; i < this.settings.length; i++) {
      this.settings[i].setValue(this.defaultValues[i]);
    }
  }

  /**
   * Exists only for serialization!
   * @param settings the settings
   */
  public void setSettings(ConfigurationSettingBoolean... settings) {
    this.settings = settings;
  }

  /**
   * Sets the actual settings on the requirement if the number of settings is correct.
   *
   * @param settings the settings
   * @throws de.metanome.algorithm_integration.AlgorithmConfigurationException if the number of
   * settings does not match the expected number of settings
   */
  @XmlTransient
  public void checkAndSetSettings(ConfigurationSettingBoolean... settings)
      throws AlgorithmConfigurationException {
    checkNumberOfSettings(settings.length);
    this.settings = settings;
    applyDefaultValues();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @XmlTransient
  @GwtIncompatible("ConfigurationValues cannot be build on client side.")
  public ConfigurationValue build(ConfigurationFactory factory)
      throws AlgorithmConfigurationException {
    return factory.build(this);
  }
}
