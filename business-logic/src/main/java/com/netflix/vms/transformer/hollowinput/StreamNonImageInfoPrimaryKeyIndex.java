package com.netflix.vms.transformer.hollowinput;

import com.netflix.hollow.api.consumer.HollowConsumer;
import com.netflix.hollow.api.custom.HollowAPI;
import com.netflix.hollow.core.schema.HollowObjectSchema;
import com.netflix.hollow.core.index.HollowPrimaryKeyIndex;
import com.netflix.hollow.core.read.engine.HollowReadStateEngine;

public class StreamNonImageInfoPrimaryKeyIndex implements HollowConsumer.RefreshListener {

    private HollowPrimaryKeyIndex idx;
    private VMSHollowInputAPI api;

    public StreamNonImageInfoPrimaryKeyIndex(HollowConsumer consumer) {
        this(consumer, ((HollowObjectSchema)consumer.getStateEngine().getSchema("StreamNonImageInfo")).getPrimaryKey().getFieldPaths());
    }

    public StreamNonImageInfoPrimaryKeyIndex(HollowConsumer consumer, String... fieldPaths) {
        consumer.getRefreshLock().lock();
        try {
            this.api = (VMSHollowInputAPI)consumer.getAPI();
            this.idx = new HollowPrimaryKeyIndex(consumer.getStateEngine(), "StreamNonImageInfo", fieldPaths);
            idx.listenForDeltaUpdates();
            consumer.addRefreshListener(this);
        } catch(ClassCastException cce) {
            throw new ClassCastException("The HollowConsumer provided was not created with the VMSHollowInputAPI generated API class.");
        } finally {
            consumer.getRefreshLock().unlock();
        }
    }

    public StreamNonImageInfoHollow findMatch(Object... keys) {
        int ordinal = idx.getMatchingOrdinal(keys);
        if(ordinal == -1)
            return null;
        return api.getStreamNonImageInfoHollow(ordinal);
    }

    @Override public void snapshotUpdateOccurred(HollowAPI api, HollowReadStateEngine stateEngine, long version) throws Exception {
        idx.detachFromDeltaUpdates();
        idx = new HollowPrimaryKeyIndex(stateEngine, idx.getPrimaryKey());
        idx.listenForDeltaUpdates();
        this.api = (VMSHollowInputAPI)api;
    }

    @Override public void deltaUpdateOccurred(HollowAPI api, HollowReadStateEngine stateEngine, long version) throws Exception {
        this.api = (VMSHollowInputAPI)api;
    }

    @Override public void refreshStarted(long currentVersion, long requestedVersion) { }
    @Override public void blobLoaded(HollowConsumer.Blob transition) { }
    @Override public void refreshSuccessful(long beforeVersion, long afterVersion, long requestedVersion) { }
    @Override public void refreshFailed(long beforeVersion, long afterVersion, long requestedVersion, Throwable failureCause) { }
}