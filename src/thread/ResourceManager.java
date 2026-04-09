package thread;

/**
 * Demonstrates deadlock prevention - Section V
 */
public class ResourceManager {
    private final Object gradeLock = new Object();
    private final Object reportLock = new Object();

    // Commented: This would cause deadlock if used
    /*
    public void badMethod() {
        synchronized (gradeLock) {
            synchronized (reportLock) {
                // deadlock possible
            }
        }
    }
    */

    // Safe way - always acquire locks in the same order
    public void safeUpdateGrades() {
        synchronized (gradeLock) {
            synchronized (reportLock) {
                // safe code here
            }
        }
    }
}