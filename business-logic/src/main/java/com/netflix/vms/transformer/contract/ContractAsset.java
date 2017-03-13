package com.netflix.vms.transformer.contract;

import com.netflix.vms.transformer.hollowinput.RightsContractAssetHollow;

public class ContractAsset {
    
    private final ContractAssetType type;
    private final String language;
    private final String locale;
    
    public ContractAsset(RightsContractAssetHollow assetInput) {
        this(ContractAssetType.fromAssetTypeString(assetInput._getAssetType()._getValue()), assetInput._getBcp47Code()._getValue());
    }
    
    public ContractAsset(ContractAssetType type, String locale) {
        this.type = type;
        this.locale = locale;

        String language = locale;
        if(language.indexOf('-') != -1)
            language = language.substring(0, language.indexOf('-'));
        if(language.indexOf('_') != -1)
            language = language.substring(0, language.indexOf('_'));
        
        this.language = language;
    }

    public ContractAssetType getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public String getLocale() {
        return locale;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((locale == null) ? 0 : locale.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContractAsset other = (ContractAsset) obj;
        if (locale == null) {
            if (other.locale != null)
                return false;
        } else if (!locale.equals(other.locale))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ContractAsset [type=" + type + ", locale=" + locale + "]";
    }
    
}