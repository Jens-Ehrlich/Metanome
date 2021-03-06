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

package de.metanome.backend.input.sql;

import de.metanome.algorithm_integration.AlgorithmConfigurationException;
import de.metanome.algorithm_integration.configuration.ConfigurationSettingTableInput;
import de.metanome.algorithm_integration.input.InputGenerationException;
import de.metanome.algorithm_integration.input.RelationalInput;
import de.metanome.algorithm_integration.input.TableInputGenerator;

/**
 * Provides database tables as {@link RelationalInput} by executing select statements on an
 * underlying {@link DefaultDatabaseConnectionGenerator}.
 *
 * @author Jakob Zwiener
 * @see de.metanome.algorithm_integration.input.RelationalInput
 * @see DefaultDatabaseConnectionGenerator
 */
public class DefaultTableInputGenerator implements TableInputGenerator {

  protected static final String BASE_STATEMENT = "SELECT * FROM ";

  protected DefaultDatabaseConnectionGenerator defaultDatabaseConnectionGenerator;
  protected String table;

  /**
   * Exists for tests.
   */
  protected DefaultTableInputGenerator(
      DefaultDatabaseConnectionGenerator defaultDatabaseConnectionGenerator, String table) {
    this.defaultDatabaseConnectionGenerator = defaultDatabaseConnectionGenerator;
    this.table = table;
  }

  /**
   * @param setting the table input setting to construct the table input generator from
   * @throws AlgorithmConfigurationException is thrown if the underlying {@link DefaultDatabaseConnectionGenerator} cannot be instantiated.
   */
  public DefaultTableInputGenerator(ConfigurationSettingTableInput setting)
      throws AlgorithmConfigurationException {
    this.defaultDatabaseConnectionGenerator =
        new DefaultDatabaseConnectionGenerator(setting.getDatabaseConnection());
    this.table = setting.getTable();
  }

  /**
   * Generates a new {@link de.metanome.algorithm_integration.input.RelationalInput} to iterate over the data in the table.
   *
   * @return the {@link de.metanome.algorithm_integration.input.RelationalInput}
   * @throws InputGenerationException if the sql statement could not be executed
   */
  @Override
  public RelationalInput generateNewCopy() throws InputGenerationException {
    return defaultDatabaseConnectionGenerator
        .generateRelationalInputFromSql(BASE_STATEMENT + table);
  }
}
