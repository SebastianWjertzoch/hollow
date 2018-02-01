package com.netflix.vms.transformer.hollowinput;

import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.index.AbstractHollowUniqueKeyIndex;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class SupplementalsPrimaryKeyIndex extends AbstractHollowUniqueKeyIndex<VMSHollowInputAPI, SupplementalsHollow> {

    public SupplementalsPrimaryKeyIndex(HollowConsumer consumer) {
        this(consumer, true);    }

    public SupplementalsPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh) {
        this(consumer, isListenToDataRefresh, ((HollowObjectSchema)consumer.getStateEngine().getSchema("Supplementals")).getPrimaryKey().getFieldPaths());
    }

    public SupplementalsPrimaryKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        this(consumer, true, fieldPaths);
    }

    public SupplementalsPrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh, String... fieldPaths) {
        super(consumer, "Supplementals", isListenToDataRefresh, fieldPaths);
    }

    public SupplementalsHollow findMatch(Object... keys) {
        int ordinal = idx.getMatchingOrdinal(keys);
        if(ordinal == -1)
            return null;
        return api.getSupplementalsHollow(ordinal);
    }

}