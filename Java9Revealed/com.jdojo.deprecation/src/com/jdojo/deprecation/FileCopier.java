// FileCopier.java
package com.jdojo.deprecation;

import java.io.File;

/**
 * The class consists of static methods that can be used to 
 * copy files and directories.
 * 
 * @deprecated Deprecated since 1.4. Not safe to use. Use the 
 * <code>java.nio.file.Files</code> class instead. This class 
 * will be removed in a future release of this library.
 * 
 * @since 1.2
 */

@Deprecated
public class FileCopier { 
    // No direct instantiation supported.
    private FileCopier() {
    }
    
    /**
     * Copies the contents of src to dst.
     * @param src The source file
     * @param dst The destination file
     * @return true if the copy is successfully, 
     * false otherwise.
     */
    public static boolean copy(File src, File dst) {
        // More code goes here
        
        return true;
    }
    
    // More methods go here
}
