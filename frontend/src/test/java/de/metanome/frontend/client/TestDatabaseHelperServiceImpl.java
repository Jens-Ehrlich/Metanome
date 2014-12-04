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

package de.metanome.frontend.client;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.metanome.backend.resources.AlgorithmResource;
import de.metanome.backend.results_db.Algorithm;
import de.metanome.backend.results_db.HibernateUtil;

/**
 * A service to reset the database in gwt client side tests.
 *
 * @author Jakob Zwiener
 */
public class TestDatabaseHelperServiceImpl extends RemoteServiceServlet
    implements TestDatabaseHelperService {

  /**
   * Resets the database.
   */
  @Override
  public void resetDatabase() {
    HibernateUtil.clear();
  }

  /**
   * Stores an algorithm in the database.
   *
   * @param algorithm the algorithm to store
   */
  @Override
  public void storeAlgorithmInDatabase(Algorithm algorithm) {
    AlgorithmResource.addAlgorithm(algorithm);
  }

}
