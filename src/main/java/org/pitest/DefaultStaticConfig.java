/*
 * Copyright 2010 Henry Coles
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and limitations under the License. 
 */
package org.pitest;

import java.util.ArrayList;
import java.util.Collection;

import org.pitest.extension.GroupingStrategy;
import org.pitest.extension.OrderStrategy;
import org.pitest.extension.ResultClassifier;
import org.pitest.extension.StaticConfiguration;
import org.pitest.extension.TestDiscoveryListener;
import org.pitest.extension.TestFilter;
import org.pitest.extension.TestListener;
import org.pitest.extension.common.GroupPerClassStrategy;
import org.pitest.extension.common.NoOrderStrategy;

public class DefaultStaticConfig implements StaticConfiguration {

  private ResultClassifier                        classifier;
  private GroupingStrategy                        groupingStrategy;
  private final Collection<TestListener>          testListeners          = new ArrayList<TestListener>();
  private final Collection<TestDiscoveryListener> testDiscoveryListeners = new ArrayList<TestDiscoveryListener>();
  private final Collection<TestFilter>            testFilters            = new ArrayList<TestFilter>();
  private OrderStrategy                           orderStrategy;

  public DefaultStaticConfig() {
    this.classifier = new DefaultResultClassifier();
    this.groupingStrategy = new GroupPerClassStrategy();
    this.orderStrategy = new NoOrderStrategy();
  }

  public DefaultStaticConfig(final StaticConfiguration orig) {
    addConfiguration(orig);
  }

  public final void addConfiguration(final StaticConfiguration orig) {
    this.classifier = orig.getClassifier();
    this.testListeners.addAll(orig.getTestListeners());
    this.testDiscoveryListeners.addAll(orig.getDiscoveryListeners());
    this.groupingStrategy = orig.getGroupingStrategy();
    this.testFilters.addAll(orig.getTestFilters());
    this.orderStrategy = orig.getOrderStrategy();
  }

  public Collection<TestListener> getTestListeners() {
    return this.testListeners;
  }

  public ResultClassifier getClassifier() {
    return this.classifier;
  }

  public final void addTestListener(final TestListener listener) {
    this.testListeners.add(listener);
  }

  public final void addDiscoveryListener(final TestDiscoveryListener tdl) {
    this.testDiscoveryListeners.add(tdl);
  }

  public Collection<TestDiscoveryListener> getDiscoveryListeners() {
    return this.testDiscoveryListeners;
  }

  public GroupingStrategy getGroupingStrategy() {
    return this.groupingStrategy;
  }

  public Collection<TestFilter> getTestFilters() {
    return this.testFilters;
  }

  public OrderStrategy getOrderStrategy() {
    return this.orderStrategy;
  }

}