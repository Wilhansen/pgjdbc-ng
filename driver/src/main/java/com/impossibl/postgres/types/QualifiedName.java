/**
 * Copyright (c) 2013, impossibl.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *  * Neither the name of impossibl.com nor the names of its contributors may
 *    be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.impossibl.postgres.types;

import static com.impossibl.postgres.system.Identifier.quoteIfNeeded;
import static com.impossibl.postgres.utils.guava.Preconditions.checkNotNull;

import java.util.Objects;

public class QualifiedName {

  private String namespace;
  private String localName;

  public QualifiedName(String namespace, String localName) {
    this.namespace = checkNotNull(namespace);
    this.localName = checkNotNull(localName);
  }

  public String getNamespace() {
    return namespace;
  }

  public String getLocalName() {
    return localName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    QualifiedName that = (QualifiedName) o;
    return namespace.equals(that.namespace) &&
        localName.equals(that.localName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(namespace, localName);
  }

  @Override
  public String toString() {
    return toString(true);
  }

  public String toString(boolean unqualifyCommonSchemas) {
    if (unqualifyCommonSchemas && (namespace.equals(Type.CATALOG_NAMESPACE) || namespace.equals(Type.PUBLIC_NAMESPACE))) {
      return quoteIfNeeded(localName);
    }
    return quoteIfNeeded(namespace) + "." + quoteIfNeeded(localName);
  }

}
