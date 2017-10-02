package com.netflix.vms.transformer.hollowinput;

import com.netflix.hollow.api.objects.delegate.HollowObjectAbstractDelegate;
import com.netflix.hollow.core.read.dataaccess.HollowObjectTypeDataAccess;
import com.netflix.hollow.core.schema.HollowObjectSchema;
import com.netflix.hollow.api.custom.HollowTypeAPI;
import com.netflix.hollow.api.objects.delegate.HollowCachedDelegate;

@SuppressWarnings("all")
public class CacheDeploymentIntentDelegateCachedImpl extends HollowObjectAbstractDelegate implements HollowCachedDelegate, CacheDeploymentIntentDelegate {

    private final Long streamProfileId;
    private final int isoCountryCodeOrdinal;
    private final Long bitrateKBPS;
    private CacheDeploymentIntentTypeAPI typeAPI;

    public CacheDeploymentIntentDelegateCachedImpl(CacheDeploymentIntentTypeAPI typeAPI, int ordinal) {
        this.streamProfileId = typeAPI.getStreamProfileIdBoxed(ordinal);
        this.isoCountryCodeOrdinal = typeAPI.getIsoCountryCodeOrdinal(ordinal);
        this.bitrateKBPS = typeAPI.getBitrateKBPSBoxed(ordinal);
        this.typeAPI = typeAPI;
    }

    public long getStreamProfileId(int ordinal) {
        if(streamProfileId == null)
            return Long.MIN_VALUE;
        return streamProfileId.longValue();
    }

    public Long getStreamProfileIdBoxed(int ordinal) {
        return streamProfileId;
    }

    public int getIsoCountryCodeOrdinal(int ordinal) {
        return isoCountryCodeOrdinal;
    }

    public long getBitrateKBPS(int ordinal) {
        if(bitrateKBPS == null)
            return Long.MIN_VALUE;
        return bitrateKBPS.longValue();
    }

    public Long getBitrateKBPSBoxed(int ordinal) {
        return bitrateKBPS;
    }

    @Override
    public HollowObjectSchema getSchema() {
        return typeAPI.getTypeDataAccess().getSchema();
    }

    @Override
    public HollowObjectTypeDataAccess getTypeDataAccess() {
        return typeAPI.getTypeDataAccess();
    }

    public CacheDeploymentIntentTypeAPI getTypeAPI() {
        return typeAPI;
    }

    public void updateTypeAPI(HollowTypeAPI typeAPI) {
        this.typeAPI = (CacheDeploymentIntentTypeAPI) typeAPI;
    }

}