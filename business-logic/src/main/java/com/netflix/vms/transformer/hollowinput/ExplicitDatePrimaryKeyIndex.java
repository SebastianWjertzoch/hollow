package com.netflix.vms.transformer.hollowinput;

import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.consumer.index.AbstractHollowUniqueKeyIndex;
import com.netflix.hollow.core.schema.HollowObjectSchema;

@SuppressWarnings("all")
public class ExplicitDatePrimaryKeyIndex extends AbstractHollowUniqueKeyIndex<VMSHollowInputAPI, ExplicitDateHollow> {

    public ExplicitDatePrimaryKeyIndex(HollowConsumer consumer) {
        this(consumer, true);    }

    public ExplicitDatePrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh) {
        this(consumer, isListenToDataRefresh, ((HollowObjectSchema)consumer.getStateEngine().getSchema("ExplicitDate")).getPrimaryKey().getFieldPaths());
    }

    public ExplicitDatePrimaryKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        this(consumer, true, fieldPaths);
    }

    public ExplicitDatePrimaryKeyIndex(HollowConsumer consumer, boolean isListenToDataRefresh, String... fieldPaths) {
        super(consumer, "ExplicitDate", isListenToDataRefresh, fieldPaths);
    }

    public ExplicitDateHollow findMatch(Object... keys) {
        int ordinal = idx.getMatchingOrdinal(keys);
        if(ordinal == -1)
            return null;
        return api.getExplicitDateHollow(ordinal);
    }

}