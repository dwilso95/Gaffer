/*
 * Copyright 2016-2017 Crown Copyright
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

package uk.gov.gchq.gaffer.operation.impl.export;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import uk.gov.gchq.gaffer.commonutil.iterable.CloseableIterable;
import uk.gov.gchq.gaffer.operation.Operation;
import uk.gov.gchq.gaffer.operation.export.GetExport;
import uk.gov.gchq.gaffer.operation.io.Output;
import uk.gov.gchq.gaffer.operation.serialisation.TypeReferenceImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A <code>GetExports</code> operation gets multiple exports and returns then
 * in a Map.
 * The keys in the map are: "[ExportOperationClassName]: [key]"
 * The values in the map are the exported values.
 */
public class GetExports implements
        Operation,
        Output<Map<String, CloseableIterable<?>>> {
    private List<GetExport> getExports = new ArrayList<>();

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
    public List<GetExport> getGetExports() {
        return getExports;
    }

    public void setGetExports(final List<GetExport> getExports) {
        if (null == getExports) {
            this.getExports = new ArrayList<>();
        } else {
            this.getExports = getExports;
        }
    }

    @Override
    public TypeReference<Map<String, CloseableIterable<?>>> getOutputTypeReference() {
        return (TypeReference) new TypeReferenceImpl.MapStringSet();
    }

    public static class Builder
            extends Operation.BaseBuilder<GetExports, Builder>
            implements Output.Builder<GetExports, Map<String, CloseableIterable<?>>, Builder> {
        public Builder() {
            super(new GetExports());
        }

        public Builder exports(final List<GetExport> exports) {
            _getOp().setGetExports(exports);
            return _self();
        }

        public Builder exports(final GetExport... exports) {
            _getOp().getGetExports().clear();
            Collections.addAll(_getOp().getGetExports(), exports);
            return _self();
        }
    }
}
