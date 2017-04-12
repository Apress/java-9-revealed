// Policy.java
package com.jdojo.policy;

public class Policy {
    private int policyId;
    private Risk[] risks;
    
    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public Risk[] getRisks() {
        return risks;
    }

    public void setRisks(Risk[] risks) {
        this.risks = risks;
    }
}
