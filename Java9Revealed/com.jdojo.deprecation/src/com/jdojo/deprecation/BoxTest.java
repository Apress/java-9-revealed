// BoxTest.java
package com.jdojo.deprecation;

public class BoxTest {
    /**
     * API: Not deprecated 
     * Use-site: Not deprecated
     * Deprecation warning: No warning
     */
    public static void m11() {
        Box.notDeprecated();
    }
        
    /**
    * API: Ordinarily deprecated
    * Use-site: Not deprecated
    * Deprecation warning: No warning
    */
    public static void m12() {
        Box.deprecatedOrdinarily();
    }
    
    /**
     * API: Terminally deprecated
     * Use-site: Not deprecated
     * Deprecation warning: Removal warning
     */
    public static void m13() {
        Box.deprecatedTerminally();
    }
            
    /**
     * API: Not deprecated
     * Use-site: Ordinarily deprecated
     * Deprecation warning: No warning
     * @deprecated Dangerous to use.
     */
    @Deprecated(since="1.1")
    public static void m21() {
        Box.notDeprecated();
    }
        
    /**
    * API: Ordinarily deprecated
    * Use-site: Ordinarily deprecated
    * Deprecation warning: No warning
    * @deprecated Dangerous to use.
    */
    @Deprecated(since="1.1")    
    public static void m22() {
        Box.deprecatedOrdinarily();
    }
    
    /**
     * API: Terminally deprecated
     * Use-site: Ordinarily deprecated
     * Deprecation warning: Removal warning
     * @deprecated Dangerous to use.
    */
    @Deprecated(since="1.1")
    public static void m23() {
        Box.deprecatedTerminally();
    }
        
    /**
     * API: Not deprecated
     * Use-site: Terminally deprecated
     * Deprecation warning: No warning
     * @deprecated Going away.
     */
    @Deprecated(since="1.1", forRemoval=true)
    public static void m31() {
        Box.notDeprecated();
    }
        
    /**
    * API: Ordinarily deprecated
    * Use-site: Terminally deprecated
    * Deprecation warning: No warning
    * @deprecated Going away.
    */
    @Deprecated(since="1.1", forRemoval=true)
    public static void m32() {
        Box.deprecatedOrdinarily();
    }
    
    /**
     * API: Terminally deprecated
     * Use-site: Terminally deprecated
     * Deprecation warning: Removal warning
     * @deprecated Going away.
    */
    @Deprecated(since="1.1", forRemoval=true)
    public static void m33() {
        Box.deprecatedTerminally();
    }
    
    
    /**
     * API: Ordinarily and Terminally deprecated
     * Use-site: Not deprecated
     * Deprecation warning: Ordinary and removal warnings
    */    
    public static void m41() {
        Box.deprecatedOrdinarily();
        Box.deprecatedTerminally();        
    }
    
    /**
     * API: Ordinarily and Terminally deprecated
     * Use-site: Not deprecated
     * Deprecation warning: Ordinary warnings
    */    
    @SuppressWarnings("deprecation")
    public static void m42() {
        Box.deprecatedOrdinarily();
        Box.deprecatedTerminally();        
    }
    
    /**
     * API: Ordinarily and Terminally deprecated
     * Use-site: Not deprecated
     * Deprecation warning: Removal warnings
    */    
    @SuppressWarnings("removal")
    public static void m43() {
        Box.deprecatedOrdinarily();
        Box.deprecatedTerminally();        
    }
        
    /**
     * API: Ordinarily and Terminally deprecated
     * Use-site: Not deprecated
     * Deprecation warning: Removal warnings
    */    
    @SuppressWarnings({"deprecation", "removal"})
    public static void m44() {
        Box.deprecatedOrdinarily();
        Box.deprecatedTerminally();        
    }
}
