/*
 *  Copyright (c) 2011-2015 The original author or authors
 *  ------------------------------------------------------
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *       The Eclipse Public License is available at
 *       http://www.eclipse.org/legal/epl-v10.html
 *
 *       The Apache License v2.0 is available at
 *       http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

package io.vertx.maven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Options to configure the resolver.
 *
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class ResolverOptions {

  public static final String LOCAL_REPO_SYS_PROP = "vertx.maven.localRepo";
  public static final String REMOTE_REPOS_SYS_PROP = "vertx.maven.remoteRepos";
  public static final String HTTP_PROXY_SYS_PROP = "vertx.maven.httpProxy";
  public static final String HTTPS_PROXY_SYS_PROP = "vertx.maven.httpsProxy";

  public static final String REMOTE_SNAPSHOT_POLICY_SYS_PROP = "vertx.maven.remoteSnapshotPolicy";

  private static final String USER_HOME = System.getProperty("user.home");
  private static final String FILE_SEP = System.getProperty("file.separator");
  private static final String DEFAULT_MAVEN_LOCAL = USER_HOME + FILE_SEP + ".m2" + FILE_SEP + "repository";
  private static final String DEFAULT_MAVEN_REMOTES =
      "http://central.maven.org/maven2/ https://s01.oss.sonatype.org/content/repositories/snapshots/";
  private static final String DEFAULT_REMOTE_SNAPSHOT_POLICY = "daily";

  private String localRepository = System.getProperty(LOCAL_REPO_SYS_PROP, DEFAULT_MAVEN_LOCAL);

  private List<String> remoteRepositories =
      new ArrayList<>(Arrays.asList(System.getProperty(REMOTE_REPOS_SYS_PROP, DEFAULT_MAVEN_REMOTES).split(" ")));

  private String httpProxy = System.getProperty(HTTP_PROXY_SYS_PROP);
  private String httpsProxy = System.getProperty(HTTPS_PROXY_SYS_PROP);

  private String remoteSnapshotPolicy = System.getProperty(REMOTE_SNAPSHOT_POLICY_SYS_PROP,
      DEFAULT_REMOTE_SNAPSHOT_POLICY);

  /**
   * @return the configured proxy address for HTTP request, {@code null} if none.
   */
  public String getHttpProxy() {
    return httpProxy;
  }

  /**
   * Sets the address of the proxy used for HTTP requests.
   *
   * @param httpProxy the proxy address
   * @return the current {@link ResolverOptions} instance
   */
  public ResolverOptions setHttpProxy(String httpProxy) {
    this.httpProxy = httpProxy;
    return this;
  }

  /**
   * @return the configured proxy address for HTTPS request, {@code null} if none.
   */
  public String getHttpsProxy() {
    return httpsProxy;
  }

  /**
   * Sets the address of the proxy used for HTTPS requests.
   *
   * @param httpsProxy the proxy address
   * @return the current {@link ResolverOptions} instance
   */
  public ResolverOptions setHttpsProxy(String httpsProxy) {
    this.httpsProxy = httpsProxy;
    return this;
  }

  /**
   * @return the path of the local repository. By default it's ~/.m2/repository.
   */
  public String getLocalRepository() {
    return localRepository;
  }

  /**
   * Sets the path to the local Maven repository. By default it's ~/.m2/repository.
   *
   * @param localRepository the local repository
   * @return the current {@link ResolverOptions} instance
   */
  public ResolverOptions setLocalRepository(String localRepository) {
    this.localRepository = localRepository;
    return this;
  }

  /**
   * @return the list of remove repositories.
   */
  public List<String> getRemoteRepositories() {
    if (remoteRepositories == null || remoteRepositories.isEmpty()) {
      String remoteString = System.getProperty(REMOTE_REPOS_SYS_PROP, DEFAULT_MAVEN_REMOTES);
      // They are space-delimited (space is illegal char in urls)
      remoteRepositories = Arrays.asList(remoteString.split(" "));
    }
    return remoteRepositories;
  }

  /**
   * Sets the list of remote repositories used by the resolver. The repository must use the Maven 2 repository
   * layout. If you don't want to resolved from remote locations, set it to an empty list. By default it resolves
   * from Maven central and Sonatype OSS Snapshots.
   *
   * @param remoteRepositories the list of remote repositories
   * @return the current {@link ResolverOptions} instance
   */
  public ResolverOptions setRemoteRepositories(List<String> remoteRepositories) {
    this.remoteRepositories = remoteRepositories;
    return this;
  }

  /**
   * Gets the remote snapshot policy.
   *
   * @return the remote snapshot policy.
   */
  public String getRemoteSnapshotPolicy() {
    return remoteSnapshotPolicy;
  }

  /**
   * Sets the remote snapshot policy (`daily` by default). Accepted values are {@code daily}, {@code never}, {@code always} and
   * {@code interval:X} where {@code X} is the number of minutes between two resolutions.
   *
   * @param remoteSnapshotPolicy the desired policy
   * @return the current {@link ResolverOptions} instance
   */
  public ResolverOptions setRemoteSnapshotPolicy(String remoteSnapshotPolicy) {
    this.remoteSnapshotPolicy = remoteSnapshotPolicy;
    return this;
  }
}
